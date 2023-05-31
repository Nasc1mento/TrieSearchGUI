package com.example.app.datastructures;

public class NodeLL<T> {

    private T value;
    private NodeLL<T> next;

    public NodeLL(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return this.value;
    }

    public NodeLL<T> getNext() {
        return this.next;
    }

    public void setNext(NodeLL<T> next) {
        this.next = next;
    }

}
