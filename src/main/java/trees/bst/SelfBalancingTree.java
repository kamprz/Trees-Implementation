package trees.bst;


import trees.bst.abstr.AbstractBstNode;

public interface SelfBalancingTree<T extends Comparable,N extends AbstractBstNode<T,N>> {
    void balance(N node);
}
