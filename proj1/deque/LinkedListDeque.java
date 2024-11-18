package deque;

public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T data;
        private Node next;

        public Node(T data) {
            this.next = null;
            this.prev = null;
            this.data = data;
        }
    }

    private Node sentinel;
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
        sentinel.next = node;

        size++;
    }

    public void addLast(T item) {
        var node = new Node(item);
        if (size == 0) {
            sentinel.next = node;
        }
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.prev = node;
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
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        lld1.addFirst("hi");
        lld1.addFirst("hi2");
        lld1.printDeque();
    }
}
