/**
 * Your implementation of a linked queue.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("There are no "
            + "elements in the stack");
        } else if (size == 1) {
            T element = head.getData();
            head = null;
            tail = null;
            this.size -= 1;
            return element;
        } else {
            T element = head.getData();
            head = head.getNext();
            this.size -= 1;
            return element;
        }
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (size == 0) {
            head = new LinkedNode<T>(data, null);
            tail = head;
            this.size += 1;
        } else if (size == 1) {
            head.setNext(new LinkedNode<T>(data, null));
            this.size += 1;
            tail = head.getNext();
        } else {
            tail.setNext(new LinkedNode<T>(data, null));
            tail = tail.getNext();
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
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
