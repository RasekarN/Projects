package com.RasekarN.cache;

import com.RasekarN.cache.exceptions.NotFoundException;
import com.RasekarN.cache.exceptions.InvalidStateException;
import com.RasekarN.cache.exceptions.NotFoundException;
import com.RasekarN.cache.exceptions.StorageFullException;
import com.RasekarN.cache.policies.EvictionPolicy;
import com.RasekarN.cache.storage.Storage;

import java.util.Objects;

public class Cache<Key, Value> {
    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public Value get(final Key key) {
        try {
            Value value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException keyNotFoundException) {
            System.out.println("Hit a cahce miss  for key " + key);
        }
        return null;
    }

    public void put(final Key key, final Value value) {
        try {
            storage.add(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException storageFullException) {
            System.out.println("Got storage full! Trying to evict");
            Key keyToBeRemoved = evictionPolicy.evict();
            if (Objects.isNull(keyToBeRemoved)) {
                throw new InvalidStateException("Invalid State! No storage space left and no keys to evict");
            }
            storage.remove(keyToBeRemoved);
            System.out.println("Evicting key " + keyToBeRemoved);
            put(key, value);
        }
    }
}