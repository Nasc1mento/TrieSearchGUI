package com.example.app.models;

import java.util.HashMap;
import java.util.Map;

public class Node <T> {

    private final Map<Character, Node<T>> children;
    private boolean isEndOfWord;
    T value; // url pra mostrar no front

    public Node() {
        this.children = new HashMap<>();
    }

    public Map<Character, Node<T>> getChildren() {
        return this.children;
    }

    public boolean getIsEndOfWord() {
        return this.isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}
