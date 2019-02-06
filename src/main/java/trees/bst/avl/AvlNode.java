package trees.bst.avl;

import trees.bst.abstr.AbstractBstNode;

public class AvlNode<T extends Comparable> extends AbstractBstNode<T,AvlNode<T>> {
    private int balanceFactor = 0;
    private int leftSubtreeHeight = 0;
    private int rightSubtreeHeight = 0;

    public AvlNode(T value) {
        super(value);
    }

    public boolean isLeftSubtreeHigherThanRight()
    {
        return leftSubtreeHeight > rightSubtreeHeight;
    }

    public boolean areSubtreesEquallyHigh()
    {
        return leftSubtreeHeight == rightSubtreeHeight;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public int getSubtreeHeight()
    {
        if(leftSubtreeHeight > rightSubtreeHeight) return leftSubtreeHeight + 1;
        else return rightSubtreeHeight + 1;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public void setLeftSubtreeHeight(int leftSubtreeHeight) {
        this.leftSubtreeHeight = leftSubtreeHeight;
        setBalanceFactor();
    }
    public void setRightSubtreeHeight(int rightSubtreeHeight) {
        this.rightSubtreeHeight = rightSubtreeHeight;
        setBalanceFactor();
    }

    public void decrementLeftSubtree(){
        leftSubtreeHeight--;
        setBalanceFactor();
    }

    public void incrementLeftSubtree(){
        leftSubtreeHeight++;
        setBalanceFactor();
    }

    public void decrementRightSubtree(){
        rightSubtreeHeight--;
        setBalanceFactor();
    }

    public void incrementRightSubtree(){
        rightSubtreeHeight++;
        setBalanceFactor();
    }

    private void setBalanceFactor()
    {
        balanceFactor = getRightSubtreeHeight() - getLeftSubtreeHeight();
    }

    public int getLeftSubtreeHeight() {
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
