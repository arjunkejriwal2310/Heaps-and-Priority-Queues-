# Heaps-and-Priority-Queues-

#### Name: Arjun Kejriwal

As seen in the table and graph in the attached MS Excel file for
Question 1, the running time for both methods, insert() and removeMin(),
in both implementations, HeapPriorityQueue and AVLPriorityQueue, show 
an approximate linear relationship with the size of the data structure.
In other words, for all 4 methods, as the size of the data structure 
increases by the same amount, the running time (in nanoseconds) seems
to increase by approximately the same amount. As seen in the graph, 
the remove method in both implementations have similar run times for
every 100 words added (they don't differ by a significant amount). 
However, the insert method in the AVLPriorityQueue implementation has
a substantially higher running time (approximately double) than the
insert method in the HeapPriorityQueue implementation. Therefore, the 
insert method in the HeapPriorityQueue implementation is much faster 
at adding elements to a priority queue than the insert method in the 
AVLPriorityQueue implementation.

The array stores the topmost node of the heap at the left end of the array and fills in the 
rest of the array as we traverse down the heap. Since there is no 
specific rule that the right child needs to be greater than the left 
child, the array won't necessarily be arranged in increasing (ascending)
order. Therefore, binary search, and other searching algorithms like
jump search, interpolation search, and exponential search, can't be 
used to find elements in an array storing a heap because these searching
algorithms require arrays to be sorted. These searching algorithms tend
to find elements much faster than the linear search algorithm in which
the array is traversed from left to right (they tend to have a running
time of less than O(n)). If we were to force the find method in our
ArrayBinaryHeap class to search for an element using binary search or
any faster searching algorithm than linear search, we will need to 
modify the heap structure and the insert/remove methods in some way. 
Thus, we are forced to stick to linear search in order to find an 
element in an array that stores a heap. Thus, it is not possible to 
search for a given element significantly faster than traversing the 
entire array that stores a heap.  
