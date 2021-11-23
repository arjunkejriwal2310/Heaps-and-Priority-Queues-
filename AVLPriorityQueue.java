public class AVLPriorityQueue<E> implements SimplePriorityQueue<E> {
    
    SimpleSSet<Pair<E>> set = new AVLTree<Pair<E>>();

    @Override
    public int size() {
	return set.size();
    }

    @Override
    public boolean isEmpty() {
	return set.isEmpty();
    }

    @Override
    public E min() {
	return set.findMin().val;
    }

    @Override
    public void insert(long k, E x) {
	set.add(new Pair<E>(k, x));
    }

    @Override
    public E removeMin() {
	Pair<E> p = set.findMin();
	set.remove(p);
	return p.val;
    }

    protected class Pair<E> implements Comparable<Pair<E>> {
	long k;
	E val;

	public Pair(long k, E val) {
	    this.k = k;
	    this.val = val;
	}

	@Override
	public int compareTo(Pair p) {
	    if (k < p.k) {
		return -1;
	    } else if (k > p.k) {
		return 1;
	    }

	    return 0;
	}

	@Override
	public boolean equals(Object o) {
	    if (!(o instanceof Pair)) {
		return false;
	    }

	    return k == ((Pair) o).k;
	}
    }
}
