package ua.edu.ucu.collections.immutable;

public final class ImmutableArrayList implements ImmutableList {

    private int len;
    private Object[] list;

    //LIST INITIALISATION
    public ImmutableArrayList(Object[] lst) {
        list = new Object[lst.length];
        System.arraycopy(lst, 0, list, 0, lst.length);
        len = lst.length;
    }

    public ImmutableArrayList() {
        list = new Object[0];
        len = 0;
    }


    @Override
    public ImmutableArrayList add(Object e) {

        Object[] lst = new Object[size() + 1];
        System.arraycopy(list, 0, lst, 0, size());
        lst[size()] = e;
        return new ImmutableArrayList(lst);
    }

    private void checkIndex(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        if (index == size()) {
            return add(e);
        }
        checkIndex(index);
        Object[] lst = new Object[size() + 1];
        System.arraycopy(list, 0, lst, 0, index);
        lst[index] = e;
        System.arraycopy(list, index, lst, index + 1, size() - index);
        return new ImmutableArrayList(lst);
    }

    private Object[] addElems(Object[] lst, int index, Object[] copyFrom) {
        int i = index;
        for (Object el : copyFrom) {

            lst[i] = el;
            i++;
        }
        return lst;
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        Object[] lst = new Object[size() + c.length];
        System.arraycopy(list, 0, lst, 0, size());

        return new ImmutableArrayList(addElems(lst, size(), c));
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (index == size()) {
            return addAll(c);
        }
        checkIndex(index);
        Object[] lst = new Object[size() + c.length];
        System.arraycopy(list, 0, lst, 0, index);
        addElems(lst, index, c);
        for (int k = index; k < size(); k++) {
            lst[k + c.length] = list[k];
        }
        return new ImmutableArrayList(lst);
    }


    @Override
    public Object get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        checkIndex(index);
        Object[] lst = new Object[size() - 1];
        System.arraycopy(list, 0, lst, 0, index);
        System.arraycopy(list, index + 1, lst, index, size() - 1 - index);

        return new ImmutableArrayList(lst);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        checkIndex(index);
        ImmutableArrayList copy = new ImmutableArrayList(list);
        copy.list[index] = e;
        return copy;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size(); i++) {
            if (list[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] lst = new Object[size()];

        for (int i = 0; i < size(); i++) {
            lst[i] = list[i];
        }

        return lst;
    }

    @Override
    public String toString() {
        if (size() != 0) {
            String toReturn;
            int k = 0;
            StringBuilder toReturnBuilder = new StringBuilder();
            for (int i = 0; i < size() - 1; i++) {
                toReturnBuilder.append(list[i]);
                toReturnBuilder.append(", ");
                k = i + 1;
            }
            toReturn = toReturnBuilder.toString();
            toReturn += list[k];
            return toReturn;
        } else {
            return "";
        }
    }
}
