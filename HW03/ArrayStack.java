/**
 * Your implementation of an array-backed stack.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size = -1;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return (size < 0) ? true : false;
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size < 0) {
            throw new java.util.NoSuchElementException("There are no "
            + "elements in the stack");
        } else {
            T element = backingArray[size];
            backingArray[size] = null;
            this.size -= 1;
            return element;
        }


    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (size + 1 >= backingArray.length) {
            reGrow();
            this.size += 1;
            backingArray[size] = data;
        } else {
            this.size += 1;
            backingArray[size] = data;
        }

    }

    @Override
    public int size() {
        return this.size + 1;
    }

    /**
     * Returns the backing array of this stack.
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

    /**
     * Grows the backingArray to twice its capacity plus one
     *
     */
    private void reGrow() {
        T[] newArr = (T[]) new Object[backingArray.length * 2 + 1];
        for (int i = 0; i < backingArray.length; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }
}
