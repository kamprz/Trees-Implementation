package trees.bst.avl;

import org.apache.log4j.Logger;
import trees.bst.BstTree;
import trees.iterator.TreeIteratorType;

public class AvlTree<T extends Comparable> extends BstTree {
    private AvlNode<T> root;
    private final Logger logger = Logger.getLogger(AvlTree.class);

    public AvlTree(TreeIteratorType iteratorType)
    {
        super(iteratorType);
    }

    public AvlTree() {
    }
}
