package com.RasekarN.cache.storage;

import com.RasekarN.cache.exceptions.NotFoundException;
import com.RasekarN.cache.exceptions.StorageFullException;

import java.util.HashMap;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {
    private final HashMap<Key, Value> storage;
    private final int capacity;

    public HashMapStorage(int capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
    }

    @Override
    public void add(final Key key, final Value value) throws StorageFullException {
        if (storageFull())
            throw new StorageFullException("Capacity Reached");
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "doesn't exist in cache.");
        storage.remove(key);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key))
            throw new NotFoundException("Key " + key+ " Not Found");
        return storage.get(key);
    }

    private boolean storageFull() {
        return storage.size() == capacity;
    }
}