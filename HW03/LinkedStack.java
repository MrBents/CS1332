/**
 * Your implementation of a linked stack.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size = 0;

    @Override
    public boolean isEmpty() {
        return (size <= 0) ? true : false;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("There are no "
            + "elements in the stack");
        } else if (size == 1)  {
            T element = head.getData();
            head = null;
            this.size -= 1;
            return element;
        } else {
            T cur = head.getData();
            head = head.getNext();
            this.size -= 1;
            return cur;
        }
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (size == 0) {
            head = new LinkedNode<T>(data, null);
            this.size += 1;
        } else {
            LinkedNode<T> next = new LinkedNode<T>(data, null);
            next.setNext(head);
            head = next;
            this.size += 1;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns the head of this stack.
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
}
