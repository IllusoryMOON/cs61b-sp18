public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /* Resizes the underlying array deque to the target capacity.*/
    private void resize(int capacity) {
        System.out.println("Resize method is called!");
        Item[] temp = (Item[]) new Object[capacity];
        if (minusOne(nextLast) >= nextFirst || plusOne(nextFirst) <= nextLast) {
            System.arraycopy(items, plusOne(nextFirst), temp, plusOne(nextFirst), size);
            nextLast = (nextFirst + size) % (capacity - 1);
        }
        else {
            System.arraycopy(items, 0, temp, 0, nextLast);
            System.arraycopy(items, nextFirst + 1, temp, capacity - (size - nextLast), size - nextLast);
            nextFirst = capacity - (size - nextLast) - 1;
        }
        items = temp;
        System.out.println("Now the length is: " + items.length);
    }

    /* Computes the index before a given index.*/
    int minusOne(int index) {
        return index == 0 ? items.length - 1 : index - 1;
    }

    /* Computes the index after a given index.*/
    int plusOne(int index) {
        return index == items.length - 1 ? 0 : index + 1;
    }

    /* Inserts X into the front of the list.*/
    public void addFirst(Item x) {
        if (plusOne(nextLast) == nextFirst) {
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /* Inserts X into the back of the list.*/
    public void addLast(Item x) {
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
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item p = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (size < 0.25*items.length && items.length != 8) {
            resize((int) (0.5*items.length));
        }
        return p;
    }

    /* Removes the last item of the list and returns this item.*/
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item p = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (size < 0.25*items.length && items.length != 8) {
            resize((int) (0.5*items.length));
        }
        return p;
    }

    /* Returns the item from the last of the list.*/
    public Item getLast() {
        return items[minusOne(nextLast)];
    }

    /* Returns the item from the first of the list.*/
    public Item getFirst() {
        return items[plusOne(nextFirst)];
    }

    /* Gets the item with required index.*/
    public Item get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(nextFirst + index + 1) % items.length];
    }

}
