package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private Node prev;
        private final T data;
        private Node next;

        public Node(T data) {
            this.next = null;
            this.prev = null;
            this.data = data;
        }
    }

    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        var node = new Node(item);
        if (size == 0) {
            sentinel.prev = node;
        }
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;

        size++;
    }

    public void addLast(T item) {
        var node = new Node(item);
        if (size == 0) {
            sentinel.next = node;
        }
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;

        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        var node = sentinel.next;
        sentinel.next = node.next;
        node.next.prev = sentinel;

        size--;
        return node.data;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        var node = sentinel.prev;
        sentinel.prev = node.prev;
        node.prev.next = sentinel;

        size--;
        return node.data;
    }

    /**
     * Get the item by index.
     *
     * @param index
     * @return item
     */
    @Override
    public T get(int index) {
        if (index >= size || isEmpty()) {
            return null;
        }
        var node = sentinel.next;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.data;
    }

    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }

        var node = getRecursive(sentinel.next, index);

        return node.data;
    }

    private Node getRecursive(Node cur, int index) {
        if (index == 0) {
            return cur;
        } else {
            return getRecursive(cur.next, index - 1);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        var node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addFirst("hi");
        lld1.addFirst("hi2");
        lld1.printDeque();
    }
}
