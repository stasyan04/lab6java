package com.stanislav.labwork6;

import java.util.*;

public class CandyList implements List<Candy> {

    private static class Node {
        Candy item;
        Node prev;
        Node next;

        Node(Node prev, Candy item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CandyList() {
    }

    public CandyList(Candy candy) {
        add(candy);
    }

    public CandyList(Collection<? extends Candy> c) {
        addAll(c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<Candy> iterator() {
        return new Iterator<Candy>() {
            Node current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Candy next() {
                if (!hasNext()) throw new NoSuchElementException();
                Candy item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (Candy c : this) {
            arr[i++] = c;
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Candy c : this) {
            result[i++] = c;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(Candy candy) {
        final Node oldTail = tail;
        final Node newNode = new Node(oldTail, candy, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node current = head;
        while (current != null) {
            if (Objects.equals(current.item, o)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private Candy unlink(Node x) {
        Candy item = x.item;
        Node prev = x.prev;
        Node next = x.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return item;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Candy> c) {
        boolean modified = false;
        for (Candy candy : c) {
            add(candy);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Candy> c) {
        checkPositionIndex(index);
        if (c.isEmpty()) return false;
        Node nodeAtIndex = (index == size) ? null : node(index);
        Node prev = (nodeAtIndex == null) ? tail : nodeAtIndex.prev;

        for (Candy candy : c) {
            Node newNode = new Node(prev, candy, null);
            if (prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
            prev = newNode;
            size++;
        }

        if (nodeAtIndex != null) {
            nodeAtIndex.prev = prev;
            prev.next = nodeAtIndex;
        } else {
            tail = prev;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator<Candy> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<Candy> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.item = null;
            current.prev = null;
            current.next = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public Candy get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public Candy set(int index, Candy element) {
        checkElementIndex(index);
        Node n = node(index);
        Candy oldVal = n.item;
        n.item = element;
        return oldVal;
    }

    @Override
    public void add(int index, Candy element) {
        checkPositionIndex(index);
        if (index == size) {
            add(element);
        } else {
            Node succ = node(index);
            Node pred = succ.prev;
            Node newNode = new Node(pred, element, succ);
            succ.prev = newNode;
            if (pred == null) {
                head = newNode;
            } else {
                pred.next = newNode;
            }
            size++;
        }
    }

    @Override
    public Candy remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Candy c : this) {
            if (Objects.equals(c, o)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        Node current = tail;
        while (current != null) {
            if (Objects.equals(current.item, o)) return index;
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<Candy> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Candy> listIterator(int index) {
        checkPositionIndex(index);
        return new ListIterator<Candy>() {
            Node current = (index == size) ? null : node(index);
            int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Candy next() {
                if (!hasNext()) throw new NoSuchElementException();
                Candy item = current.item;
                current = current.next;
                currentIndex++;
                return item;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public Candy previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                current = (current == null) ? tail : current.prev;
                Candy item = current.item;
                currentIndex--;
                return item;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (current == null || size == 0) throw new IllegalStateException();
                Node toRemove = (current == null) ? tail : current.prev;
                CandyList.this.unlink(toRemove);
                currentIndex--;
            }

            @Override
            public void set(Candy candy) {
                if (current == null) throw new IllegalStateException();
                Node prevNode = (current == null) ? tail : current.prev;
                if (prevNode == null) throw new IllegalStateException();
                prevNode.item = candy;
            }

            @Override
            public void add(Candy candy) {
                Node prevNode = (current == null) ? tail : current.prev;
                Node newNode = new Node(prevNode, candy, current);
                if (prevNode == null) {
                    head = newNode;
                } else {
                    prevNode.next = newNode;
                }
                if (current != null) {
                    current.prev = newNode;
                } else {
                    tail = newNode;
                }
                size++;
                currentIndex++;
            }
        };
    }

    @Override
    public List<Candy> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList не реалізовано.");
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private Node node(int index) {
        if (index < (size >> 1)) {
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
}