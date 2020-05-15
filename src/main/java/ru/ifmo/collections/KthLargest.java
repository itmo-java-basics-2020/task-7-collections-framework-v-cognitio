package ru.ifmo.collections;

import java.util.Map;
import java.util.TreeMap;

/**
 * Design a class to find the kth largest element in a stream. k is from 1 to numbers.length.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Constructor accepts an integer k and an integer array numbers, which contains initial elements from the stream.
 * For each call to the method KthLargest.add(), return the element representing the kth largest element in the stream.
 */
public class KthLargest {

    private TreeMap<Integer, Integer> map = new TreeMap<>();
    private int k;

    public KthLargest(int k, int[] numbers) {
        for (int a : numbers) {
            map.put(a, 1);
        }
        this.k = k;
    }

    public int add(int val) {
        Integer previousCount = map.get(val);
        map.put(val, previousCount != null ? previousCount + 1 : 1);

        var it = map.descendingMap();
        int elementRemaining = k;

        for (Map.Entry<Integer, Integer> entry : it.entrySet()) {
            for (int i = 0; i < entry.getValue(); ++i) {
                if (--elementRemaining == 0) {
                    return entry.getKey();
                }
            }
        }

        throw new IllegalArgumentException("There isn't " + k + "th element");
    }
}