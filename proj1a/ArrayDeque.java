public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /* Resizes the underlying array deque to the target capacity.*/
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if (size == 0) {
            nextFirst = 0;
            nextLast = 1;
            return;
        }
        if (minusOne(nextLast) > plusOne(nextFirst)) {
            System.arraycopy(items, plusOne(nextFirst), temp, 1, size);
        } else {
            int length1 = items.length - nextFirst - 1;
            System.arraycopy(items, plusOne(nextFirst), temp, 1, length1);
            System.arraycopy(items, 0, temp, items.length - nextFirst, size - length1);
        }
        items = temp;
        nextFirst = 0;
        nextLast = size + 1;
    }

    /* Computes the index before a given index.*/
    private int minusOne(int index) {
        return index == 0 ? items.length - 1 : index - 1;
    }

    /* Computes the index after a given index.*/
    private int plusOne(int index) {
        return index == items.length - 1 ? 0 : index + 1;
    }

    /* Inserts X into the front of the list.*/
    public void addFirst(T x) {
        if (plusOne(nextLast) == nextFirst) {
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /* Inserts X into the back of the list.*/
    public void addLast(T x) {
        if (plusOne(nextLast) == nextFirst) {
            resize(items.length * 2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /* Returns if this is an empty deque.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /* Returns the size of the deque.*/
    public int size() {
        return size;
    }

    /* Prints the deque from the first item till the last.*/
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.print("\n");
    }

    /* Removes the first item of the list and returns this item.*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T p = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (size < 0.25 * items.length && items.length != 8) {
            resize((int) (0.5 * items.length));
        }
        return p;
    }

    /* Removes the last item of the list and returns this item.*/
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T p = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (size < 0.25 * items.length && items.length != 8) {
            resize((int) (0.5 * items.length));
        }
        return p;
    }

    /* Gets the item with required index.*/
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(nextFirst + index + 1) % items.length];
    }

}
