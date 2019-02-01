package trees.bst.bst;

import trees.bst.abstr.AbstractBstNode;

public class BstNode<T extends Comparable> extends AbstractBstNode<T, BstNode<T>> {
    public BstNode(T value) {
        super(value);
    }

}
