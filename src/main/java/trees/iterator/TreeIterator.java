package trees.iterator;

import org.apache.log4j.Logger;
import trees.bst.BstNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeIterator implements Iterator<BstNode<? extends Comparable>>
{
    private static final Logger logger = Logger.getLogger(TreeIterator.class);
    private List<BstNode<?>> iterable;
    private TreeIteratorType iteratorType = TreeIteratorType.PREORDER;
    private BstNode root;
    private int iteratorPointer = 0;

    public TreeIterator(BstNode root) {
        this.root = root;
        getIterator();
    }

    public TreeIterator(TreeIteratorType iteratorType, BstNode root) {
        this.iteratorType = iteratorType;
        this.root = root;
        getIterator();
    }

    private void getIterator()
    {
        iterable = new LinkedList<>();
        if(root != null) {
            if (iteratorType == TreeIteratorType.PREORDER) getPreOrderIterable(root);
            if (iteratorType == TreeIteratorType.INORDER) getInOrderIterable(root);
            if (iteratorType == TreeIteratorType.POSTORDER) getPostOrderIterable(root);
        }
    }

    private void getPreOrderIterable(BstNode n) {
        addNode(n);
        if(n.hasLeftChild()) getPreOrderIterable(n.getLeftChild());
        if(n.hasRightChild()) getPreOrderIterable(n.getRightChild());
    }

    private void getInOrderIterable(BstNode n)
    {
        if(n.hasLeftChild()) getInOrderIterable(n.getLeftChild());
        addNode(n);
        if(n.hasRightChild()) getInOrderIterable(n.getRightChild());
    }

    private void getPostOrderIterable(BstNode n) {
        if(n.hasLeftChild()) getPostOrderIterable(n.getLeftChild());
        if(n.hasRightChild()) getPostOrderIterable(n.getRightChild());
        addNode(n);
    }

    private void addNode(BstNode n)
    {
        //logger.debug("Adding " + n + " to iterator.");
        iterable.add(n);
    }

    @Override
    public boolean hasNext() {
        try{
            iterable.get(iteratorPointer);
            return true;
        }
        catch(IndexOutOfBoundsException e)
        {
            return false;
        }
    }

    @Override
    public BstNode<? extends Comparable> next() {
        return iterable.get(iteratorPointer++);
    }
}
