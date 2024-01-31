package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-03-13
 */
public class BST<T extends Comparable<T>> {

    // Attributes.
    /**
     * Count of comparisons performed by tree.
     */
    protected int comparisons = 0;
    /**
     * Root node of the tree.
     */
    protected TreeNode<T> root = null;
    /**
     * Number of nodes in the tree.
     */
    protected int size = 0;

    /**
     * Auxiliary method for {@code equals}. Determines whether two subtrees are
     * identical in items and height.
     *
     * @param source Node of this BST.
     * @param target Node of that BST.
     * @return true if source and target are identical in items and height.
     */
    protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {

    	if (source == null && target == null) {
            return true;
        }
        if (source == null || target == null) {
            return false;
        }
        if (!source.getCs().equals(target.getCs())) {
            return false;
        }
        if (source.getHeight() != target.getHeight()) {
            return false;
        }
        return equalsAux(source.getLeft(), target.getLeft()) && equalsAux(source.getRight(), target.getRight());
    }

    /**
     * Auxiliary method for insert. Inserts data into this BST.
     *
     * @param node The current node (TreeNode).
     * @param cs   Data to be inserted into the tree.
     * @return The inserted node.
     */
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> cs) {

	if (node == null) {
	    // Base case - add a new node containing the data.
	    node = new TreeNode<T>(cs);
	    node.getCs().incrementCount();
	    this.size++;
	} else {
	    // Compare the node data against the insert data.
	    final int result = node.getCs().compareTo(cs);

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.insertAux(node.getLeft(), cs));
	    } else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.insertAux(node.getRight(), cs));
	    } else {
		// Base case - data is already in the tree, increment its count
		node.getCs().incrementCount();
	    }
	}
	node.updateHeight();
	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree.
     *
     * @param node    The root of the subtree to test for validity.
     * @param minNode The node of the minimum value in the current subtree.
     * @param maxNode The node of the maximum value in the current subtree.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

    	 if (node == null) {
    	        return true;
    	    }
    	    if (minNode != null && node.getCs().compareTo(minNode.getCs()) <= 0) {
    	        return false;
    	    }
    	    if (maxNode != null && node.getCs().compareTo(maxNode.getCs()) >= 0) {
    	        return false;
    	    }
    	    return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Returns the height of a given TreeNode.
     *
     * @param node The TreeNode to determine the height of.
     * @return The height attribute of node, 0 if node is null.
     */
    protected int nodeHeight(final TreeNode<T> node) {
	int height = 0;

	if (node != null) {
	    height = node.getHeight();
	}
	return height;
    }

    /**
     * Determines if this BST contains key.
     *
     * @param key The key to search for.
     * @return true if this contains key, false otherwise.
     */
    public boolean contains(final CountedValue<T> key) {

    	comparisons = 0;
        TreeNode<T> node = root;
        while (node != null) {
            comparisons++;
            final int cmp = key.compareTo(node.getCs());
            if (cmp == 0) {
                return true;
            }
            node = cmp < 0 ? node.getLeft() : node.getRight();
        }

	return false;
    }

    /**
     * Determines whether two trees are identical.
     *
     * @param target The tree to compare this BST against.
     * @return true if this and target contain nodes that match in position, item,
     *         count, and height, false otherwise.
     */
    public boolean equals(final BST<T> target) {
	boolean isEqual = false;

	if (this.size == target.size) {
	    isEqual = this.equalsAux(this.root, target.root);
	}
	return isEqual;
    }

    /**
     * Get number of comparisons executed by the retrieve method.
     *
     * @return comparisons
     */
    public int getComparisons() {
	return this.comparisons;
    }

    /**
     * Returns the height of the root node of this tree.
     *
     * @return height of root node, 0 if the root node is null.
     */
    public int getHeight() {
	int height = 0;

	if (this.root != null) {
	    height = this.root.getHeight();
	}
	return height;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in this tree.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in
     * order from smallest to largest.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return The contents of this tree as a list of data.
     */
    public ArrayList<CountedValue<T>> inOrder() {
	return this.root.inOrder();
    }

    /**
     * Inserts data into this tree.
     *
     * @param cs Data to store.
     */
    public void insert(final CountedValue<T> cs) {
	this.root = this.insertAux(this.root, cs);
	return;
    }

    /**
     * Determines if this tree is empty.
     *
     * @return true if this tree is empty, false otherwise.
     */
    public boolean isEmpty() {

    	return size == 0;
    }

    /**
     * Determines if this tree is a valid BST; i.e. a node's left child data is
     * smaller than its data, and its right child data is greater than its data, and
     * a node's height is equal to the maximum of the heights of its two children
     * (empty child nodes have a height of 0), plus 1.
     *
     * @return true if this tree is a valid BST, false otherwise.
     */
    public boolean isValid() {
	return this.isValidAux(this.root, null, null);
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in node
     * level order starting from the root node. Helps determine the structure of the
     * tree.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return this tree data as a list of data.
     */
    public ArrayList<CountedValue<T>> levelOrder() {
	return this.root.levelOrder();
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in node
     * preorder.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return The contents of this tree as a list of data.
     */
    public ArrayList<CountedValue<T>> preOrder() {
	return this.root.preOrder();
    }

    /**
     * Resets the comparison count to 0.
     */
    public void resetComparisons() {
	this.comparisons = 0;
	return;
    }

    /**
     * Retrieves a copy of data matching key (key should have item count of 0).
     * Returning a complete CountedValue gives access to the data and its count.
     *
     * @param key The key to look for.
     * @return data The complete CountedValue that matches key, null otherwise.
     */
    public CountedValue<T> retrieve(final CountedValue<T> key) {
    	comparisons = 0;
        TreeNode<T> node = root;
        while (node != null) {
            comparisons++;
            final int cmp = key.compareTo(node.getCs());
            if (cmp == 0) {
                return node.getCs();
            }
            node = cmp < 0 ? node.getLeft() : node.getRight();
        }
	return null;
    }
}
