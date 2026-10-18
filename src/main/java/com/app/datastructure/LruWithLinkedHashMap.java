package com.app.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruWithLinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    int capacity;
    LruWithLinkedHashMap(int capacity){
        super(capacity, 0.75f, true);
        this.capacity=capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
        return size() > capacity;
    }

    public static void main(String[] args) {
        LruWithLinkedHashMap<Integer,Integer> map=new LruWithLinkedHashMap<>(2);
        map.put(1, 1);
        map.put(2, 2);
        map.get(1);
        map.put(3, 3);

        map.forEach((key, value) -> {
            System.out.println("key::" + key);
            System.out.println("value::" + value);
        });
    }

}
