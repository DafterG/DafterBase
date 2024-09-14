package a01;

import java.util.NoSuchElementException;

/**
 * NumberList is a singly-linked list of double values.
 * It is designed as a practice opportunity to learn how to manipulate linked structures.
 * 
 * @author CS Starter Code + [Your Name]
 * @see <a href="URL_to_NumberList.java">NumberList.java</a>
 * @see <a href="URL_to_StackQueueChallenges.java">StackQueueChallenges.java</a>
 */
public class NumberList {
    private Node head; // first node of the list or null
    private Node tail; // last node of the list or null
    private int n;     // number of elements in the list

    /**
     * Node of LinkedList that stores the item and a
     * single reference to the next node.
     */
    private static class Node {
        private double item;
        private Node next;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void add(double item) {
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        n++;
    }

    public double firstElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return head.item;
    }

    public boolean endsNegative() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.item < 0;
    }

    public double product() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        double product = 1;
        Node current = head;
        while (current != null) {
            product *= current.item;
            current = current.next;
        }
        return product;
    }

    public void insert(int index, double item) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node newNode = new Node();
        newNode.item = item;

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (n == 0) {
                tail = newNode;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
        n++;
    }

    public void rotateLeft(int positions) {
        if (isEmpty() || positions <= 0) {
            return;
        }

        positions = positions % n;
        if (positions == 0) {
            return;
        }

        Node current = head;
        for (int i = 0; i < positions - 1; i++) {
            current = current.next;
        }

        Node newHead = current.next;
        current.next = null;
        tail.next = head;
        head = newHead;
        tail = current;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;

        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }

        return sb.toString().trim();
    }

    /* * * * * * * * Test Client * * * * * * */
    public static void main(String[] args) {
        // NumberList tests
        NumberList list = new NumberList();
        list.add(1.1);
        list.add(2.2);
        list.add(3.3);
        list.add(4.4);
        System.out.println("First Element: " + list.firstElement()); // Expected: 1.1
        System.out.println("Ends Negative: " + list.endsNegative()); // Expected: false
        System.out.println("Product: " + list.product()); // Expected: 35.222
        list.insert(2, 5.5);
        System.out.println("After Insert: " + list); // Expected: 1.1 2.2 5.5 3.3 4.4
        list.rotateLeft(2);
        System.out.println("After Rotate: " + list); // Expected: 5.5 3.3 4.4 1.1 2.2
    }
}


