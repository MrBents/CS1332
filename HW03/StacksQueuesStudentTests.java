import org.junit.Test;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Some tests for the linked list-backed stack and queue,
 * testing removing from stack and queue with one element,
 * testing ArrayQueue's circular array and shifting front and back pointers,
 * testing resizing of arrays and exceptions.
 *
 * @author Madison Grams
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private LinkedStack<String> stack;
    private LinkedQueue<String> queue;
    private ArrayStack<Integer> aStack;
    private ArrayQueue<Integer> aQueue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPush() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        assertEquals(4, stack.size());

        LinkedNode<String> current = stack.getHead();
        assertNotNull(current);
        assertEquals("d", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("a", current.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPop() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        assertEquals("d", stack.pop());

        assertEquals(3, stack.size());

        LinkedNode<String> current = stack.getHead();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("a", current.getData());

        assertNull(current.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueue() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");

        assertEquals(4, queue.size());

        LinkedNode<String> current = queue.getHead();
        assertNotNull(current);
        assertEquals("a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("d", current.getData());

    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueDequeue() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");

        assertEquals(4, queue.size());
        assertEquals("a", queue.dequeue());
        assertEquals(3, queue.size());
        LinkedNode<String> current = queue.getHead();
        assertNotNull(current);
        assertEquals("b", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("c", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("d", current.getData());

        current = current.getNext();
        assertNull(current);
    }
    @Test(timeout = TIMEOUT)
    public void testArrayStackResize() {
        aStack = new ArrayStack<>();
        assertEquals(0, aStack.size());
        aStack.push(1);
        aStack.push(2);
        aStack.push(3);
        aStack.push(4);
        aStack.push(5);
        aStack.push(6);
        aStack.push(7);
        aStack.push(8);
        aStack.push(9);
        aStack.push(10);
        aStack.push(11);
        aStack.push(12);
        assertEquals(12, aStack.size());
        assertEquals(23, aStack.getBackingArray().length);
        Object[] backingArray = (aStack.getBackingArray());

        Object[] expected = new Object[23];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;
        expected[11] = 12;

        assertArrayEquals(expected, backingArray);
    }
    @Test(timeout = TIMEOUT)
    public void testArrayStackResizePop() {
        aStack = new ArrayStack<>();
        assertEquals(0, aStack.size());
        aStack.push(1);
        aStack.push(2);
        aStack.push(3);
        aStack.push(4);
        aStack.push(5);
        aStack.push(6);
        aStack.push(7);
        aStack.push(8);
        aStack.push(9);
        aStack.push(10);
        aStack.push(11);
        aStack.push(12);
        assertEquals(12, aStack.size());
        assertEquals(23, aStack.getBackingArray().length);
        assertEquals((Integer) 12, aStack.pop());
        assertEquals(11, aStack.size());
        Object[] backingArray = (aStack.getBackingArray());

        Object[] expected = new Object[23];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;

        assertArrayEquals(expected, backingArray);
    }
    @Test(timeout = TIMEOUT)
    public void testPopOne() {
        aStack = new ArrayStack<>();
        aStack.push(1);
        assertEquals(1, aStack.size());
        assertEquals((Integer) 1, aStack.pop());
        assertEquals(0, aStack.size());

        stack = new LinkedStack<>();
        stack.push("a");
        assertEquals(1, stack.size());
        assertEquals("a", stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.getHead());

    }
    @Test(timeout = TIMEOUT)
    public void testDequeueOne() {
        aQueue = new ArrayQueue<>();
        aQueue.enqueue(1);
        assertEquals(1, aQueue.size());
        assertEquals((Integer) 1, aQueue.dequeue());
        assertEquals(0, aQueue.size());

        queue = new LinkedQueue<>();
        queue.enqueue("a");
        assertEquals(1, queue.size());
        assertEquals("a", queue.dequeue());
        assertEquals(0, queue.size());
        assertNull(queue.getHead());
        assertNull(queue.getTail());
    }
    @Test(timeout = TIMEOUT)
    public void testQueueStackResize() {
        aQueue = new ArrayQueue<>();
        assertEquals(0, aQueue.size());
        aQueue.enqueue(1);
        aQueue.enqueue(2);
        aQueue.enqueue(3);
        aQueue.enqueue(4);
        aQueue.enqueue(5);
        aQueue.enqueue(6);
        aQueue.enqueue(7);
        aQueue.enqueue(8);
        aQueue.enqueue(9);
        aQueue.enqueue(10);
        aQueue.enqueue(11);
        aQueue.enqueue(12);
        assertEquals(12, aQueue.size());
        assertEquals(23, aQueue.getBackingArray().length);
        Object[] backingArray = (aQueue.getBackingArray());

        Object[] expected = new Object[23];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;
        expected[11] = 12;

        assertArrayEquals(expected, backingArray);
    }
    @Test(timeout = TIMEOUT)
    public void testQueueStackResizeDequeue() {
        aQueue = new ArrayQueue<>();
        assertEquals(0, aQueue.size());
        aQueue.enqueue(1);
        aQueue.enqueue(2);
        aQueue.enqueue(3);
        aQueue.enqueue(4);
        aQueue.enqueue(5);
        aQueue.enqueue(6);
        aQueue.enqueue(7);
        aQueue.enqueue(8);
        aQueue.enqueue(9);
        aQueue.enqueue(10);
        aQueue.enqueue(11);
        aQueue.enqueue(12);

        assertEquals(12, aQueue.size());
        assertEquals((Integer) 1, aQueue.dequeue());
        assertEquals(11, aQueue.size());
        assertEquals(23, aQueue.getBackingArray().length);
        Object[] backingArray = (aQueue.getBackingArray());

        Object[] expected = new Object[23];
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;
        expected[11] = 12;
        assertArrayEquals(expected, backingArray);
    }
    @Test(timeout = TIMEOUT)
    public void testShiftingFrontAndBack() {
        aQueue = new ArrayQueue<>();
        assertEquals(0, aQueue.size());
        aQueue.enqueue(1);
        aQueue.enqueue(2);
        aQueue.enqueue(3);
        assertEquals(3, aQueue.size());
        assertEquals((Integer) 1, aQueue.dequeue());
        assertEquals((Integer) 2, aQueue.dequeue());
        assertEquals(1, aQueue.size());
        aQueue.enqueue(4);
        assertEquals(2, aQueue.size());

        Object[] backingArray = aQueue.getBackingArray();

        Object[] expected = new Object[11];
        expected[2] = 3;
        expected[3] = 4;

        assertArrayEquals(expected, backingArray);

    }
    @Test(timeout = TIMEOUT)
    public void testCircularQueue() {
        aQueue = new ArrayQueue<>();
        assertEquals(0, aQueue.size());
        aQueue.enqueue(1);
        aQueue.enqueue(2);
        aQueue.enqueue(3);
        aQueue.enqueue(4);
        aQueue.enqueue(5);
        aQueue.enqueue(6);
        aQueue.enqueue(7);
        aQueue.enqueue(8);
        assertEquals(8, aQueue.size());
        assertEquals((Integer) 1, aQueue.dequeue());
        assertEquals((Integer) 2, aQueue.dequeue());
        assertEquals((Integer) 3, aQueue.dequeue());
        assertEquals((Integer) 4, aQueue.dequeue());

        aQueue.enqueue(9);
        aQueue.enqueue(10);
        aQueue.enqueue(11);
        aQueue.enqueue(12);
        assertEquals(8, aQueue.size());

        Object[] backingArray = aQueue.getBackingArray();

        Object[] expected = new Object[11];
        expected[0] = 12;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;

        assertArrayEquals(expected, backingArray);

    }
    @Test(timeout = TIMEOUT)
    public void testCircularQueueResize() {
        aQueue = new ArrayQueue<>();
        assertEquals(0, aQueue.size());
        aQueue.enqueue(1);
        aQueue.enqueue(2);
        aQueue.enqueue(3);
        aQueue.enqueue(4);
        aQueue.enqueue(5);
        aQueue.enqueue(6);
        aQueue.enqueue(7);
        aQueue.enqueue(8);
        assertEquals(8, aQueue.size());
        assertEquals((Integer) 1, aQueue.dequeue());
        assertEquals((Integer) 2, aQueue.dequeue());

        aQueue.enqueue(9);
        aQueue.enqueue(10);
        aQueue.enqueue(11);
        aQueue.enqueue(12);
        aQueue.enqueue(13);
        aQueue.enqueue(14);
        assertEquals(12, aQueue.size());

        Object[] backingArray = aQueue.getBackingArray();
        assertEquals(23, backingArray.length);
        Object[] expected = new Object[23];
        expected[0] = 3;
        expected[1] = 4;
        expected[2] = 5;
        expected[3] = 6;
        expected[4] = 7;
        expected[5] = 8;
        expected[6] = 9;
        expected[7] = 10;
        expected[8] = 11;
        expected[9] = 12;
        expected[10] = 13;
        expected[11] = 14;


        assertArrayEquals(expected, backingArray);

    }
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPopEmptyThrowsNSEE() {
        aStack = new ArrayStack<>();
        aStack.pop();
        stack = new LinkedStack<>();
        stack.pop();
    }
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDequeueEmptyThrowsNSEE() {
        aQueue = new ArrayQueue<>();
        aQueue.dequeue();
        queue = new LinkedQueue<>();
        queue.dequeue();
    }
}
