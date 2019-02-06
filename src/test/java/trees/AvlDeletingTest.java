package trees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trees.bst.avl.AvlTree;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AvlDeletingTest {
    AvlTree<Integer> tree;

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
    public void deletingOnEmptyTree()
    {
        tree.removeNode(10);
    }

    @Test
    public void deletingRoot()
    {
        tree.addValue(10);
        tree.removeNode(10);
        assertEquals(0, tree.getSize());
    }

    @Test
    public void removeWithLLRotation()
    {
        List<Integer> values = Arrays.asList(100,50,200,10);
        tree.addValues(values);
        tree.removeNode(200);
        assertEquals(tree.getRoot(), tree.findNode(50));
        assertEquals(2,tree.getTreeHeight());
    }

    @Test
    public void removeWithRRRotation()
    {
        List<Integer> values = Arrays.asList(100,10,200,300);
        tree.addValues(values);
        tree.removeNode(10);
        assertEquals(tree.getRoot(), tree.findNode(200));
        assertEquals(2,tree.getTreeHeight());
    }

    @Test
    public void removeWith2ChildrenAndRotationAfterRemovingRoot()
    {
        List<Integer> values = Arrays.asList(100,50,200,75,150,300,400);
        tree.addValues(values);
        tree.removeNode(100);
        assertEquals(tree.getRoot(),tree.findNode(150));
        assertEquals(tree.getRoot().getRightChild(), tree.findNode(300));
    }

    @Test
    public void removeRootWithTwoConsequentRotations()
    {
        List<Integer> values = Arrays.asList(50,100,25,15,40,70,110,10,20,30,45,60,75,105,120,5,12,47,80,107,115,130,140);
        tree.addValues(values);
        tree.removeNode(50);

        assertEquals(tree.getRoot(), tree.findNode(60));
        assertEquals(tree.getRoot().getRightChild(),tree.findNode(110));
        assertEquals(tree.findNode(110).getLeftChild(),tree.findNode(100));
        assertEquals(tree.findNode(100).getLeftChild(),tree.findNode(75));
        assertEquals(tree.findNode(75).getLeftChild(),tree.findNode(70));
        assertEquals(tree.findNode(100).getRightChild(),tree.findNode(105));
    }

    @Test
    public void removeRootWithThreeConsequentRotations() {
        List<Integer> values = Arrays.asList(50,100,25,15,40,70,110,10,20,30,45,60,75,
                105,120, 5,12,47,80,107,115,130,140,22,35,1,28,27,33,37,42,48,38);
        tree.addValues(values);
        tree.removeNode(50);

        assertEquals(tree.getRoot(), tree.findNode(40));
        assertEquals(tree.findNode(40).getLeftChild(),tree.findNode(25));
        assertEquals(tree.findNode(25).getRightChild(),tree.findNode(30));
        assertEquals(tree.findNode(40).getRightChild(),tree.findNode(60));
        assertEquals(tree.findNode(60).getRightChild(),tree.findNode(110));
        assertEquals(tree.findNode(110).getLeftChild(),tree.findNode(100));
        assertEquals(tree.findNode(100).getLeftChild(),tree.findNode(75));
        assertEquals(tree.findNode(75).getLeftChild(),tree.findNode(70));
        assertEquals(tree.findNode(100).getRightChild(),tree.findNode(105));
    }
}
