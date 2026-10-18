package com.app.datastructure;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> data;
    int capacity;
    Node head, tail;

    LRUCache(int capacity) {
        this.capacity = capacity;
        data = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value) {
        if (data.containsKey(key)) {
            Node node = data.get(key);
            remove(node);
        }
        Node newNode = new Node(key, value);
        data.put(key, newNode);
        addNode(newNode);

        if (data.size() > capacity) {
            Node nodeToDelete = head.next;
            data.remove(nodeToDelete.key);
            remove(nodeToDelete);
        }
    }

    public int get(int key) {
        if (data.containsKey(key)) {
            Node node= data.get(key);
            remove(node);
            addNode(node);
            return node.value;
        }
        return -1;
    }

    public void addNode(Node node) {
        Node previousTail = tail.prev;
        previousTail.next=node;
        node.prev = previousTail;
        tail.prev = node;
        node.next = tail;


    }

    public void remove(Node data) {
        data.next.prev = data.prev;
        data.prev.next = data.next;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);

        lruCache.data.forEach((key, value) -> {
            System.out.println("key::" + key);
            System.out.println("value::" + value);

        });

    }
}

class Node {
    int key, value;
    Node next, prev;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }


}
