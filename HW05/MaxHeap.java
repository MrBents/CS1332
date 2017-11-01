/**
 * Your implementation of a max heap.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial length of {@code INITIAL_CAPACITY} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Item is not valid");
        } else if (size == 0) {
            backingArray[++size] = item;
        } else {
            if (size + 1 == backingArray.length) { //regrow the array
                T[] tempA = (T[]) new Comparable[2 * backingArray.length + 1];
                for (int i = 1; i <= size; i++) {
                    tempA[i] = backingArray[i];
                }
                backingArray = tempA;
            }

            backingArray[++size] = item;
            heapify(size);
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("There are no elements"
            + "in the Heap");
        } else if (size == 1) {
            T temp = backingArray[1];
            backingArray[1] = null;
            size -= 1;
            return temp;
        } else {
            T temp = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size -= 1;
            bubbleItDown(1);
            return temp;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }
    /**
     * bubbles it down
     *
     * @param idx index intended to bubbleitDown
     * @return int
     */
    private int bubbleItDown(int idx) {
        if (size < 2 * idx) {
            return 1;
        } else {
            int swapper;
            if (size == idx * 2) {
                swapper = idx * 2;
            } else {
                T rightC = backingArray[idx * 2 + 1];
                T leftC = backingArray[idx * 2];
                if (leftC.compareTo(rightC) > 0) {
                    swapper = idx * 2;
                } else {
                    swapper = idx * 2 + 1;
                }
            }
            if (backingArray[swapper].compareTo(backingArray[idx]) > 0) {
                T swapIt = backingArray[swapper];
                backingArray[swapper] = backingArray[idx];
                backingArray[idx] = swapIt;
                return bubbleItDown(swapper);
            } else {
                return 1;
            }
        }
    }

    /**
    * heapify's
    *
    * @param idx index intended to bubbleitDown
    * @return int
    */
    private int heapify(int idx) {
        if (idx == 1) {
            return 1;
        } else if (backingArray[idx].compareTo(backingArray[idx / 2]) < 0) {
            return 1;
        } else if (backingArray[idx].compareTo(backingArray[idx / 2]) > 0) {
            T swapIt = backingArray[idx / 2]; // save idx/2 in temp variable
            backingArray[idx / 2] = backingArray[idx];
            backingArray[idx] = swapIt;
            return heapify(idx / 2);
        } else {    // avoid duplicates
            return 1;
        }
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
