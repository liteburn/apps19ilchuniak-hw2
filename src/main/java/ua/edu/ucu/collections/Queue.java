package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList data;

    Queue(Object[] data) {
        this.data = new ImmutableLinkedList(data);
    }

    Queue(Object elem) {
        this.data = new ImmutableLinkedList(elem);
    }

    Queue() {
        this.data = new ImmutableLinkedList();
    }

    private Object getFirst() {

        return this.data.getFirst();
    }

    public Object peek() {
        return this.getFirst();
    }

    private void removeFirst() {
        this.data = this.data.removeFirst();

    }

    public Object dequeue() {
        Object ret = peek();
        this.removeFirst();
        return ret;
    }

    private void add(Object e) {
        this.data = this.data.add(e);
    }

    public void enqueue(Object e) {
        this.add(e);
    }
}
