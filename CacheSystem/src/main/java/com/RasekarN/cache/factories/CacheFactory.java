package com.RasekarN.cache.factories;


import com.RasekarN.cache.Cache;
import com.RasekarN.cache.policies.LRUEvictionPolicy;
import com.RasekarN.cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<>(new HashMapStorage<Key, Value>(capacity), new LRUEvictionPolicy<Key>());
    }
}