package trees.bst.abstr;

import org.apache.log4j.Logger;
import trees.iterator.TreeIteratorType;
import trees.iterator.TreeIterator;

import java.util.Iterator;

public abstract class AbstractBstTree<T extends Comparable,N extends AbstractBstNode<T,N>> implements Iterable{
    protected N root;
    private final Logger logger = Logger.getLogger(AbstractBstTree.class);
    private TreeIteratorType iteratorType = TreeIteratorType.INORDER;

    public AbstractBstTree() {
    }

    public AbstractBstTree(TreeIteratorType iteratorType) {
        this.iteratorType = iteratorType;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public abstract void addValue(T value);

    public void addNode(N node)
    {
        if(root == null) root = node;
        else
        {
            N currentIndicated = root;
            N parent = null;
            boolean wentLeft = false;
            while(!foundPlaceForNode(currentIndicated))
            {
                if(node.compareTo(currentIndicated) == 0) {
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
        logger.debug("Adding " + node);
    }

    public N removeNode(T value)
    {
        N beingDeleted = findNode(value);
        if(beingDeleted == null)
        {
            logger.debug("Cant delete element. It doesnt exist.");
            return null;
        }
        else{
            if(beingDeleted.isLeaf())
            {
                logger.debug("Deleting " + beingDeleted + " which is a leaf.");
                return removeLeaf(beingDeleted);
            }
            else if(beingDeleted.hasOneChild())
            {
                logger.debug("Deleting " + beingDeleted + " which has only 1 child.");
                return removeNodeWithOnlyChild(beingDeleted);
            }
            else
            {
                logger.debug("Deleting " + beingDeleted + " which has 2 children.");
                return removeNodeWith2Children(beingDeleted);
            }
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

    public int getTreeHeight()
    {
        return getSubtreeHeight(root);
    }

    public void printTree()
    {
        System.out.println("Root: " + root);
        for(Object n : this)
        {
            System.out.println(n);
        }
    }

    public N getRoot()
    {
        return root;
    }

    protected static void setLeftChildAndParent(AbstractBstNode child, AbstractBstNode parent)
    {
        if(parent != null) parent.setLeftChild(child);
        if(child !=null) child.setParent(parent);
    }

    protected static void setRightChildAndParent(AbstractBstNode child, AbstractBstNode parent)
    {
        if(parent != null) parent.setRightChild(child);
        if(child !=null) child.setParent(parent);
    }

    private int getSubTreeSize(AbstractBstNode node)
    {
        int result = 0;
        if(node != null){
            result = 1;
            result += getSubTreeSize(node.getLeftChild());
            result += getSubTreeSize(node.getRightChild());
        }
        return result;
    }

    private int getSubtreeHeight(N node) {
        int result = 0;
        if(node != null){
            int leftSubtree = getSubtreeHeight(node.getLeftChild());
            int rightSubtree = getSubtreeHeight(node.getRightChild());
            result = Math.max(leftSubtree,rightSubtree) + 1;
            logger.debug("Subtree height of node " + node + " = " + result);
        }
        return result;
    }

    private boolean foundPlaceForNode(AbstractBstNode n) {
        return n == null;
    }

    private void addNodeAsLeftChild(N added, N parent)
    {
        added.setParent(parent);
        parent.setLeftChild(added);
    }

    private void addNodeAsRightChild(N added, N parent)
    {
        added.setParent(parent);
        parent.setRightChild(added);
    }

    private N removeLeaf(N beingDeleted) {
        N parent = beingDeleted.getParent();
        if(parent == null)  //deleting root
        {
            root = null;
        }
        else{
            if(beingDeleted.isLeftChild()) parent.setLeftChild(null);
            else parent.setRightChild(null);
        }
        return parent;
    }

    private N removeNodeWithOnlyChild(N beingDeleted) {
        N parent = beingDeleted.getParent();
        N onlyChild = beingDeleted.getOnlyChild(beingDeleted);

        if(beingDeleted.isLeftChild()) parent.setLeftChild(onlyChild);
        else parent.setRightChild(onlyChild);

        onlyChild.setParent(beingDeleted.getParent());
        return onlyChild;
    }

    private N removeNodeWith2Children(N beingDeleted) {
        N consequentNode = beingDeleted.getConsequentNode();
        if(isConsequentNodeRightChildOfDeletedNode(beingDeleted,consequentNode))
        {
            connectConsequentWithDeletedParent(consequentNode,beingDeleted);
            consequentNode.setLeftChild(beingDeleted.getLeftChild());
            return consequentNode;
        }
        else
        {
            N deletedParent = beingDeleted.getParent();
            N deletedRightChild = beingDeleted.getRightChild();
            N consequentParent = consequentNode.getParent();
            N consequentRightChild = consequentNode.getRightChild();

            setLeftChildAndParent(consequentRightChild,consequentParent);

            consequentNode.setParent(deletedParent);
            if(beingDeleted != root)
            {
                if(beingDeleted.isLeftChild()) deletedParent.setLeftChild(consequentNode);
                else deletedParent.setRightChild(consequentNode);
            }
            else root = consequentNode;

            setRightChildAndParent(deletedRightChild,consequentNode);

            setLeftChildAndParent(consequentRightChild,consequentParent);

            setLeftChildAndParent(beingDeleted.getLeftChild(),consequentNode);
            return consequentParent;
        }
    }

    private void connectConsequentWithDeletedParent(N consequent, N beingDeleted)
    {
        N deletedParent = beingDeleted.getParent();
        if(deletedParent != null){
            if(beingDeleted.isLeftChild()) deletedParent.setLeftChild(consequent);
            else deletedParent.setRightChild(consequent);
        }
        consequent.setParent(deletedParent);
    }

    private void setParentAndChild(N parent, N child, boolean isRightChild)
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
