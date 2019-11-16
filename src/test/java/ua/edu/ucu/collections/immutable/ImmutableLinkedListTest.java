package ua.edu.ucu.collections.immutable;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {


    private ImmutableLinkedList arr1 = new ImmutableLinkedList();
    private Object[] lst = {'s', '3', 'a', 'G', 'R'};
    private ImmutableLinkedList arr3 = arr1.addAll(lst);

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddException() {
        ImmutableLinkedList arr = arr1.add(1, 4);
    }

    @Test
    public void testAdd1() {
        ImmutableLinkedList arr2 = arr1.add('3');
        Object[] checker = {'3'};
        Object[] checker1 = {};
        assertArrayEquals(checker, arr2.toArray());
        assertArrayEquals(checker1, arr1.toArray());
        assertNotSame(arr2.toString(), arr1.toString());
    }

    @Test
    public void testAdd2() {
        ImmutableLinkedList arr2 = arr1.add('3');
        arr2 = arr2.add(0, 4);
        Object[] checker = {4, '3'};
        assertArrayEquals(checker, arr2.toArray());
        arr2 = arr2.addAll(checker);
        String checkstr = "4, 3, 4, 3";
        assertEquals(checkstr, arr2.toString());
        Object[] checker3 = {4, '3', 4, '3'};
        assertArrayEquals(checker3, arr2.toArray());
    }

    @Test
    public void testAddAll() {
        ImmutableLinkedList arr2 = arr1.add('4');
        arr2 = arr2.add('s');
        Object[] toAdd = {3, 't', 'i'};
        ImmutableLinkedList arr3 = arr2.addAll(1, toAdd);
        Object[] compare = {'4', 3, 't', 'i', 's'};
        assertArrayEquals(compare, arr3.toArray());
        ImmutableLinkedList arr4 = arr2.addAll(2, toAdd);
        Object[] compare1 = {'4', 's', 3, 't', 'i'};
        assertArrayEquals(compare1, arr4.toArray());
    }

    @Test
    public void testGet() {
        ImmutableLinkedList arr2 = arr1.add('s');
        assertSame(arr2.get(0), 's');
        arr2 = arr2.add('m');
        assertSame(arr2.get(1), 'm');
    }

    @Test
    public void testRemove1() {
        ImmutableLinkedList arr2 = arr1.add('1');
        arr2 = arr2.add('s');
        assertEquals(2, arr2.size(), 0.0001);
        arr2 = arr2.remove(0);
        assertEquals(1, arr2.size(), 0.0001);
        Object[] compare = {'s'};
        assertArrayEquals(compare, arr2.toArray());
    }

    @Test
    public void testRemove2() {
        ImmutableLinkedList arr = new ImmutableLinkedList('m');
        assertTrue(arr.remove(0).isEmpty());
    }

    @Test
    public void testRemove3() {
        Object[] lst = {'m', 'r'};
        ImmutableLinkedList arr = new ImmutableLinkedList(lst);
        assertEquals('m', arr.remove(1).getFirst());
    }

    @Test
    public void testSet() {
        arr3 = arr3.set(3, "EZ");
        assertSame("EZ", arr3.get(3));
    }

    @Test
    public void testIndexOf() {
        assertSame(2, arr3.indexOf('a'));
    }

    @Test
    public void testIndexOfNotInList() {
        assertSame(-1, arr3.indexOf('p'));
    }

    @Test
    public void testClear() {
        ImmutableLinkedList cleaned = arr3.clear();
        Object[] checker = {};
        assertArrayEquals(checker, cleaned.toArray());
        assertTrue(cleaned.isEmpty());
        assertNotSame(cleaned.toString(), arr3.toString());
    }

    @Test
    public void testAllOther() {
        assertSame(arr3.getFirst(), 's');
        ImmutableLinkedList arr4 = arr3.removeLast();
        Object[] correct = {'s', '3', 'a', 'G'};
        assertArrayEquals(correct, arr4.toArray());
        arr4 = arr4.removeFirst();
        Object[] correct1 = {'3', 'a', 'G'};
        assertArrayEquals(correct1, arr4.toArray());
    }

}
