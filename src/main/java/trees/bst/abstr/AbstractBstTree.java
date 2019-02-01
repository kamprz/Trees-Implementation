package trees.bst.abstr;

import org.apache.log4j.Logger;
import trees.iterator.TreeIteratorType;
import trees.iterator.TreeIterator;

import java.util.Iterator;

public abstract class AbstractBstTree<T extends Comparable,N extends AbstractBstNode<T,N>> implements Iterable{
    private N root;
    private final Logger logger = Logger.getLogger(AbstractBstTree.class);
    private TreeIteratorType iteratorType = TreeIteratorType.PREORDER;

    public AbstractBstTree() {
    }

    public AbstractBstTree(TreeIteratorType iteratorType) {
        this.iteratorType = iteratorType;
    }

    public boolean isEmpty()
    {
        return root == null;
    }


    public void addNode(N node)
    {
        if(root == null) root = node;
        else
        {
            AbstractBstNode currentIndicated = root;
            AbstractBstNode parent = null;
            boolean wentLeft = false;
            while(!foundPlaceForNode(currentIndicated))
            {
                if(node.getValue().compareTo(currentIndicated.getValue()) == 0) {
                    logger.debug("AbstractBstNode " + node + " already exists in a tree.");
                    return;
                }

                parent = currentIndicated;
                if(currentIndicated.isGreaterThan(node)) {
                    currentIndicated = currentIndicated.getLeftChild();
                    wentLeft = true;
                }
                else {
                    currentIndicated = currentIndicated.getRightChild();
                    wentLeft = false;
                }
            }
            currentIndicated = node;
            if(wentLeft) addNodeAsLeftChild(currentIndicated,parent);
            else addNodeAsRightChild(currentIndicated,parent);
        }
        logger.debug("AbstractBstNode " + node + " added.");
    }

    public boolean removeNode(T value)
    {
        N beingDeleted = findNode(value);
        if(beingDeleted == null)
        {
            logger.debug("Cant delete element. It doesnt exist.");
            return false;
        }
        else{
            if(beingDeleted.isLeaf())
            {
                logger.debug("Deleting " + beingDeleted + " which is a leaf.");
                removeLeaf(beingDeleted);
            }
            else if(beingDeleted.hasOneChild())
            {
                logger.debug("Deleting " + beingDeleted + " which has only 1 child.");
                removeNodeWithOnlyChild(beingDeleted);
            }
            else
            {
                logger.debug("Deleting " + beingDeleted + " which has 2 children.");
                removeNodeWith2Children(beingDeleted);
            }
            logger.debug("Deleted.");
            return true;
        }
    }


    public N findNode(T value)
    {
        N currentIndicated = root;
        while(currentIndicated != null && currentIndicated.getValue().compareTo(value) != 0)
        {
            if(currentIndicated.getValue().compareTo(value) > 0) currentIndicated = currentIndicated.getLeftChild();
            else currentIndicated = currentIndicated.getRightChild();
        }
        if(currentIndicated == null) logger.debug("AbstractBstNode with value=" + value +  " doesnt exist.");
        else logger.debug("Found " + currentIndicated);
        return currentIndicated;
    }

    public int getSize()
    {
        return getSubTreeSize(root);
    }

    public void printTree()
    {
        for(Object n : this)
        {
            System.out.println(n);
        }
    }

    private int getSubTreeSize(AbstractBstNode n)
    {
        int result = 0;
        if(n != null){
            result = 1;
            result += getSubTreeSize(n.getLeftChild());
            result += getSubTreeSize(n.getRightChild());
        }
        return result;
    }

    private boolean foundPlaceForNode(AbstractBstNode n) {
        return n == null;
    }

    private void addNodeAsLeftChild(AbstractBstNode added, AbstractBstNode parent)
    {
        added.setParent(parent);
        parent.setLeftChild(added);
    }

    private void addNodeAsRightChild(AbstractBstNode added, AbstractBstNode parent)
    {
        added.setParent(parent);
        parent.setRightChild(added);
    }

    private void removeLeaf(AbstractBstNode beingDeleted) {
        AbstractBstNode parent = beingDeleted.getParent();
        if(parent == null)  //deleting root
        {
            root = null;
        }
        else{
            if(beingDeleted.isLeftChildren()) parent.setLeftChild(null);
            else parent.setRightChild(null);
        }
    }

    private void removeNodeWithOnlyChild(AbstractBstNode beingDeleted) {
        AbstractBstNode parent = beingDeleted.getParent();
        AbstractBstNode onlyChild = beingDeleted.getOnlyChild(beingDeleted);

        if(beingDeleted.isLeftChildren()) parent.setLeftChild(onlyChild);
        else parent.setRightChild(onlyChild);

        onlyChild.setParent(beingDeleted.getParent());
    }

    private void removeNodeWith2Children(N beingDeleted) {
        N consequentNode = beingDeleted.getConsequentNode();
        if(isConsequentNodeRightChildOfDeletedNode(beingDeleted,consequentNode))
        {
            connectConsequentWithDeletedParent(consequentNode,beingDeleted);
            consequentNode.setLeftChild(beingDeleted.getLeftChild());
        }
        else
        {
            N deletedParent = beingDeleted.getParent();
            N deletedRightChild = beingDeleted.getRightChild();
            N consequentParent = consequentNode.getParent();
            N consequentRightChild = consequentNode.getRightChild();

            consequentParent.setLeftChild(consequentRightChild);
            if(consequentRightChild != null) consequentRightChild.setParent(consequentParent);

            consequentNode.setParent(deletedParent);
            if(beingDeleted != root)
            {
                if(beingDeleted.isLeftChildren()) deletedParent.setLeftChild(consequentNode);
                else deletedParent.setRightChild(consequentNode);
            }
            else root = consequentNode;

            consequentNode.setRightChild(deletedRightChild);
            deletedRightChild.setParent(consequentNode);

            consequentRightChild.setParent(consequentParent);
            consequentParent.setLeftChild(consequentRightChild);

            consequentNode.setLeftChild(beingDeleted.getLeftChild());
            beingDeleted.getLeftChild().setParent(consequentNode);
        }
    }

    private void connectConsequentWithDeletedParent(AbstractBstNode consequent, AbstractBstNode beingDeleted)
    {
        AbstractBstNode deletedParent = beingDeleted.getParent();
        if(deletedParent != null){
            if(beingDeleted.isLeftChildren()) deletedParent.setLeftChild(consequent);
            else deletedParent.setRightChild(consequent);
        }
        consequent.setParent(deletedParent);
    }

    private void setParentAndChild(AbstractBstNode parent, AbstractBstNode child, boolean isRightChild)
    {
        if(isRightChild) parent.setRightChild(child);
        else parent.setLeftChild(child);
        child.setParent(parent);
    }

    private boolean isConsequentNodeRightChildOfDeletedNode(AbstractBstNode node, AbstractBstNode consequentNode)
    {
        return consequentNode.equals(node.getRightChild());
    }

    private boolean isNodeRoot(AbstractBstNode node)
    {
        return node.equals(root);
    }

    @Override
    public Iterator iterator() {
        logger.debug("Getting iterable " + iteratorType.toString().toLowerCase());
        return new TreeIterator(iteratorType,root);
    }
}
