package trees.bst.abstr;

import java.util.Objects;

public abstract class AbstractBstNode<T extends Comparable,N extends AbstractBstNode<T,N>> {
    private static int lastId = 0;
    private int id;
    private T value;
    private N parent = null;
    private N leftChild = null;
    private N rightChild = null;

    public AbstractBstNode(T value) {
        this.id = ++lastId;
        this.value = value;
    }

    public boolean isGreaterThan(AbstractBstNode n)
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

    public boolean isLeftChild()
    {
        return equals(getParent().getLeftChild());
    }

    public void setLeftChildAndParent(N child, N parent)
    {
        if(parent != null) parent.setLeftChild(child);
        if(child !=null) child.setParent(parent);
    }

    public void setRightChildAndParent(N child, N parent)
    {
        if(parent != null) parent.setRightChild(child);
        if(child !=null) child.setParent(parent);
    }

    public N getOnlyChild(N node)
    {
        return node.getLeftChild() == null ? node.getRightChild() : node.getLeftChild();
    }

    public N getConsequentNode()
    {
        if(rightChild == null) return null;
        if(rightChild.hasLeftChild())
        {
            N currentIndicated = rightChild;
            while(currentIndicated.getLeftChild() != null) currentIndicated = currentIndicated.getLeftChild();
            return currentIndicated;
        }
        else return rightChild;
    }

    public int compareTo(Object o) {
        if(!(o instanceof AbstractBstNode)) throw new ClassCastException("Przekazano zla klase.");
        AbstractBstNode n = (AbstractBstNode) o;
        return value.compareTo(n.getValue());
    }

    @Override
    public String toString() {
        String par = parent == null? "X" : parent.getValue()+"";
        String left = leftChild == null? "X" : leftChild.getValue()+"";
        String right = rightChild == null? "X" : rightChild.getValue()+"";

        return "Node{" +
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
        AbstractBstNode<T,N> node = (AbstractBstNode<T,N>) o;
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

    public N getParent() {
        return parent;
    }

    public void setParent(N parent) {
        this.parent = parent;
    }

    public N getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(N leftChild) {
        this.leftChild = leftChild;
    }

    public N getRightChild() {
        return rightChild;
    }

    public void setRightChild(N rightChild) {
        this.rightChild = rightChild;
    }
}
