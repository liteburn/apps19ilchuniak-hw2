package ua.edu.ucu.collections;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue1 = new Queue();
    private Queue queue2 = new Queue(1);
    private Object[] lst = {1,'4','a', 3, 1};
    private Object[] lst1 = {};
    private Queue queue3 = new Queue(lst);
    private Queue queue4 = new Queue(lst1);

    @Test (expected = IndexOutOfBoundsException.class)
    public void testPeekEmpty() {
        queue1.peek();
    }

    @Test
    public void tesxPeek1(){
        assertSame(queue2.peek(), 1);
    }

    @Test
    public void testPeekAndDequeue2(){
        assertSame(queue3.peek(), queue3.dequeue());
        assertSame(queue3.peek(), queue3.dequeue());
    }



    @Test
    public void testEnqueue(){
        queue4.enqueue('m');
        assertSame(queue4.peek(), 'm');
    }
}
