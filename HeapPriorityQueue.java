public class HeapPriorityQueue<E> implements SimplePriorityQueue<E> {

    private ArrayBinaryHeap<Pair<E>> heap = new ArrayBinaryHeap<Pair<E>>();

    public int size() {
	return heap.size();
    }

    public boolean isEmpty() {
	return heap.isEmpty();
    }

    public E min() {
	if (heap.min() == null) {
	    return null;
	}
	
	return heap.min().val;
    }

    public void insert(long k, E x) {
	Pair<E> p = new Pair<E>(k, x);
	heap.insert(p);
    }

    public E removeMin() {
	Pair<E> p = heap.removeMin();
	
	if (p == null) {
	    return null;
	}
	
	return p.val;
    }

    private class Pair<E> implements Comparable<Pair<E> > {
	E val;
	long key;

	public Pair(long key, E val) {
	    this.key = key;
	    this.val = val;
	}
	
	public int compareTo(Pair<E> p) {
	    long diff = key - p.key;

	    if (diff > 0) {
		return 1;
	    } else if (diff < 0) {
		return -1;
	    } else {
		return 0;
	    }
	}
    }
}
