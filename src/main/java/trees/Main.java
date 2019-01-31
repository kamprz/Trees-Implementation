package trees;

import trees.bst.BstNode;
import trees.bst.BstTree;
import trees.bst.avl.AvlTree;
import trees.iterator.TreeIteratorType;

public class Main {

    public static void main(String[] args) {
        TreeIteratorType treeIteratorType = TreeIteratorType.INORDER;
        BstTree<Integer> tree = new BstTree<>(treeIteratorType);
        tree.addNode(10);
        tree.addNode(20);
        tree.addNode(6);
        tree.addNode(2);
        tree.addNode(7);
        tree.addNode(15);
        tree.addNode(18);

        tree.printTree();

        tree.removeNode(10);

        tree.printTree();

        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.addNode(10);
        avlTree.addNode(20);
        avlTree.addNode(6);
        avlTree.addNode(2);
        avlTree.addNode(7);
        avlTree.addNode(15);
        avlTree.addNode(18);

        System.out.println("Print avl");
        avlTree.printTree();
    }
}
