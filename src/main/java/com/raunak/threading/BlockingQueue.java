package com.raunak.threading;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {

    private List<T> queue = new LinkedList<T>();

    private int limit = 100;

    public BlockingQueue(int limit) {

        this.limit = limit;
    }

    public synchronized void enqueue(T element) throws InterruptedException {

        if (queue.size() == limit) {
            wait();
        }

        if (queue.size() == 0) {
            notifyAll();
        }

        queue.add(element);
    }

    public synchronized T dequeue() throws InterruptedException {

        if (queue.size() == 0) {
            wait();
        }

        if (queue.size() == limit) {
            notifyAll();
        }

        T element = queue.remove(0);
        return element;
    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> x = new BlockingQueue<String>(2);
        x.enqueue("1");
        x.enqueue("2");
        x.enqueue("3");
        System.out.println(x.queue);
    }
}
