package trees.bst.avl;

import trees.bst.abstr.AbstractBstNode;

public class AvlNode<T extends Comparable> extends AbstractBstNode<T,AvlNode<T>> {
    private int balanceFactor = 0;
    private int leftSubtreeHeight = 0;
    private int rightSubtreeHeight = 0;

    public AvlNode(T value) {
        super(value);
    }

    int getBalanceFactor() {
        return balanceFactor;
    }

    public int getSubtreeHeight()
    {
        if(leftSubtreeHeight > rightSubtreeHeight) return leftSubtreeHeight + 1;
        else return rightSubtreeHeight + 1;
    }
   void setLeftSubtreeHeight(int leftSubtreeHeight) {
        this.leftSubtreeHeight = leftSubtreeHeight;
        setBalanceFactor();
    }
    void setRightSubtreeHeight(int rightSubtreeHeight) {
        this.rightSubtreeHeight = rightSubtreeHeight;
        setBalanceFactor();
    }

    private void setBalanceFactor()
    {
        balanceFactor = getRightSubtreeHeight() - getLeftSubtreeHeight();
    }

    int getLeftSubtreeHeight() {
        return leftSubtreeHeight;
    }

    public int getRightSubtreeHeight() {
        return rightSubtreeHeight;
    }

    @Override
    public String toString()
    {
        String result = super.toString();
        result.replace("}","" );
        result += " bf = " + balanceFactor
                + " lSubtree = " + leftSubtreeHeight
                + " rSubtree = " + rightSubtreeHeight
                + "}";
        return result;
    }
}
