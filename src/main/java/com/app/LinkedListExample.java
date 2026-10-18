package com.app;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.insert(5);
        ll.insert(10);
        ll.insert(15);
        ll.insert(40);
        LinkedList ll1 = new LinkedList();
        ll1.insert(2);
        ll1.insert(3);
        ll1.insert(20);
        LinkedList l3 = merge(ll, ll1);
        l3.printLL();
    }

    static LinkedList merge(LinkedList l, LinkedList l2) {
        LinkedList l3 = new LinkedList();
        Node node1 = l.head;
        Node node2 = l2.head;
        while (node1 != null && node2 != null) {
            if (node1.element >= node2.element) {
                l3.insert(node2.element);
                node2 = node2.next;
            } else {
                l3.insert(node1.element);
                node1 = node1.next;
            }
        }
        if (node1 != null) {
            l3.insert(node1.element);
        }
        if (node2 != null) {
            l3.insert(node2.element);
        }
        return l3;
    }

    public static class Node {
        int element;
        Node next;
        public Node(int element) {
            this.element = element;
        }
    }

    public static class LinkedList {
        Node head;

        void insert(int element) {
            Node n = new Node(element);
            if (head == null) {
                head = n;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = n;
            }
        }
        void printLL() {
            Node current = head;
            while (current != null) {
                System.out.println(current.element);
                current = current.next;
            }
        }
    }
}

