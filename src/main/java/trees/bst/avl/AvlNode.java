package trees.bst.avl;

import trees.bst.AbstractBstNode;

public class AvlNode<T extends Comparable> extends AbstractBstNode<T,AvlNode<T>> {
    private int balanceFactor;

    public AvlNode(T value) {
        super(value);
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }
}
