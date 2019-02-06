import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trees.bst.avl.AvlTree;

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
        tree.addValue(100);
        tree.addValue(50);
        tree.addValue(200);
        tree.addValue(10);
        tree.removeNode(200);
        assertEquals(tree.getRoot(), tree.findNode(50));
        assertEquals(2,tree.getTreeHeight());
    }

    @Test
    public void removeWithRRRotation()
    {
        tree.addValue(100);
        tree.addValue(10);
        tree.addValue(200);
        tree.addValue(300);
        tree.removeNode(10);
        assertEquals(tree.getRoot(), tree.findNode(200));
        assertEquals(2,tree.getTreeHeight());
    }

    @Test
    public void removeWith2ChildrenAndRotationAfterRemovingRoot()
    {
        tree.addValue(100);
        tree.addValue(50);
        tree.addValue(200);
        tree.addValue(75);
        tree.addValue(150);
        tree.addValue(300);
        tree.addValue(400);
        tree.removeNode(100);
        assertEquals(tree.getRoot(),tree.findNode(150));
        assertEquals(tree.getRoot().getRightChild(), tree.findNode(300));
    }

    @Test
    public void removeRootWithTwoConsequentRotations()
    {
        tree.addValue(50);
        tree.addValue(100);
        tree.addValue(25);
        tree.addValue(15);
        tree.addValue(40);
        tree.addValue(70);
        tree.addValue(110);
        tree.addValue(10);
        tree.addValue(20);
        tree.addValue(30);
        tree.addValue(45);
        tree.addValue(60);
        tree.addValue(75);
        tree.addValue(105);
        tree.addValue(120);
        tree.addValue(5);
        tree.addValue(12);
        tree.addValue(47);
        tree.addValue(80);
        tree.addValue(107);
        tree.addValue(115);
        tree.addValue(130);
        tree.addValue(140);
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
        tree.addValue(50);
        tree.addValue(100);
        tree.addValue(25);
        tree.addValue(15);
        tree.addValue(40);
        tree.addValue(70);
        tree.addValue(110);
        tree.addValue(10);
        tree.addValue(20);
        tree.addValue(30);
        tree.addValue(45);
        tree.addValue(60);
        tree.addValue(75);
        tree.addValue(105);
        tree.addValue(120);
        tree.addValue(5);
        tree.addValue(12);
        tree.addValue(47);
        tree.addValue(80);
        tree.addValue(107);
        tree.addValue(115);
        tree.addValue(130);
        tree.addValue(140);
        tree.addValue(22);
        tree.addValue(35);
        tree.addValue(1);
        tree.addValue(28);
        tree.addValue(27);
        tree.addValue(33);
        tree.addValue(37);
        tree.addValue(42);
        tree.addValue(48);
        tree.addValue(38);
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
