package com.example.app.models;

public class LinkedListNode <T> {

    private T value;
    private LinkedListNode<T> next;

    public LinkedListNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return this.value;
    }

    public LinkedListNode<T> getNext() {
        return this.next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }
}
