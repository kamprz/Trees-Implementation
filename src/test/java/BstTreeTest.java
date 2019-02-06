import org.apache.log4j.Logger;
import org.junit.Test;
import trees.bst.bst.BstTree;

import static org.junit.Assert.*;

public class BstTreeTest {

    private BstTree<Integer> tree;

    public void addElements()
    {
        tree = new BstTree<>();
        tree.addValue(10);
        tree.addValue(20);
        tree.addValue(6);
        tree.addValue(2);
        tree.addValue(7);
        tree.addValue(15);
        tree.addValue(18);
    }

    @Test
    public void testFindingElements()
    {
        addElements();
        assertNotNull(tree.findNode(2));
        assertNotNull(tree.findNode(6));
        assertNotNull(tree.findNode(7));
        assertNotNull(tree.findNode(10));
        assertNotNull(tree.findNode(15));
        assertNotNull(tree.findNode(20));
        assertNull(tree.findNode(123));
        assertNull(tree.findNode(0));
    }

    @Test
    public void testAddingElements()
    {
        addElements();
        assertEquals(7, tree.getSize());
        assertEquals(6, tree.findNode(10).getLeftChild().getValue());
    }

    @Test
    public void testRemovingLeaf()
    {
        addElements();
        tree.removeNode(2);
        assertEquals(6,tree.getSize());
        assertNull(tree.findNode(2));
        assertNull(tree.findNode(6).getLeftChild());
    }

    @Test
    public void removingNodeWithOnlyChild()
    {
        addElements();
        tree.removeNode(20);
        assertNull(tree.findNode(20));
        assertEquals(15, tree.findNode(10).getRightChild().getValue());
    }

    @Test
    public void removeRootWhichIsTheOnlyNode()
    {
        tree = new BstTree<>();
        tree.addValue(10);
        tree.removeNode(10);
        assertTrue(tree.isEmpty());
    }

    @Test
    public void removeNodeWith2ChildrenAndConsequentBeingRightChild()
    {
        addElements();
        tree.removeNode(6);
        assertEquals(6, tree.getSize());
        assertEquals(tree.findNode(10).getLeftChild(), tree.findNode(7));
        assertEquals(2, tree.findNode(7).getLeftChild().getValue());
    }

    @Test
    public void removeNodeWith2ChildrenAndConsequentNotBeingDeletedRightChildAndHavingOwnRightChild()
    {
        addElements();
        tree.removeNode(10);
        assertNull(tree.findNode(15).getParent()); //15 is root
        assertEquals(tree.findNode(15).getRightChild(), tree.findNode(20));
        assertEquals(tree.findNode(20).getLeftChild(), tree.findNode(18));
    }

    @Test
    public void removeValueWhichIsNotInTree()
    {
        addElements();
        tree.removeNode(1000);
    }
}
