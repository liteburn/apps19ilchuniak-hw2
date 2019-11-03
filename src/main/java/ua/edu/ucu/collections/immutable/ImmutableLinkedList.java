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

        this.head = null;
        this.tail = null;
        Node copyFrom = element.head;
        this.len = element.len;
        while (copyFrom != null) {
            if (this.head == null) {
                this.head = new Node(copyFrom.data);
                this.tail = this.head;
            } else {
                this.tail.next = new Node(copyFrom.data);
                this.tail.next.prev = this.tail;
                this.tail = this.tail.next;
            }
            copyFrom = copyFrom.next;
        }
    }

    public ImmutableLinkedList(Object[] lst) {
        if (lst.length > 0) {
            this.head = new Node(lst[0]);
            this.tail = this.head;
            this.len = 0;
            for (Object el : lst) {
                if (size() != 1) {
                    this.tail.next = new Node(el);
                    this.tail = this.tail.next;
                }
                this.len += 1;
            }
        } else {
            this.head = null;
            this.len = 0;
        }
    }

    public ImmutableLinkedList(Object elem) {
        this.head = new Node(elem);
        this.tail = this.head;
        this.len = 1;
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.len = 0;
        this.tail = null;
    }


    //START OF FUNCTIONS
    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.head = new Node(e);
        newone.tail = newone.head;
        newone.len += 1;
        return newone;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.tail.next = new Node(e);
        newone.tail.next.prev = newone.tail;
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
    public ImmutableLinkedList add(int index, Object e) {

        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        Node change = getNode(newone, index);

        change.next = new Node(change.data, change.next, change);
        change.data = e;
        newone.len += 1;
        return newone;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);

        for (Object el : c) {
            newone = newone.add(el);
        }
        return newone;
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
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


    public Object getFirst() {
        checkIndex(0);
        return this.head.data;
    }

    public Object getLast() {
        checkIndex(0);
        return this.tail.data;
    }

    @Override
    public Object get(int index) {
        return getNode(this, index).data;
    }


    @Override
    public ImmutableLinkedList remove(int index) {
        checkIndex(index);
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        if (size() == 1) {

            return new ImmutableLinkedList();
        }
        if (index == 0) {
            newone = newone.removeFirst();
        } else if (index == size() - 1) {
            newone = newone.removeLast();
        } else {
            Node el = getNode(newone, index);
            el.prev.next = el.next;
            el.next.prev = el.prev;
            newone.len -= 1;
        }
        return newone;
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.head = newone.head.next;
        newone.len -= 1;
        if (newone.head != null) {
            newone.head.prev = null;
        } else {
            this.tail = null;
        }
        return newone;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList newone = new ImmutableLinkedList(this);
        newone.tail = newone.tail.prev;
        newone.len -= 1;
        if (newone.tail != null) {
            newone.tail.next = null;
        } else {
            this.head = null;
        }

        return newone;
    }


    @Override
    public ImmutableLinkedList set(int index, Object e) {
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
        while (getting != null) {
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
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        if (size() == 0) {
            return new Object[]{};
        }
        Object[] toReturn = new Object[size()];

        Node from = this.head;

        for (int i = 0; i < size(); i++) {
            toReturn[i] = from.data;
            from = from.next;
        }

        return toReturn;
    }

    @Override
    public String toString() {
        Node a = this.head;
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < size() - 1; i++) {
            toReturn.append(a.data);
            toReturn.append(", ");
            a = a.next;
        }
        if (this.size() > 0) {
            toReturn.append(this.getLast());
        }
        return toReturn.toString();
    }

}
