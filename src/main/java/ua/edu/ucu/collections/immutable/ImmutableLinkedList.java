package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {

    private static class Node {
        Object data;
        Node next;
        Node prev;

        //NODE INITIALISATION
        Node(Object elem, Node next, Node prev) {
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }

        Node(Object elem) {
            this.data = elem;
            this.next = null;
            this.prev = null;
        }

    }


    private Node head;
    private int len;
    private Node tail;

    //LINKEDLIST INITIALISATION
    private ImmutableLinkedList(ImmutableLinkedList element) {
        this.head = new Node(element.head);
        Node copyFrom = element.head;
        Node copyTo = this.head;
        this.len = element.len;
        while (copyFrom.next != null) {
            copyTo.next = copyFrom.next;
            copyTo = copyTo.next;
            copyFrom = copyFrom.next;
        }
        this.tail = copyTo;
    }

    private ImmutableLinkedList() {
        this.head = null;
        this.len = 0;
        this.tail = null;
    }


    //START OF FUNCTIONS
    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.head = new Node(e);
        newone.len += 1;
        return newone;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.tail.next = new Node(e);
        newone.tail = newone.tail.next;
        newone.len += 1;
        return newone;
    }

    @Override
    public ImmutableLinkedList add(Object e) {

        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        if (newone.size() == 0) {
            return addFirst(e);
        } else {
            return addLast(e);
        }
    }

    private void checkIndex(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node getNode(ImmutableLinkedList lst, int index) {
        checkIndex(index);
        Node el = lst.head;
        int i = 0;

        while (i != index) {
            el = el.next;
            i++;
        }
        return el;
    }

    @Override
    public ImmutableList add(int index, Object e) {

        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        Node change = getNode(newone, index);

        change.next = new Node(change.data, change.next, change);
        change.data = e;
        newone.len += 1;
        return newone;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);

        for (Object el : c) {
            newone = newone.add(el);
        }
        return newone;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {

        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        Node change = getNode(newone, index);
        int i = 0;
        for (Object el : c) {
            change.next = new Node(change.data, change.next, change);
            change.data = el;
            newone.len += 1;
            change = change.next;
        }

        return newone;

    }

    public Object getFirst(){
        checkIndex(0);
        return getNode(this, 0).data;
    }

    public Object getLast(){
        checkIndex(0);
        return getNode(this, size()).data;
    }

    @Override
    public Object get(int index) {
        return getNode(this, index).data;
    }


    @Override
    public ImmutableList remove(int index) {
        checkIndex(index);
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        Node el = getNode(newone, index);
        if (el.next != null) {
            el.next.prev = el.prev;
        }
        el.prev.next = el.next;
        newone.len -= 1;
        return newone;
    }

    public ImmutableLinkedList removeFirst(){
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.head = this.head.next;
        newone.len -= 1;
        if (newone.len == 0){
            newone.tail = null;
        }
        return newone;
    }
    public ImmutableLinkedList removeLast(){
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.tail =newone.tail.prev;
        newone.len -= 1;
        if (newone.len == 0){
            newone.head = null;
        }
        return newone;
    }


    @Override
    public ImmutableList set(int index, Object e) {
        checkIndex(index);
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        Node el = getNode(newone, index);
        el.data = e;
        return newone;
    }

    @Override
    public int indexOf(Object e) {
        Node getting = this.head;
        int i = 0;
        while (getting.data != null) {
            if (getting.data == e) {
                return i;
            }
            i++;
            getting = getting.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.len;
    }

    @Override
    public ImmutableList clear() {
        ImmutableLinkedList newone = new ImmutableLinkedList();
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] toReturn = new Object[size()];
        Node a = this.head;
        for (int i = 0; i < size(); i++) {
            toReturn[i] = a.data;
            a = a.next;
        }
        return toReturn;
    }

    @Override
    public String toString() {
        Node a = this.head;
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < size() - 1; i++) {
            toReturn.append((String) a.data);
            toReturn.append(", ");
        }
        toReturn.append((String) a.next.data);
        return toReturn.toString();
    }

}
