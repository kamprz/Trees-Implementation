package trees.bst.avl;

import org.apache.log4j.Logger;
import trees.bst.SelfBalancingTree;
import trees.bst.abstr.AbstractBstTree;
import trees.iterator.TreeIteratorType;

public class AvlTree<T extends Comparable> extends AbstractBstTree<T,AvlNode<T>> implements SelfBalancingTree<T,AvlNode<T>> {
    private final Logger logger = Logger.getLogger(AvlTree.class);

    public AvlTree(TreeIteratorType iteratorType)
    {
        super(iteratorType);
    }

    @Override
    public void addValue(T value) {
        addNode(new AvlNode<>(value));
    }

    @Override
    public void addNode(AvlNode<T> node)
    {
        super.addNode(node);
        if(!node.equals(root))
        {
            balance(node);
        }
    }

    public AvlTree() {

    }

    @Override
    public void balance(AvlNode<T> node) {
        if(node!=null){
            if(node.getLeftChild() == null) node.setLeftSubtreeHeight(0);
            else node.setLeftSubtreeHeight(node.getLeftChild().getSubtreeHeight());
            if(node.getRightChild() == null) node.setRightSubtreeHeight(0);
            else node.setRightSubtreeHeight(node.getRightChild().getSubtreeHeight());
            performRotationIfNeeded(node);
            balance(node.getParent());
        }
    }

    private void performRotationIfNeeded(AvlNode<T> node)
    {
        if(Math.abs(node.getBalanceFactor()) == 2)
        {
            AvlNode<T> nodeToRotate = pickNodeToRotate(node);
            logger.debug("Picked " + nodeToRotate + " to rotate.");
            rotate(nodeToRotate);
        }
    }

    private AvlNode<T> pickNodeToRotate(AvlNode<T> notBalancedNode)
    {
        if(notBalancedNode.getBalanceFactor() < 0)
        {
            if(notBalancedNode.getBalanceFactor() * notBalancedNode.getLeftChild().getBalanceFactor() >= 0) return notBalancedNode.getLeftChild();
            else return notBalancedNode.getLeftChild().getRightChild();
        }
        else if(notBalancedNode.getBalanceFactor() * notBalancedNode.getRightChild().getBalanceFactor() >= 0) return notBalancedNode.getRightChild();
        else return notBalancedNode.getRightChild().getLeftChild();
    }

    //param node is going up during rotation
    public void rotate(AvlNode<T> node)
    {
        AvlNode<T> parent = node.getParent();
        AvlNode<T> grandpa = parent.getParent();

        logger.debug("ROTATION:\nRotating " + node+".\nParent = " + parent+"\nGrandparent = " + grandpa + "\nRoot = " + root);
        if(node.isLeftChild() && parent.getBalanceFactor() == -2) {
            logger.debug("L rotation");
            rotationL(node,parent);
        }
        else if(node.isLeftChild()) {
            logger.debug("RL rotation");
            rotationRL(node,parent,grandpa);
        }
        else if(!node.isLeftChild() && parent.getBalanceFactor() == 2) {
            logger.debug("R rotation");
            rotationR(node,parent);
        }
        else{
            logger.debug("LR rotation");
            rotationLR(node,parent,grandpa);
        }
    }

    private void rotationR(AvlNode<T> child, AvlNode<T> parent)
    {
        logger.debug("Rotate R " + child.getValue() + " over " + parent.getValue());
        AvlNode<T> childLeft = child.getLeftChild();
        AvlNode<T> grandpa = parent.getParent();
        boolean isParentGrandpasLeftChild = false;
        if(grandpa!= null) isParentGrandpasLeftChild = parent.isLeftChild();

        int childLeftSubtreeHeight = childLeft == null ? 0 : childLeft.getSubtreeHeight();
        parent.setRightChild(childLeft);
        parent.setRightSubtreeHeight(childLeftSubtreeHeight);
        if(childLeft != null) childLeft.setParent(parent);

        child.setLeftChild(parent);
        child.setLeftSubtreeHeight(parent.getSubtreeHeight());
        parent.setParent(child);

        if(grandpa != null) {
            if (isParentGrandpasLeftChild) {
                grandpa.setLeftChild(child);
                grandpa.setLeftSubtreeHeight(child.getSubtreeHeight());
            } else {
                grandpa.setRightChild(child);
                grandpa.setRightSubtreeHeight(child.getSubtreeHeight());
            }
        }
        else root = child;
        child.setParent(grandpa);

        logger.debug("After R rotation: ");
        printTree();
    }

    private void rotationL(AvlNode<T> child, AvlNode<T> parent)
    {
        logger.debug("Rotate L " + child.getValue() + " over " + parent.getValue());
        AvlNode<T> childRight = child.getRightChild();
        AvlNode<T> grandpa = parent.getParent();
        boolean isParentGrandpasLeftChild = false;
        if(grandpa!= null) isParentGrandpasLeftChild = parent.isLeftChild();

        int childRightSubtreeHight = childRight == null ? 0 : childRight.getSubtreeHeight();
        parent.setLeftChild(childRight);
        parent.setLeftSubtreeHeight(childRightSubtreeHight);
        if(childRight != null) childRight.setParent(parent);

        child.setRightChild(parent);
        child.setRightSubtreeHeight(parent.getSubtreeHeight());
        parent.setParent(child);

        if(grandpa != null) {
            if(isParentGrandpasLeftChild) {
                grandpa.setLeftChild(child);
                grandpa.setLeftSubtreeHeight(child.getSubtreeHeight());
            }
            else {
                grandpa.setRightChild(child);
                grandpa.setRightSubtreeHeight(child.getSubtreeHeight());
            }
        }
        else {
            logger.debug("Changing root from " + root + " to " + child);
            root = child;
        }
        child.setParent(grandpa);

        logger.debug("After L rotation: ");
        printTree();
    }

    private void rotationRL(AvlNode<T> child, AvlNode<T> parent, AvlNode<T> grandpa)
    {
        rotationL(child,parent);
        rotationR(child,grandpa);
    }

    private void rotationLR(AvlNode<T> child, AvlNode<T> parent, AvlNode<T> grandpa)
    {
        rotationR(child,parent);
        rotationL(child,grandpa);
    }

    public AvlNode<T> removeNode(T value)
    {
        AvlNode<T> nodeOverDeleted = super.removeNode(value);
        if(nodeOverDeleted != null)
        {
            setSubtreeBalanceFactors(nodeOverDeleted);
            performRotationIfNeeded(nodeOverDeleted);
            balance(nodeOverDeleted.getParent());
        }
        return null;
    }

    private void setSubtreeBalanceFactors(AvlNode<T> node)
    {
        if(node.hasLeftChild()){
            setSubtreeBalanceFactors(node.getLeftChild());
            node.setLeftSubtreeHeight(node.getLeftChild().getSubtreeHeight());
        }
        else node.setLeftSubtreeHeight(0);
        if(node.hasRightChild()){
            setSubtreeBalanceFactors(node.getRightChild());
            node.setRightSubtreeHeight(node.getRightChild().getSubtreeHeight());
        }
        else node.setRightSubtreeHeight(0);
    }
}
