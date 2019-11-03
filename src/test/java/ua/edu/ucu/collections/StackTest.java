package ua.edu.ucu.collections;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Stack stack1 = new Stack();
    private Stack stack2 = new Stack('k');
    private Object[] lst = {1, 'm', 4, 'a', 'c'};
    private Stack stack3 = new Stack(lst);
    private Object[] lst1 = {};
    private Stack stack4 = new Stack(lst1);

    @Test (expected = IndexOutOfBoundsException.class)
    public void testPeekEmpty() {
        stack1.peek();
    }

    @Test
    public void testPeek2() {
        assertSame(stack2.peek(), 'k');
    }

    @Test
    public void testPeek3() {
        assertSame(stack3.peek(), 'c');
    }

    @Test
    public void testPop(){
        assertSame(stack3.pop(), 'c');
        assertSame(stack3.peek(), stack3.pop());
    }

    @Test
    public void testPush(){
        stack1.push('z');
        assertSame('z', stack1.peek());
        assertSame(stack1.peek(), stack1.pop());
    }
}
