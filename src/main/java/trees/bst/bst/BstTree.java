package trees.bst.bst;

import org.apache.log4j.Logger;
import trees.bst.abstr.AbstractBstTree;
import trees.iterator.TreeIteratorType;

public class BstTree<T extends Comparable> extends AbstractBstTree<T, BstNode<T>> {
    private BstNode<T> root;
    private final Logger logger = Logger.getLogger(BstTree.class);

    public BstTree(TreeIteratorType iteratorType)
    {
        super(iteratorType);
    }

    public BstTree() {

    }

    @Override
    public void addValue(T value) {
        addNode(new BstNode<>(value));
    }
}
