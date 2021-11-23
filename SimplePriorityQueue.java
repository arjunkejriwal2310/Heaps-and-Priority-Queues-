/**
 *
 * <p>A container that stores elements, where each element has an
 * associated <em>priority</em> represented as a <code>long</code>
 * value. Elements can be inserted into the priority queue together
 * with an associated priority, where priorities are assumed to be
 * distinct (i.e., two distinct elements should not have the same
 * priority). Smaller <code>long</code> values indicate higher
 * priority. Access is only granted to the highest priority element in
 * the queue.</p>
 *
 * <p>More formally, the state of a priority queue is a set of
 * <em>pairs</em>, where each pair consistst of an integer
 * (representing the priority) and an element. That is <code>S =
 * {(k_0, x_0), (k_1, x_1), ..., (k_{n-1}, x_{n-1})}</code>, where the
 * <code>k_i</code> are numbers satisfying <code>k_0 &#60; k_1 &#60;
 * ... &#60; k_{n-1}</code>, and <code>x_i</code> is the element
 * associated with priority <code>k_i</code>.</p>
 *
 */

public interface SimplePriorityQueue<E> {
    
    /**
     * <p>Get the current size of the priority queue (i.e., the number
     * of elements currently stored in the queue).</p>
     *
     * @return the current size
     */    
    int size();

    /**
     * <p>Determine if the priority queue is currently empty (i.e.,
     * currently contains no elements).</p>
     *
     * @return true if and only if the priority queue is empty
     */        
    boolean isEmpty();

    /**
     * <p>Return the element with highest priority stored in the
     * queue. That is, if the current state is <code>S = {(k_0, x_0),
     * (k_1, x_1), ..., (k_{n-1}, x_{n-1})}</code>, where the
     * <code>k_i</code> are numbers satisfying <code>k_0 &#60; k_1 &#60;
     * ... &#60; k_{n-1}</code>, then the value <code>x_0</code> is
     * returned. This method does not modify the state of the
     * queue.</p>
     *
     * @return the highest priority element in the queue
     */
    E min();

    /**
     * <p>In sert the element <code>x</code> with priority
     * <code>k</code>. If the current state is <code>S = {(k_0, x_0),
     * (k_1, x_1), ..., (k_{n-1}, x_{n-1})}</code> and <code>k</code>
     * satisfies <code>k_i &#60; k &#60; k_{i+1}</code>, then the new
     * state will be <code>S = {(k_0, x_0),..., (k_i, x_i), (k, x),
     * (k_{i+1}, x_{i+1}),..., (k_{n-1}, x_{n-1})}</code>.</p>
     *
     * @param k the priority of the element to be inserted
     * @param x the element to be inserted with priority <code>k</code>
     */
    void insert(long k, E x);

    /**
     * <p>Remove and return the element with highest priority in the
     * queue. If the current state is <code>S = {(k_0, x_0), (k_1,
     * x_1), ..., (k_{n-1}, x_{n-1})}</code>, the state will be
     * updated to <code>S = {(k_1, x_1),..., (k_{n-1},
     * x_{n-1})}</code> and the element <code>x_0</code> returned.</p>
     *
     * @return the element with highest priority in the queue
     */
    E removeMin();
}
