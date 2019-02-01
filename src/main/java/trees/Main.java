package trees;

import trees.bst.avl.AvlNode;
import trees.bst.avl.AvlTree;
import trees.bst.bst.BstNode;
import trees.bst.bst.BstTree;
import trees.iterator.TreeIteratorType;

public class Main {

    public static void main(String[] args) {
        TreeIteratorType treeIteratorType = TreeIteratorType.INORDER;
        AvlTree<Integer> avlTree = new AvlTree(treeIteratorType);

        avlTree.addNode(new AvlNode<>(10));
        avlTree.addNode(new AvlNode<>(20));
        avlTree.printTree();

        BstTree<Integer> bstTree = new BstTree<>(TreeIteratorType.POSTORDER);
        bstTree.addNode(new BstNode<>(10));
        bstTree.addNode(new BstNode<>(20));
        bstTree.printTree();


    }
}
