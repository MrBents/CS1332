/**
 * Your implementation of an array-backed queue.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front = 0;
    private int back = 0;
    private int size = 0;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Queue is empty");
        } else {
            T element = backingArray[front];
            backingArray[front] = null;
            front = (front + 1) % backingArray.length;
            this.size -= 1;
            return element;

        }
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity. If a regrow is necessary, you should copy
     * elements to the front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (size >= backingArray.length) {
            reGrow();
            backingArray[back] = data;
            back = (back + 1) % backingArray.length;
            this.size += 1;
        } else {
            backingArray[back] = data;
            back = (back + 1) % backingArray.length;
            this.size += 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size <= 0) ? true : false;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Grows the backingArray to twice its capacity plus one
     *
     */
    private void reGrow() {
        T[] newArr = (T[]) new Object[backingArray.length * 2 + 1];
        for (int i = 0; i < backingArray.length; i++) {
            newArr[i] = backingArray[(front + i) % backingArray.length];
        }
        front = 0;
        back = backingArray.length;
        backingArray = newArr;
    }

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}
