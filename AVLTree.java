/**
 * A height-balanced binary search tree (BST) implementation. An AVL
 * tree is a BST which additionally maintains a "height-balance"
 * property: for every node v, the heights of v's left and right
 * children differ by at most 1. As a result, an AVL tree with n nodes
 * has height O(log n). The operations add, remove, and find are all
 * implemented to require O(log n) comparisons and elementary operations.
 *
 * @see BinarySearchTree
 */


public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    
    /**
     * <p>Restructure an unbalanced node z in the AVLTree. If z's
     * children's heights differ by exactly 2, after this operation,
     * the heights will differ by at most one.</p>
     *
     * @param z the root of the subtree to be restructured
     */
    private void restructure(Node<E> z) {
	Node<E> y, x;

	y = (z.lHeight() >= z.rHeight()) ? z.left : z.right;
	x = (y.lHeight() >= y.rHeight()) ? y.left : y.right;

	restructure(z, y, x);
    }

    /**
     * <p>Restructure an unbalanced node z in the AVLTree. If z's
     * children's heights differ by exactly 2, after this operation,
     * the heights will differ by at most one. Here, z is the root, y
     * is z's taller child, and x is y's taller child, or left child
     * if y's children have the same height.</p>
     *
     * @param z the root of the subtree to be restructured
     * @param y the taller of z's children
     * @param x the taller of y's children, or y's left child if
     * children have same height
     */
    private void restructure(Node<E> z, Node<E> y, Node<E> x) {
	// the parent of z, if any
	Node<E> p = z.parent;

	// a, b, and c will be the nodes x, y, and z where a stores
	// the largest value, b the middle value, and c the smallest
	// value
	Node<E> a, b, c;

	// the four children of z, y, and x other than z, y, and x
	// themselves; t1 stores the smallest element, t2 next
	// smallest, t3 next, and t4 the largest element
	Node<E> t1, t2, t3, t4;

	// make the assignments of a, b, c, t1, t2, t3, t4 as
	// specified above
	if (y == z.left) {
	    if (x == y.left) {
		a = x;
		b = y;
		c = z;
		t1 = x.left;
		t2 = x.right;
		t3 = y.right;
		t4 = z.right;
	    } else if (x == y.right) {
		a = y;
		b = x;
		c = z;
		t1 = y.left;
		t2 = x.left;
		t3 = x.right;
		t4 = z.right;
		
	    } else {
		System.err.println("This should not have occurred!");
		return;		
	    }
	} else if (y == z.right) {
	    if (x == y.left) {
		a = z;
		b = x;
		c = y;
		t1 = z.left;
		t2 = x.left;
		t3 = x.right;
		t4 = y.right;
	    } else if (x == y.right) {
		a = z;
		b = y;
		c = x;
		t1 = z.left;
		t2 = y.left;
		t3 = x.left;
		t4 = x.right;
	    } else {
		System.err.println("This should not have occurred!");
		return;
	    }
	} else {
	    System.err.println("This should not have occurred!");
	    return;
	}

	// make node b the new root of the subtree, previously rooted at z
	b.parent = p;	
	if (p != null) {
	    if (z == p.left) {
		p.left = b;
	    } else {
		p.right = b;
	    }
	} else {
	    // if p is null, then z was root
	    root = b;
	}


	// set a and c to be b's left and right children, respectively
	b.left = a;
	a.parent = b;	
	b.right = c;
	c.parent = b;

	// set t1 and t2 to be a's left and right children,
	// respectively
	a.left = t1;
	if (t1 != null) {
	    t1.parent = a;
	}
	a.right = t2;
	if (t2 != null) {
	    t2.parent = a;
	}	
	a.updateHeight(false);

	// set t3 and t4 to be c's left and right children,
	// respectively	
	c.left = t3;
	if (t3 != null) {
	    t3.parent = c;
	}
	
	c.right = t4;
	if (t4 != null) {
	    t4.parent = c;
	}
	c.updateHeight(false);

	b.updateHeight(false);
    }

    

    @Override
    public boolean add(E val) {
	// if the tree was previously empty, make a new node storing
	// val to be the root
	if (root == null) {
	    root = new Node<E>();
	    root.value = val;
	    ++size;
	    return true;
	}

	// nd is the last non-null node encountered in searching for
	// val
	Node<E> nd = findNode(val);	

	// check if val was found
	if (val.equals(nd.value)) {
	    return false;
	}
	
	// val was not found, so create a new node to stor val,
	// additing it as a leaf in the tree in the appropriate
	// location
	Node<E> newNode = new Node<E>();
	
	newNode.value = val;
	newNode.parent = nd;

	if (val.compareTo(nd.value) < 0) {
	    nd.left = newNode;
	} else {
	    nd.right = newNode;
	}
		
	++size;

	// iterate over newNode's ancestors, updating their height and
	// checking for and fixing imbalance
	while (nd != null) {
	    nd.updateHeight(false);
	    if (isUnbalanced(nd)) {
		restructure(nd);
	    }
	    
	    nd = nd.parent;
	}

	return true;
    }

    /**
     * Remove a node with 0 or 1 children, then update the heights of
     * its ancestors. Check each ancestor for imbalance and, if
     * necessary, fix the imbalance with restructuring.
     */
    @Override
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

	// since nd is not root, nd.parent != null
	Node<E> parent = nd.parent;
	
	if (child != null) {
	    child.parent = parent;
	}
	
	if (nd == parent.left) {
	    parent.left = child;
	} else {
	    parent.right = child;
	}

	while(parent != null) {
	    parent.updateHeight(false);
	    
	    if (isUnbalanced(parent)) {
		restructure(parent);
	    }

	    parent = parent.parent;
	}	
    }

    /**
     * Determine if a node is height-unbalanced, i.e., if its
     * childrens' heights differ by at least 2.
     */
    private boolean isUnbalanced(Node<E> nd) {
	return (Math.abs(nd.lHeight() - nd.rHeight()) > 1);
    }
}
