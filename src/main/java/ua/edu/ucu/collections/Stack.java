package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList data;

    Stack(Object[] data) {
        this.data = new ImmutableLinkedList(data);
    }

    Stack(Object elem) {
        this.data = new ImmutableLinkedList(elem);
    }

    Stack() {
        this.data = new ImmutableLinkedList();
    }

    private Object getFirst() {
        return this.data.getLast();
    }

    public Object peek() {
        return this.getFirst();
    }

    public Object pop() {
        Object ret = peek();
        this.data = this.data.removeLast();
        return ret;
    }

    private void add(Object el) {
        this.data = this.data.add(el);
    }

    public void push(Object e) {
        this.add(e);
    }
}
