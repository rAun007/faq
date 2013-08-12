package com.raunak.threading;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSetUsingSemaphore<K> {

    private Set<K> set;

    Semaphore semaphore;

    public BoundedHashSetUsingSemaphore(int bound) {

        set = Collections.synchronizedSet(new HashSet<K>());
        semaphore = new Semaphore(bound);
    }

    public void add(K o) throws InterruptedException {

        semaphore.acquire();

        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
        } finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    public void remove(K o) {

        boolean wasRemoved = set.remove(o);

        if (wasRemoved) {
            semaphore.release();
        }
    }
}
