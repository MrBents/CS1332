/**
 * Your implementation of an ArrayList.
 *
 * @author Max Bentata
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size = 0;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            new IllegalArgumentException("Cannot add null element");
        } else if (index < 0 || index > size) {
            new IndexOutOfBoundsException("index must be greater than 0");
        } else {
            if (size + 1 > backingArray.length) {
                reGrow();
            }
            T[] newArray = (T[]) new Object[backingArray.length];
            newArray = backingArray;
            for (int i = index + 1; i < backingArray.length; i++) {
                newArray[i] = backingArray[i - 1];
            }
            newArray[index] = data;
            backingArray = newArray;
        }
        this.size = this.size + 1;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null element");
        }
        if (size + 1 > backingArray.length) {
            reGrow();
        }
        T[] newArray = (T[]) new Object[backingArray.length];
        for (int i = 1; i < backingArray.length; i++) {
            newArray[i] = backingArray[i - 1];
        }
        newArray[0] = data;
        backingArray = newArray;
        this.size = this.size + 1;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null element");
        }
        if (size + 1 > backingArray.length) {
            reGrow();
        }
        backingArray[size] = data;
        this.size = this.size + 1;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index must be greater than 0");
        }
        T element = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        this.size = this.size - 1;
        return element;
    }

    @Override
    public T removeFromFront() {
        T element = backingArray[0];
        for (int i = 1; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        this.size = this.size - 1;
        return element;
    }

    @Override
    public T removeFromBack() {
        T element = backingArray[size - 1];
        backingArray[size - 1] = null;
        this.size = this.size - 1;
        return element;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index must be greater than 0");
        } else {
            return backingArray[index];
        }
    }

    @Override
    public boolean isEmpty() {
        boolean response = (backingArray[0] == null) ? true : false;
        return response;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    /**
     * Regrows the array to twixe its capacity when needed.
     */
    private void reGrow() {
        T[] newArray = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;
    }
}
