/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Max Bentata
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head = null;
    private LinkedListNode<T> tail = null;
    private int size = 0;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be"
            + "within bounds");
        } else if (data == null) {
            throw new java.lang.IllegalArgumentException("Data has"
            + "to be passed");
        }
        if (index == 0) {
            addToFront(data);
            // To account for the size increment made by addToFront
            this.size = this.size - 1;
        } else if (index == size) {
            addToBack(data);
            // To account for the size increment made by addToBack
            this.size = this.size - 1;
        } else {

            LinkedListNode<T> cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.getNext();
            }
            LinkedListNode<T> set = new LinkedListNode<T>(data, cur.getNext());
            cur.setNext(set);
        }
        this.size = this.size + 1;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data has"
            + "to be passed");
        }
        if (head == null) {
            head = new LinkedListNode<T>(data, tail);
            tail = head;
        } else {
            LinkedListNode<T> front = new LinkedListNode<T>(data, head);
            head = front;
        }
        this.size = this.size + 1;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data has"
            + "to be passed");
        }
        if (isEmpty()) {
            addToFront(data);
            // To account for the size increment made by addToFront
            this.size = this.size - 1;
        } else {
            LinkedListNode<T> set = new LinkedListNode<T>(data, null);
            tail.setNext(set);
            tail = set;
        }
        this.size = this.size + 1;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index must be"
            + "within bounds");
        }
        T retorno;
        if (index == 0) {
            retorno = removeFromFront();
        } else if (index == size - 1) {
            retorno = removeFromBack();
        } else {
            LinkedListNode<T> cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.getNext();
            }
            LinkedListNode<T> next = cur.getNext();
            cur.setNext(next.getNext());
            this.size = this.size - 1;
            retorno = next.getData();
        }
        return retorno;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T element = head.getData();
        head = head.getNext();
        this.size = this.size - 1;
        if (size == 0) {
            head = null;
            tail = null;
        }
        return element;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            LinkedListNode<T> element = head;
            head = null;
            tail = null;
            this.size = this.size - 1;
            return element.getData();
        } else {
            LinkedListNode<T> cur = head;
            for (int i = 0; i < size - 2; i++) {
                cur = cur.getNext();
            }
            LinkedListNode<T> next = cur.getNext();
            T element = next.getData();
            tail = cur;
            this.size = this.size - 1;
            return element;
        }
    }

    @Override
    public T removeFirstOccurrence(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data has"
            + "to be passed");
        }
        if (size == 1 && head.getData() == data) {
            T element = head.getData();
            head = null;
            tail = null;
            this.size = this.size - 1;
            return element;
        } else if (head.getData() == data) {
            T element = head.getData();
            head = head.getNext();
            this.size = this.size - 1;
            return element;
        } else {
            LinkedListNode<T> cur = head;
            while (cur.getNext() != null) {
                if (cur.getNext().getData() == data) { // Add (cur
                    if (cur.getNext() == tail) {
                        T element = cur.getNext().getData();
                        if (size - 1 == 1) {
                            tail = head;
                        } else {
                            tail = cur;
                        }
                        cur.setNext(null);
                        this.size = this.size - 1;
                        return element;
                    } else {
                        T element = cur.getNext().getData();
                        cur.setNext(cur.getNext().getNext());
                        this.size = this.size - 1;
                        return element;
                    }
                }
                cur = cur.getNext();
            }
        }
        throw new java.util.NoSuchElementException("Element was not found");
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1 || size == 0) {
            throw new IndexOutOfBoundsException("Index must be"
            + "within bounds");
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            LinkedListNode<T> cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.getNext();
            }
            LinkedListNode<T> next = cur.getNext();
            // cur.setNext(next.getNext());
            return next.getData();
        }

    }

    @Override
    public Object[] toArray() {
        T[] arr = (T[]) new Object[size];
        LinkedListNode<T> cur = head;
        for (int i = 0; i < size; i++) {
            arr[i] = cur.getData();
            cur = cur.getNext();
        }
        return arr;
    }

    @Override
    public boolean isEmpty() {
        boolean response = (head == null) ? true : false;
        return response;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
