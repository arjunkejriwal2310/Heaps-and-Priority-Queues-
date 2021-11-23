public class ArrayBinaryHeap<E extends Comparable<E>>
{
    //Let the default size of the array storing the heap be 16
    public static final int DEFAULT_SIZE = 16;
    
    //Create the instance variable that will point to the array that will store the heap
    private Object[] contents;

    //Create and initialize the instance variables for the size and the maximum size of the heap
    private int size = 0;
    private int maxSize = 16;

    //Define the class's constructor, where the contents variable is made to point to a new array object of size 16
    public ArrayBinaryHeap()
    {
        contents = new Object[DEFAULT_SIZE];
    }

    //this method returns the size of the heap
    public int size()
    {
        return this.size;
    }

    //this method returns true if the heap is empty and false if it is not empty
    public boolean isEmpty()
    {
        return this.size == 0;
    }

    //this method returns the position of the parent of the argument node in the array
    private int parent(int position)
    {
        return ((position - 1) / 2);
    }

    //this method returns the position of the left child of the argument node in the array
    private int leftChild(int position)
    {
        return ((2 * position) + 1);
    }

    //this method returns the position of the right child of the argument node in the array
    private int rightChild(int position)
    {
        return ((2 * position) + 2);
    }

    //this method returns true if the argument node is a leaf node and false if the argument node is not a leaf node
    private boolean isLeaf(int position)
    {
        return ((position >= size / 2) && (position <= size));
    }

    //this method swaps the two nodes that have the two argument positions in the array
    private void swap(int firstPosition, int secondPosition)
    {
        //Storing the node in the 'firstPosition' in a temporary variable
        E temp = (E)contents[firstPosition];

        contents[firstPosition] = contents[secondPosition];
        contents[secondPosition] = temp;
    }

    //this is a helper method that restructures the heap so that all the elements in the heap are in the correct position
    private void restructureHeap(int position)
    {
        //this method doesn't do anything if the argument node is a leaf
        if (!isLeaf(position))
        {
            //if the argument node is greater than either of its children
            if ((((E) contents[position]).compareTo(((E) contents[leftChild(position)])) > 0) || ((((E) contents[position]).compareTo(((E) contents[rightChild(position)])) > 0)))
            {
                //if the left child is lesser than the right child
                if (((E) contents[leftChild(position)]).compareTo(((E) contents[rightChild(position)])) < 0)
                {
                    //swap the left child with the argument node
                    swap(position, leftChild(position));
                    //recursively call the same method
                    restructureHeap(leftChild(position));
                }
                //if the right child is lesser than the left child
                else
                {
                    //swap the right child with the argument node
                    swap(position, rightChild(position));
                    //recursively call the same method
                    restructureHeap(rightChild(position));
                }
            }
        }
    }

    //this method returns the minimum value (topmost node) in the heap
    public E min()
    {
        //check if the heap is empty and return null if it is
        if(isEmpty())
        {
            return null;
        }

        //the minimum value will be at the start of the array
        E popped = (E)contents[0];

        return popped;
    }

    //this method inserts the value x into the heap at the correct position based on the min heap rules
    public void insert(E x)
    {
        //increase the size of the array if the heap's size is greater than or equal to the size of the array
        if (size >= maxSize)
        {
            maxSize = this.increaseCapacity(maxSize);
        }

        contents[size] = x;

        //store the position of value x in the variable current
        int current = size;

        //while the parent of the current node is not null and while current node is smaller than its parent node,
        //swap the current and parent nodes and make the current node point to its parent node
        while (((E)contents[parent(current)]) != null && ((E)contents[current]).compareTo(((E)contents[parent(current)])) < 0)
        {
            swap(current, parent(current));
            current = parent(current);
        }

        size++;
    }

    //this method removes and returns the minimum value (topmost node) of the heap
    public E removeMin()
    {
        //check if the heap is empty and return null if it is
        if(isEmpty())
        {
            return null;
        }

        //store the minimum value in a variable
        E popped = (E)contents[0];

        //bring the last non-null value of the array to the front of the array
        contents[0] = contents[size - 1];
        size--;

        //call the restructureHeap method to restructure the heap
        restructureHeap(0);

        return popped;
    }

    //this method doubles the size of the array pointed to by the variable 'contents'
    private int increaseCapacity(int maxSize)
    {
        // create a new array with larger (double) capacity
        Object[] bigContents = new Object[2 * maxSize];

        // copy the values from contents to bigContents
        for (int i = 0; i < maxSize; ++i)
        {
            bigContents[i] = contents[i];
        }

        // set contents to point to the new bigger array
        contents = bigContents;

        // update the maximum size of the heap (size of the array) accordingly
        maxSize = 2 * maxSize;

        return maxSize;
    }
}
