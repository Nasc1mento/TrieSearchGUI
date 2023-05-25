package com.example.app.models;

public class LinkedList <T>{

    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    public LinkedList() {
        this.head = tail = null;
    }

    public LinkedListNode<T> getHead() {
        return this.head;
    }

    public void add(T value) {
        LinkedListNode<T> newNode = new LinkedListNode<>(value);
        if (isEmpty()) {
            this.head = tail = newNode;
            return;
        }
        this.tail.setNext(newNode);
        this.tail = tail.getNext();
    }

    public boolean contains(T value) {
        LinkedListNode<T> currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.getValue() == value) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public boolean isEmpty() {
        return this.head == null;
    }
}
