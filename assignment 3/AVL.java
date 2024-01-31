package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-03-13
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {
    	return (node.getLeft() != null ? node.getLeft().getHeight() : 0) - (node.getRight() != null ? node.getRight().getHeight() : 0);
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
    	TreeNode<T> pivot = node.getRight();
        node.setRight(pivot.getLeft());
        pivot.setLeft(node);
        node.updateHeight();
        pivot.updateHeight();
        return pivot;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {
    	TreeNode<T> pivot = node.getLeft();
        node.setLeft(pivot.getRight());
        pivot.setRight(node);
        node.updateHeight();
        pivot.updateHeight();
        return pivot;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL.
     *
     * @param node The current node (TreeNode).
     * @param cs   Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> cs) {
            if (node == null) {
                node = new TreeNode<T>(cs);
                node.getCs().incrementCount();
                this.size++;
            } else {
                final int result = node.getCs().compareTo(cs);

                if (result > 0) {
                    node.setLeft(this.insertAux(node.getLeft(), cs));
                } else if (result < 0) {
                    node.setRight(this.insertAux(node.getRight(), cs));
                } else {
                    node.getCs().incrementCount();
                }

                node.updateHeight();
                int balance = balance(node);

                if (balance > 1) {
                    if (balance(node.getLeft()) < 0) {
                        node.setLeft(rotateLeft(node.getLeft()));
                    }
                    node = rotateRight(node);
                } else if (balance < -1) {
                    if (balance(node.getRight()) > 0) {
                        node.setRight(rotateRight(node.getRight()));
                    }
                    node = rotateLeft(node);
                }
            }

            return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
    	if (node == null) {
            return true;
        }

        if ((minNode != null && node.getCs().compareTo(minNode.getCs()) <= 0)
                || (maxNode != null && node.getCs().compareTo(maxNode.getCs()) >= 0)) {
            return false;
        }

        int balance = balance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }

        return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
