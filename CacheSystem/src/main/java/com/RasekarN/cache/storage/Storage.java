package com.RasekarN.cache.storage;

import com.RasekarN.cache.exceptions.NotFoundException;
import com.RasekarN.cache.exceptions.StorageFullException;


public interface Storage<Key,Value> {
    void add(Key key, Value value) throws StorageFullException;
    void remove(Key key) ;
    Value get(Key key) throws NotFoundException;
}
