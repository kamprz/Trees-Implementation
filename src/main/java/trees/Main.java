package trees;

import trees.bst.avl.AvlNode;
import trees.bst.avl.AvlTree;
import trees.iterator.TreeIteratorType;

public class Main {

    public static void main(String[] args) {
        TreeIteratorType treeIteratorType = TreeIteratorType.INORDER;
        AvlTree<Integer> tree = new AvlTree(treeIteratorType);
        //AvlTree<Integer> tree = new AvlTree<>();
        tree.addNode(new AvlNode<>(10));
        tree.addNode(new AvlNode<>(20));

        tree.printTree();

    }
}
