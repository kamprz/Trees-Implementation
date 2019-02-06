package trees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trees.bst.avl.AvlNode;
import trees.bst.avl.AvlTree;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AvlAddingTest
{
    private AvlTree<Integer> tree;

    @Before
    public void init()
    {
        tree = new AvlTree<>();
    }

    @After
    public void printTree()
    {
        tree.printTree();
    }

    @Test
    public void testRRRotationWithRoot()
    {
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
        tree.addValues(Arrays.asList(5,3,10,4,15,20));
        AvlNode<Integer> rotationNode = tree.findNode(15);

        assertEquals(rotationNode.getLeftChild(),tree.findNode(10));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getRightChild(),rotationNode);
    }

    @Test
    public void testLLRotationWithRoot()
    {
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
        tree.addValues(Arrays.asList(50,25,100,200,15,5));
        AvlNode rotationNode = tree.findNode(15);
        assertEquals(rotationNode.getRightChild(),tree.findNode(25));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getLeftChild(),rotationNode);
    }
    @Test
    public void testLRRotationWithRoot()
    {
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
        tree.addValues(Arrays.asList(100,50,200,150,175));
        AvlNode<Integer> rotationNode = tree.findNode(175);

        assertEquals(rotationNode.getLeftChild(),tree.findNode(150));
        assertEquals(rotationNode.getRightChild(),tree.findNode(200));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getRightChild(),rotationNode);
    }
    @Test
    public void testRLRotationWithRoot()
    {
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
        tree.addValues(Arrays.asList(100,50,200,300,250));
        AvlNode<Integer> rotationNode = tree.findNode(250);
        
        assertEquals(rotationNode.getLeftChild(),tree.findNode(200));
        assertEquals(rotationNode.getRightChild(),tree.findNode(300));
        assertEquals(rotationNode.getParent(), tree.getRoot());
        assertEquals(tree.getRoot().getRightChild(),rotationNode);
    }

    @Test
    public void moreComplexAddingTest()
    {
        tree.addValues(Arrays.asList(20,10,25,2,15,22,50,18,30,100,35,150));
        assertEquals(5, tree.getRoot().getSubtreeHeight());
        assertEquals(tree.findNode(30), tree.findNode(50).getParent());
        assertEquals(tree.findNode(25), tree.findNode(30).getLeftChild());
        assertEquals(3, tree.findNode(30).getRightSubtreeHeight());
        assertEquals(tree.findNode(50), tree.findNode(35).getParent());
    }
}
