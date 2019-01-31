package trees.bst;

import java.util.Objects;

public class BstNode<T extends Comparable> {
    private static int lastId = 0;
    private int id;
    private T value;
    private BstNode parent = null;
    private BstNode leftChild = null;
    private BstNode rightChild = null;

    public BstNode(T value) {
        this.id = ++lastId;
        this.value = value;
    }

    public boolean isGreaterThan(BstNode n)
    {
        return compareTo(n) > 0;
    }

    public boolean hasLeftChild()
    {
        return !(leftChild == null);
    }

    public boolean hasRightChild()
    {
        return !(rightChild == null);
    }

    public boolean hasParent()
    {
        return !(parent == null);
    }

    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }

    public boolean hasOneChild() {
        return getLeftChild() == null ^ getRightChild() == null;
    }

    public boolean isLeftChildren()
    {
        return getValue().compareTo(getParent().getLeftChild().getValue()) == 0;
    }

    public BstNode getOnlyChild(BstNode node)
    {
        return node.getLeftChild() == null ? node.getRightChild() : node.getLeftChild();
    }

    public BstNode getConsequentNode()
    {
        if(rightChild == null) return null;
        if(rightChild.hasLeftChild())
        {
            BstNode currentIndicated = rightChild;
            while(currentIndicated.leftChild != null) currentIndicated = currentIndicated.leftChild;
            return currentIndicated;
        }
        else return rightChild;
    }

    private int compareTo(Object o) {
        if(!(o instanceof BstNode)) throw new ClassCastException("Przekazano zla klase.");
        BstNode n = (BstNode) o;
        return value.compareTo(n.getValue());
    }

    @Override
    public String toString() {
        String par = parent == null? "X" : parent.getValue()+"";
        String left = leftChild == null? "X" : leftChild.getValue()+"";
        String right = rightChild == null? "X" : rightChild.getValue()+"";

        return "BstNode{" +
                "value=" + value +
                ", parent=" + par +
                ", leftChild=" + left +
                ", rightChild=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BstNode<?> node = (BstNode<?>) o;
        return id == node.id &&
                Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comparable getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BstNode getParent() {
        return parent;
    }

    public void setParent(BstNode parent) {
        this.parent = parent;
    }

    public BstNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BstNode leftChild) {
        this.leftChild = leftChild;
    }

    public BstNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BstNode rightChild) {
        this.rightChild = rightChild;
    }
}
