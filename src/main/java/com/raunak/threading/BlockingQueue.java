package com.raunak.threading;

public class BlockingQueue<T> {

    private T[] arrayElements;

    private int limit = 100;

    private int currentSize = -1;

    @SuppressWarnings("unchecked")
    public BlockingQueue(int limit) {

        this.limit = limit;
        arrayElements = (T[]) new Object[limit];
    }

    public synchronized void enqueue(T element) throws InterruptedException {

        while (arrayElements.length == this.limit) {
            wait();
        }

        if (arrayElements.length == 0) {
            notifyAll();
        }

        arrayElements[++currentSize] = element;
    }

    public synchronized T dequeue() throws InterruptedException {

        while (arrayElements.length == 0) {
            wait();
        }

        if (arrayElements.length == limit) {
            notifyAll();
        }

        T element = arrayElements[currentSize--];
        return element;
    }
}
