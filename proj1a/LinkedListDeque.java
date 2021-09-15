public class LinkedListDeque<T> {

    private class Node {
        private Node front;
        private T item;
        private Node next;

        public Node(Node f, T i, Node n) {
            front = f;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /* construct a null deque */
    public LinkedListDeque() {
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.next = sentinel;
        sentinel.front = sentinel;
        size = 0;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.front = sentinel.next;
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        sentinel.front = new Node(sentinel.front, item, sentinel);
        sentinel.front.front.next = sentinel.front;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last,
    separated by a space.
     */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        p = sentinel.front;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.front;
        }
        System.out.print("\n");
    }

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T i = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.front = sentinel;
        size -= 1;
        return i;
    }

    /* Removes and returns the item at the back of the deque.
    If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T i = sentinel.front.item;
        sentinel.front = sentinel.front.front;
        sentinel.front.next = sentinel;
        size -= 1;
        return i;
    }

    /* Gets the item at the given index,
    where 0 is the front. If no such item exists, returns null.
    Must not alter the deque. Must use iteration, not recursion.
     */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node p = sentinel;
        if (index > size / 2) {
            int step = size - index;
            while (step > 0) {
                p = p.front;
                step -= 1;
            }
        } else {
            while (index >= 0) {
                p = p.next;
                index -= 1;
            }
        }
        return p.item;
    }

    /* Same as get, but uses recursion. */
    private T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        return getRecursive(p, index);
    }

    /* Helper method */
    public T getRecursive(Node n, int index) {
        if (index == 0) {
            return n.item;
        } else {
            index--;
            n = n.next;
        }
        return getRecursive(n, index);
    }
}
