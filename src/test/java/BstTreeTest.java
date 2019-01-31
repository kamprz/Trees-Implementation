import org.apache.log4j.Logger;
import org.junit.Test;
import trees.bst.BstNode;
import trees.bst.BstTree;
import static org.junit.Assert.*;

public class BstTreeTest {
    private BstTree<Integer> tree;
    private final Logger logger = Logger.getLogger(BstTreeTest.class);

    public void addElements()
    {
        tree = new BstTree<>();
        tree.addNode(10);
        tree.addNode(20);
        tree.addNode(6);
        tree.addNode(2);
        tree.addNode(7);
        tree.addNode(15);
        tree.addNode(18);
    }

    @Test
    public void testFindingElements()
    {
        logger.debug("Testing finding elements.");
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
        logger.debug("Testing adding elements.");
        addElements();
        assertEquals(7, tree.getSize());
        assertEquals(6, tree.findNode(10).getLeftChild().getValue());
    }

    @Test
    public void testRemovingLeaf()
    {
        logger.debug("Testing removing leaf.");
        addElements();
        tree.removeNode(2);
        assertEquals(6,tree.getSize());
        assertNull(tree.findNode(2));
        assertNull(tree.findNode(6).getLeftChild());
    }

    @Test
    public void removingNodeWithOnlyChild()
    {
        logger.debug("Testing removing node with only child.");
        addElements();
        tree.removeNode(20);
        assertNull(tree.findNode(20));
        assertEquals(15, tree.findNode(10).getRightChild().getValue());
    }

    @Test
    public void removeRootWhichIsTheOnlyNode()
    {
        logger.debug("Testing removing root being the only node.");
        tree = new BstTree<>();
        tree.addNode(10);
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
}
