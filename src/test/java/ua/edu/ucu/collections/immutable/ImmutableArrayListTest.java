package ua.edu.ucu.collections.immutable;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {

    private ImmutableArrayList arr1 = new ImmutableArrayList();
    private Object[] lst = {'s', '3', 'a', 'G', 'R'};
    private ImmutableArrayList arr3 = arr1.addAll(lst);

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddException() {
        ImmutableArrayList arr = arr1.add(0, 4);
    }

    @Test
    public void testAdd1() {
        ImmutableArrayList arr2 = arr1.add('3');
        Object[] checker = {'3'};
        Object[] checker1 = {};
        assertArrayEquals(checker, arr2.toArray());
        assertArrayEquals(checker1, arr1.toArray());
        assertNotSame(arr2.toString(), arr1.toString());
    }

    @Test
    public void testAdd2() {
        ImmutableArrayList arr2 = arr1.add('3');
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
        ImmutableArrayList arr2 = arr1.add('4');
        arr2 = arr2.add('s');
        Object[] toAdd = {3, 't', 'i'};
        ImmutableArrayList arr3 = arr2.addAll(1, toAdd);
        Object[] compare = {'4', 3, 't', 'i', 's'};
        assertArrayEquals(compare, arr3.toArray());
    }

    @Test
    public void testGet() {
        ImmutableArrayList arr2 = arr1.add('s');

        assertSame(arr2.get(0), 's');
    }

    @Test
    public void testRemove() {
        ImmutableArrayList arr2 = arr1.add('1');
        arr2 = arr2.add('s');
        assertEquals(2, arr2.size(), 0.0001);
        arr2 = arr2.remove(0);
        assertEquals(1, arr2.size(), 0.0001);
        Object[] compare = {'s'};
        assertArrayEquals(compare, arr2.toArray());
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
        ImmutableArrayList cleaned = arr3.clear();
        Object[] checker = {};
        assertArrayEquals(checker, cleaned.toArray());
        assertTrue(cleaned.isEmpty());
        assertSame(cleaned.toString(), "");
    }

}
