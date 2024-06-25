import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private final Node _sentinel;
    private int _size;

    private class Node {
        private Node _prev;
        private Node _next;
        private T _item;

        public Node() {
            _prev = this;
            _next = this;
        }

        public Node(T item) {
            _item = item;
            _prev = this;
            _next = this;
        }

        public Node(T item, Node prev, Node next) {
            _item = item;
            _next = next;
            _prev = prev;
        }
    }

    public LinkedListDeque61B() {
        _sentinel = new Node();
        _size = 0;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node node;
        if (_size == 0) {
            node = new Node(x, _sentinel, _sentinel);
            _sentinel._prev = node;
        } else {
            node = new Node(x, _sentinel, _sentinel._next);
            _sentinel._next._prev = node;
        }
        _sentinel._next = node;
        _size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node node;
        if (_size == 0) {
            node = new Node(x, _sentinel, _sentinel);
            _sentinel._next = node;
        } else {
            node = new Node(x, _sentinel._prev, _sentinel);
            _sentinel._prev._next = node;
        }
        _sentinel._prev = node;
        _size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node current = _sentinel._next;
        while (current != _sentinel) {
            result.add(current._item);
            current = current._next;
        }
        return result;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return _size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node first = _sentinel._next;

        if (_size == 1) {
            _sentinel._next = _sentinel;
            _sentinel._prev = _sentinel;
            first._next = null;
            first._prev = null;
        } else {
            _sentinel._next = first._next;
            first._next._prev = _sentinel;
        }
        _size--;
        return first._item;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node last = _sentinel._next;

        if (_size == 1) {
            _sentinel._next = _sentinel;
            _sentinel._prev = _sentinel;
            last._next = null;
            last._prev = null;
        } else {
            _sentinel._next = last._next;
            last._next._prev = _sentinel;
        }
        _size--;
        return last._item;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= _size) {
            return null;
        }

        Node current = _sentinel._next;
        for (int i = 0; i < index; i++) {
            current = current._next;
        }

        return current._item;
    }

    private T getRecursive(int index, Node node) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= _size) {
            return null;
        }

        if (index == 0) {
            return node._item;
        } else {
            return getRecursive(--index, node._next);
        }
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        return getRecursive(index, _sentinel._next);
    }
}
