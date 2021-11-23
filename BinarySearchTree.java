/**
 * An implementation of a binary search tree. Comparable elements are
 * stored in a binary tree of nodes, where each node stores a single
 * element. The tree maintains the BST property that at each node v,
 * all left-descendents of v store smaller values than v, while right
 * descendants of v store larger values. The tree supports basic
 * operations of adding, removing, and finding elements. Additionally,
 * the tree can report its size and height (i.e., the length of the
 * longest path from the root to any descendant leaf). The operations
 * add/remove/find use O(height) comparisons and elementary operations.
 */


public class BinarySearchTree<E extends Comparable<E>> implements SimpleSSet<E>
{

    /**
     * The root node of the tree.
     */
    protected Node<E> root = null;

    /**
     * The current size of the tree.
     */
    protected int size = 0;

    /**
     * Return the current height of the tree. That is, the length of
     * the longest path from the root to any leaf.
     *
     * @return the height of the tree
     */
    public int height() {
        if (root == null) {
            return -1;
        }

        return root.height();
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }

        return root.size();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Return a string listing the contents of the BST in-order. That
     * is, the elements of the set are listed in increasing order
     * according to their natural order.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        if (root != null) {
            root.buildString(sb);
        }

        sb.append("]");

        return sb.toString();

    }

    @Override
    public E find(E x) {
        Node<E> nd = findNode(x);

        if (nd == null) {
            return null;
        }

        if (x.equals(nd.value) || x.compareTo(nd.value) < 0) {
            return nd.value;
        }

        nd = nd.nextNode();

        if (nd == null) {
            return null;
        }

        return nd.value;
    }

    @Override
    public boolean add(E x) {
        // if the tree was previously empty, make a new node storing
        // val to be the root
        if (root == null) {
            root = new Node<E>();
            root.value = x;
            ++size;
            return true;
        }

        // nd is the last non-null node encountered in searching for
        // x
        Node<E> nd = findNode(x);

        // check if x was found
        if (x.equals(nd.value)) {
            return false;
        }

        // x was not found, so create a new node to store x,
        // additing it as a leaf in the tree in the appropriate
        // location
        Node<E> newNode = new Node<E>();

        newNode.value = x;
        newNode.parent = nd;

        if (x.compareTo(nd.value) < 0) {
            nd.left = newNode;
        } else {
            nd.right = newNode;
        }

        // update the height of newNode's ancestors
        newNode.updateHeight(true);

        ++size;

        return true;
    }

    @Override
    public E remove(E x) {
        // nd is the last non-null node encountered in searching for
        // x
        Node<E> nd = findNode(x);

        // check if x was not found
        if (nd == null || !(nd.value.equals(x))) {
            return null;
        }

        // nd.value equals x so remove it
        --size;

        // if nd has at most one child, splice it out
        if (nd.left == null || nd.right == null) {
            splice(nd);
            return nd.value;
        }

        E value = nd.value;

        // copy the "next" value in the set to nd and remove the node
        // previously storing the next value (using splice)
        Node<E> next = nd.nextNode();
        nd.value = next.value;
        splice(next);

        return value;
    }

    @Override
    public E findMin() {
        // check if the set is empty
        if (root == null) {
            return null;
        }

        // the smallest value is stored in the "left-most" node in the
        // tree
        Node<E> nd = root;
        while (nd.left != null) {
            nd = nd.left;
        }

        return nd.value;
    }

    @Override
    public E findMax() {
        // check if the set is empty
        if (root == null) {
            return null;
        }

        // the largestest value is stored in the "right-most" node in the
        // tree
        Node<E> nd = root;
        while (nd.right != null) {
            nd = nd.right;
        }

        return nd.value;
    }


    /**
     * Use binary search to find a node storing an element y equal to
     * x. If no such node exists, the last non-null node visited is
     * returned.
     */
    protected Node<E> findNode(E x) {
        Node<E> nd = root;
        Node<E> prev = root;

        while (nd != null) {
            if (x.equals(nd.value)) {
                return nd;
            }

            prev = nd;

            if (x.compareTo(nd.value) < 0) {
                nd = nd.left;
            } else {
                nd = nd.right;
            }
        }

        return prev;
    }

    /**
     * Remove a node with 0 or 1 children, then update the heights of
     * its ancestors.
     */
    protected void splice(Node<E> nd) {
        if (nd.left != null && nd.right != null) {
            throw new SpliceException("attempted to splice tree node with two children");
        }

        Node<E> child = (nd.left != null) ? nd.left : nd.right;

        // check if nd is root
        if (nd == root) {
            root = child;
            return;
        }

        Node<E> parent = nd.parent;

        if (child != null) {
            child.parent = parent;
        }

        if (nd == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        if (child != null) {
            child.updateHeight(true);
        } else if (parent != null) {
            parent.updateHeight(true);
        }
    }

    /**
     * A class representing a node with a parent, left child, right
     * child, and value. Additionally a node stores its height---i.e.,
     * the maximum distance from the node to a descendant leaf.
     */
    protected class Node<E> {
        protected Node<E> parent;
        protected Node<E> left;
        protected Node<E> right;
        protected int height = 0;
        E value;

        /**
         * Update the height of the node. Note that the height is one
         * more than the maximum of the heights of the node's
         * children. The method can be called recursively so that the
         * heights of all of this node's ancestors are also updated.
         *
         * @param recursive set to true to update ancestors' heights
         * as well
         */
        public void updateHeight(boolean recursive) {
            height = 1 + Math.max(lHeight(), rHeight());

            if (recursive && parent != null) {
                parent.updateHeight(true);
            }
        }

        /**
         * Get the height (i.e., longest distance to a descendant
         * leaf) of this node.
         *
         * @return the height of this node
         */
        public int height() {
            return height;
        }

        /**
         * Get the height (i.e., longest distance to a descendant
         * leaf) of this node's left child. By convention, if left is
         * null, the value -1 is returned.
         *
         * @return the height of this node's left child
         */
        public int lHeight() {
            if (left == null)
                return -1;

            return left.height;
        }

        /**
         * Get the height (i.e., longest distance to a descendant
         * leaf) of this node's right child. By convention, if right is
         * null, the value -1 is returned.
         *
         * @return the height of this node's right child
         */
        public int rHeight() {
            if (right == null)
                return -1;

            return right.height;
        }

        /**
         * Get the size of (i.e., number of nodes in) the subtree
         * rooted at this node.
         *
         * @return the size of the subtree rooted at this node
         */
        public int size() {
            int leftSize = (left == null) ? 0 : left.size();
            int rightSize = (right == null) ? 0 : right.size();

            return 1 + leftSize + rightSize;
        }

        /**
         * Get a string representation of this node, which is just a
         * String representation of the value stored in this node.
         *
         * @return a String representation of the value stored in this
         * node
         */
        @Override
        public String toString() {
            return value.toString();
        }

        /**
         * Append a String representation of the subtree rooted at
         * this node to the <code>StringBuilder sb</code>. The string
         * representation of the subtree lists the subtree's contents
         * in order (i.e., according to an in-order traversal).
         *
         * @param sb the StringBuilder to be appended to
         */
        public void buildString(StringBuilder sb) {
            if (left != null) {
                left.buildString(sb);
                sb.append(", ");
            }

            sb.append(this.toString());

            if (right != null) {
                sb.append(", ");
                right.buildString(sb);
            }
        }

        // alternate buildString shows tree structure in addition to
        // tree contents
        // public void buildString(StringBuilder sb) {
        //     if (left != null) {
        // 	sb.append("(");
        // 	left.buildString(sb);
        // 	sb.append(") , ");
        //     }

        //     sb.append(this.toString());

        //     if (right != null) {
        // 	sb.append(", (");
        // 	right.buildString(sb);
        // 	sb.append(")");
        //     }
        // }


        /**
         * Get the next Node in the tree after this Node during an
         * in-order traversal of the tree (or <code>null</code> if no
         * such node in the tree. Note that the value stored by the
         * next Node is the next smallest element stored in the tree.
         *
         * @return the next Node according to an in-order traversal,
         * or null of there is no such Node
         */
        public Node<E> nextNode() {
            // if this has a right child, the next node is the
            // left-most descendant of the right child
            if (right != null) {
                Node<E> nd = right;

                while (nd.left != null) {
                    nd = nd.left;
                }

                return nd;
            }

            // if this doesn't have a right child, the next node is
            // the next of the first ancestor of which this is a left
            // descendant

            Node<E> nd = this;

            while (nd.parent != null && nd == nd.parent.right) {
                nd = nd.parent;
            }

            if (nd.parent == null) {
                return null;
            }

            // nd is left child of parent
            return nd.parent.nextNode();
        }
    }
}