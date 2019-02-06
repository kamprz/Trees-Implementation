import org.junit.After;
import org.junit.Test;
import trees.bst.avl.AvlNode;
import trees.bst.avl.AvlTree;

import static org.junit.Assert.*;

public class RotationTest
{
    private AvlTree<Integer> tree;

    @After
    public void printTree()
    {

        tree.printTree();
    }

    @Test
    public void testRRRotationWithRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(20);
        tree.addValue(10);
        tree.addNode(rotationNode);
        tree.addValue(30);

        assertEquals(tree.getRoot(), rotationNode);
        assertEquals(rotationNode.getLeftChild(), tree.findNode(10));
        assertEquals(rotationNode.getRightChild(),tree.findNode(30));
    }

    @Test
    public void testRRRotationWithoutRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(15);
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(10);
        tree.addValue(4);
        tree.addNode(rotationNode);
        tree.addValue(20);

        assertEquals(rotationNode.getLeftChild(),tree.findNode(10));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getRightChild(),rotationNode);
    }

    @Test
    public void testLLRotationWithRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(20);
        tree.addValue(30);
        tree.addNode(rotationNode);
        tree.addValue(10);

        assertEquals(tree.getRoot(), rotationNode);
        assertEquals(rotationNode.getLeftChild(), tree.findNode(10));
        assertEquals(rotationNode.getRightChild(),tree.findNode(30));
    }

    @Test
    public void testLLRotationWithoutRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(15);
        tree.addValue(50);
        tree.addValue(25);
        tree.addValue(100);
        tree.addValue(200);
        tree.addNode(rotationNode);
        tree.addValue(5);

        assertEquals(rotationNode.getRightChild(),tree.findNode(25));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getLeftChild(),rotationNode);
    }
    @Test
    public void testLRRotationWithRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(8);
        tree.addValue(10);
        tree.addValue(5);
        tree.addNode(rotationNode);

        assertEquals(tree.getRoot(), rotationNode);
        assertEquals(rotationNode.getLeftChild(), tree.findNode(5));
        assertEquals(rotationNode.getRightChild(),tree.findNode(10));
    }

    @Test
    public void testLRRotationWithoutRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(175);
        tree.addValue(100);
        tree.addValue(50);
        tree.addValue(200);
        tree.addValue(150);
        tree.addNode(rotationNode);

        assertEquals(rotationNode.getLeftChild(),tree.findNode(150));
        assertEquals(rotationNode.getRightChild(),tree.findNode(200));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getRightChild(),rotationNode);
    }
    @Test
    public void testRLRotationWithRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(20);
        tree.addValue(10);
        tree.addValue(30);
        tree.addNode(rotationNode);

        assertEquals(tree.getRoot(), rotationNode);
        assertEquals(rotationNode.getLeftChild(), tree.findNode(10));
        assertEquals(rotationNode.getRightChild(),tree.findNode(30));
    }

    @Test
    public void testRLRotationWithoutRoot()
    {
        tree = new AvlTree<>();
        AvlNode<Integer> rotationNode = new AvlNode<>(250);
        tree.addValue(100);
        tree.addValue(50);
        tree.addValue(200);
        tree.addValue(300);
        tree.addNode(rotationNode);

        assertEquals(rotationNode.getLeftChild(),tree.findNode(200));
        assertEquals(rotationNode.getRightChild(),tree.findNode(300));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getRightChild(),rotationNode);
    }

    @Test
    public void moreComplexAddingTest()
    {
        tree = new AvlTree<>();
        tree.addValue(20);
        tree.addValue(10);
        tree.addValue(25);
        tree.addValue(2);
        tree.addValue(15);
        tree.addValue(22);
        tree.addValue(50);
        tree.addValue(18);
        tree.addValue(30);
        tree.addValue(100);
        tree.addValue(35);
        tree.addValue(150);

        assertEquals(5, tree.getRoot().getSubtreeHeight());
        assertEquals(tree.findNode(30), tree.findNode(50).getParent());
        assertEquals(tree.findNode(25), tree.findNode(30).getLeftChild());
        assertEquals(3, tree.findNode(30).getRightSubtreeHeight());
        assertEquals(tree.findNode(50), tree.findNode(35).getParent());
    }
}
