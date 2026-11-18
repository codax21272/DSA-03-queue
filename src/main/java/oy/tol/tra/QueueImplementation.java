package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

    private Object[] itemArray;
    private int head;
    private int tail;
    private int count;

    public QueueImplementation(int capacity) {
        itemArray = new Object[capacity];
        head = 0;
        tail = 0;
        count = 0;
    }




    @Override
    public int capacity() {
        return itemArray.length;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }


    @Override
    public void enqueue(E element) throws QueueAllocationException {
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null");
        }


        if (count == itemArray.length) {

            int newCapacity;

            if (itemArray.length == 0) {
                newCapacity = 1;
            } else {  
                newCapacity = itemArray.length * 2;
            }

            Object[] newArray = new Object[newCapacity];

            for (int i = 0; i < count; i++) {
                newArray[i] = itemArray[(head + i) % itemArray.length];
            }

            itemArray = newArray;

            head = 0;
            tail = count;
        }


        itemArray[tail] = element;
        tail = (tail + 1) % itemArray.length;
        count++;
    }


    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() throws QueueIsEmptyException {

        if (count == 0) {
            throw new QueueIsEmptyException("Queue is empty");
        }

        E removedElement = (E) itemArray[head];

        itemArray[head] = null;

        head = (head + 1) % itemArray.length;

        count--;

        return removedElement;
    }



    @Override
    @SuppressWarnings("unchecked")
    public E element() throws QueueIsEmptyException {

        if (count == 0) {
            throw new QueueIsEmptyException("Queue is empty");
        }

        return (E) itemArray[head];
    }

    @Override
    public void clear() {

        itemArray = new Object[itemArray.length];
        head = 0;
        tail = 0;
        count = 0;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("[");
        
        for (int i = 0; i < count; i++) {

            if (i > 0) {
                result.append(", ");
            }

            result.append(itemArray[(head + i) % itemArray.length]);
        }

        result.append("]");
        return result.toString();
    }
}   