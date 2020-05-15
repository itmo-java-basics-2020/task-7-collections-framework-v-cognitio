package ru.ifmo.collections;

import java.util.LinkedHashMap;

/**
 * Represents LRU cache with fixed maximum capacity.
 * <p>
 * get() should return null if there is no value for a given key.
 * elements() should return number of elements in cache.
 * <p>
 * This class should not have any other public methods.
 * <p>
 * Implementing this cache in (almost) the same manner as it was implemented during the lecture will result in extra points.
 */
public class LruCache<K, V> {

    private LinkedHashMap<K, V> data;
    private int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.data = new LinkedHashMap<>(capacity);
    }

    public V get(K key) {
        if (data.containsKey(key)) {
            V value = data.get(key);
            data.remove(key);
            data.put(key, value);

            return value;
        }

        return null;
    }

    public void put(K key, V value) {
        if (this.get(key) == null) {
            if (data.size() == capacity) {
                var firstKey = data.entrySet().iterator().next().getKey();
                data.remove(firstKey);
            }
        }

        data.put(key, value);
    }

    public int elements() {
        return data.size();
    }
}