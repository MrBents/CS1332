import java.util.Comparator;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("One or both arguments "
                + "are not valid");
        }
        boolean swapped = true;
        int n = arr.length;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    swapped = true;
                    swap(arr, i - 1, i);
                }
            }
            n--;
        }

    }

    /**
    * @param arr array where swap will take place
    * @param idx1 first index
    * @param idx2 second index
    * @param <T> type
    */
    private static <T> void swap(T[] arr, int idx1, int idx2) {
        T temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("One or both arguments "
                + "are not valid");
        }
        for (int i = 1; i < arr.length; i++) {
            T element = arr[i];
            int j = i;
            while (j != 0) {
                if (comparator.compare(element, arr[j - 1]) < 0) {
                    // arr[j] = arr[j - 1];
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
                j--;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("One or both arguments "
                + "are not valid");
        }
        int a = 0;
        int b = arr.length - 1;
        qsort(arr, a, b, comparator, rand);

    }

    /**
    * @param arr array to be sorted
    * @param left lower idx
    * @param right upper index
    * @param comparator copmarator object
    * @param rand random number generator
    * @param <T> data type to sort
    */
    private static <T> void qsort(T[] arr, int left, int right,
        Comparator<T> comparator, Random rand) {
        if (left >= right) {
            return;
        }
        int pivotIndex = (right + left) / 2;
        T pivot = arr[pivotIndex];
        int idx = part(pivot, arr, left, right, comparator);
        qsort(arr, left, idx - 1, comparator, rand);
        qsort(arr, idx, right, comparator, rand);

    }

    /**
    * @param pivot pivot value
    * @param left lower idx
    * @param right upper index
    * @param comparator copmarator object
    * @param array the array to be sorted
    * @return next index to split
    * @param <T> data type to sort
    */
    private static <T> int part(T pivot, T[] array, int left, int right,
        Comparator<T> comparator) {
        while (left <= right) {
            while (comparator.compare(array[left], pivot) < 0) {
                left++;
            }
            while (comparator.compare(array[right], pivot) > 0) {
                right--;
            }

            if (left <= right) {
                swap(array, left++, right--);
            }
        }
        return left;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("One or both arguments "
                + "are not valid");
        }
        msort(0, arr.length - 1, arr, comparator);
    }

    /**
    * @param arr array to be sorted
    * @param lidx lower idx
    * @param hidx higher index
    * @param comparator copmarator object
    * @param <T> data type to sort
    */
    private static <T> void msort(int lidx, int hidx, T[] arr,
        Comparator<T> comparator) {
        int bound = lidx + (hidx - lidx) / 2;
        if (lidx < hidx) {
            msort(lidx, bound, arr, comparator);
            msort(bound + 1, hidx, arr, comparator);
            merger(lidx, bound, hidx, arr, comparator);
        }
    }

    /**
    * @param arr array to be sorted
    * @param lidx lower idx
    * @param hidx higher index
    * @param bound middle index
    * @param comparator copmarator object
    * @param <T> data type to sort
    */
    private static <T> void merger(int lidx, int bound, int hidx, T[] arr,
        Comparator<T> comparator) {
        T[] temp = (T[]) new Object[arr.length];
        for (int i = lidx; i <= hidx; i++) {
            temp[i] = arr[i];
        }
        int m = lidx;
        int k = bound + 1;
        int j = lidx;

        while (k <= hidx && m <= bound) {
            if (comparator.compare(temp[m], (temp[k])) <= 0) {
                arr[j] = temp[m];
                m += 1;
            } else {
                arr[j] = temp[k];
                k += 1;
            }
            j += 1;
        }

        while (m <= bound) {
            arr[j++] = temp[m++];
        }

    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("One or both arguments "
                + "are not valid");
        }
        return new int[10];
    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("One or both arguments "
                + "are not valid");
        }
        return new int[10];
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
