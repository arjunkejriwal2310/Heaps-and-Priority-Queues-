import java.util.Random;

public class PriorityQueueTester {
    static Random rand = new Random(10);
    static final int SIZE = 10;
    
    public static void main(String[] args) {
	// Replace with your preferred implementation
	// SimplePriorityQueue<Integer> q = new AVLPriorityQueue<Integer>();
	SimplePriorityQueue<Integer> q = new HeapPriorityQueue<Integer>();


	int[] vals = getVals();

	System.out.println("Testing insert method...");
	if (testInsert(q, vals)) {
	    System.out.println("...test passed!");
	} else {
	    System.out.println("...test failed.");
	}

	System.out.println("Testing remove method...");
	if (testRemove(q, vals)) {
	    System.out.println("...test passed!");
	} else {
	    System.out.println("...test failed.");
	}
	

    }

    // return an array of random integer values sorted in increasing
    // order
    static int[] getVals() {
	int[] vals = new int[SIZE];
	int cur = 0;
	for (int i = 0; i < SIZE; ++i) {
	    cur += 1 + rand.nextInt(10);
	    vals[i] = cur;
	}
	return vals;
    }
	

    static boolean testInsert(SimplePriorityQueue<Integer> q, int[] vals) {
	for (int i = vals.length - 1; i >= 0; --i) {
	    System.out.println("    adding " + vals[i] + " with priority " + i);
	    q.insert(i, vals[i]);

	    if (q.min() != null && !q.min().equals(vals[i])) {
		System.out.println("    ...incorrect min after inserting " + vals[i]);
		return false;
	    }

	    if (q.size() != vals.length - i) {
		System.out.println("    ...incrrect size after inserting " + vals[i]);
		return false;
	    }
	}

	return true;
    }

    static boolean testRemove(SimplePriorityQueue<Integer> q, int[] vals) {
	for (int i = 0; i < vals.length; ++i) {
	    long val = (q.min() != null) ? q.removeMin() : -1;

	    System.out.println("    removed value " + ((val == -1 ) ? "null" : val));

	    if (val != vals[i]) {
		System.out.println("    ...removed incorrect value\n" +
				   "       removed " + ((val == -1 ) ? "null" : val) +  ", expected "
				   + vals[i]);
		return false;	  
	    }

	    if (q.size() != vals.length - i - 1) {
		System.out.println("    ...incorrect size after removing " +
				   val);
		return false;
	    }	    
	}

	return true;
    }

    
    
}
