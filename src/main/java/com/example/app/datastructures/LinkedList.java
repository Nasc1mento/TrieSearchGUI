package com.example.app.datastructures;

public class LinkedList <T>{

    protected NodeLL<T> head;
    protected NodeLL<T> tail;

    public LinkedList() {
        this.head = tail = null;
    }

    public NodeLL<T> getHead() {
        return this.head;
    }

    public void add(T value) {
        NodeLL<T> newNode = new NodeLL<>(value);
        if (isEmpty()) {
            this.head = tail = newNode;
            return;
        }
        this.tail.setNext(newNode);
        this.tail = tail.getNext();
    }

    public boolean contains(T value) {
        NodeLL<T> currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.getValue() == value)
                return true;

            currentNode = currentNode.getNext();
        }
        return false;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public String list() {
        NodeLL<T> currentNode = this.head;
        StringBuilder result = new StringBuilder() ;
        while (currentNode != null) {
            result.append(currentNode.getValue()).append(" ");
            currentNode = currentNode.getNext();
        }
        return result.toString();
    }
}
