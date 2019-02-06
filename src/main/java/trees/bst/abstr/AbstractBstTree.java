package trees.bst.abstr;

import org.apache.log4j.Logger;
import trees.iterator.TreeIteratorType;
import trees.iterator.TreeIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractBstTree<T extends Comparable,N extends AbstractBstNode<T,N>> implements Iterable{
    protected N root;
    private final Logger logger = Logger.getLogger(AbstractBstTree.class);
    private TreeIteratorType iteratorType = TreeIteratorType.INORDER;

    public AbstractBstTree() { }

    public AbstractBstTree(TreeIteratorType iteratorType) {
        this.iteratorType = iteratorType;
    }


    public abstract void addValue(T value);

    public void addValues(List<T> valuesList)
    {
        for(T value : valuesList) addValue(value);
    }

    @Override
    public Iterator iterator() {
        logger.debug("Getting iterable " + iteratorType.toString().toLowerCase());
        return new TreeIterator(iteratorType,root);
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

    public void printTreeBfs()
    {
        System.out.println("Printing tree with breadth-first search:");
        if(root != null)
        {
            Queue<N> queue = new LinkedList<>();
            queue.add(root);
            getNodeToBfsQueue(queue);
        }
        else System.out.println("Tree is empty.");
    }

    private void getNodeToBfsQueue(Queue<N> queue)
    {
        N node = queue.remove();
        System.out.println(node);
        if(node.hasLeftChild()) queue.add(node.getLeftChild());
        if(node.hasRightChild()) queue.add(node.getRightChild());
        if(!queue.isEmpty()) getNodeToBfsQueue(queue);
    }

    public N getRoot()
    {
        return root;
    }

    protected void setRoot(N node)
    {
        if(node != null) node.setParent(null);
        root = node;
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
        if(isNodeRoot(beingDeleted)) root = null;
        else{
            N parent = beingDeleted.getParent();
            replaceNodeOnSecondNodeAsItsParentChild(null,beingDeleted);
            return parent;
        }
        return null;
    }

    private N removeNodeWithOnlyChild(N beingDeleted) {
        N onlyChild = beingDeleted.getOnlyChild(beingDeleted);
        replaceNodeOnSecondNodeAsItsParentChild(onlyChild,beingDeleted);
        return onlyChild;
    }

    private N removeNodeWith2Children(N beingDeleted) {
        N consequentNode = beingDeleted.getConsequentNode();
        if(isConsequentNodeRightChildOfDeletedNode(beingDeleted,consequentNode))
        {
            replaceNodeOnSecondNodeAsItsParentChild(consequentNode,beingDeleted);
            consequentNode.setLeftChild(beingDeleted.getLeftChild());
            return consequentNode;
        }
        else
        {
            N deletedRightChild = beingDeleted.getRightChild();
            N consequentParent = consequentNode.getParent();
            N consequentRightChild = consequentNode.getRightChild();

            if(isNodeRoot(beingDeleted)) setRoot(consequentNode);
            else replaceNodeOnSecondNodeAsItsParentChild(consequentNode,beingDeleted);
            consequentParent.setLeftChild(consequentRightChild);
            consequentNode.setRightChild(deletedRightChild);
            consequentParent.setLeftChild(consequentRightChild);
            consequentNode.setLeftChild(beingDeleted.getLeftChild());
            return consequentParent;
        }
    }

    private void replaceNodeOnSecondNodeAsItsParentChild(N node, N secondNode)
    {
        if(!isNodeRoot(secondNode))
        {
            N secondParent = secondNode.getParent();
            if(secondNode.isLeftChild()) secondParent.setLeftChild(node);
            else secondParent.setRightChild(node);
        }
        else setRoot(node);
    }

    private boolean isConsequentNodeRightChildOfDeletedNode(AbstractBstNode node, AbstractBstNode consequentNode)
    {
        return consequentNode.equals(node.getRightChild());
    }

    private boolean isNodeRoot(N node)
    {
        return node.equals(root);
    }
}