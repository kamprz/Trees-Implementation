package trees.bst.avl;

import trees.bst.BstNode;

public class AvlNode<T extends Comparable> extends BstNode {
    private int balanceFactor;

    public AvlNode(Comparable value) {
        super(value);
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }
}
