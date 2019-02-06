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
/*
        avlTree.addValue(10);
        avlTree.addValue(5);
        avlTree.addValue(1);
        avlTree.addValue(20);
        avlTree.addValue(15);
        avlTree.addValue(40);
        avlTree.addValue(50);
        avlTree.addValue(60);
        avlTree.printTree();
        */
/*
        avlTree.addValue(20);
        avlTree.addValue(10);
        avlTree.addValue(25);
        avlTree.addValue(2);
        avlTree.addValue(15);
        avlTree.addValue(22);
        avlTree.addValue(50);
        avlTree.addValue(18);
        avlTree.addValue(30);
        avlTree.addValue(100);
        avlTree.addValue(35);
        avlTree.addValue(150);*/

        avlTree.addValue(100);
        avlTree.addValue(110);
        avlTree.addValue(50);
        avlTree.addValue(55);
        avlTree.addValue(105);
        avlTree.addValue(60);
        avlTree.printTree();
    }
}
