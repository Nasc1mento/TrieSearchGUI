package com.example.app.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node<T> {

    private final Map<Character, Node<T>> children;
    private boolean isEndOfWord;
    private List<T> urls;

    public Node() {
        this.children = new HashMap<>();
        this.urls = new ArrayList<>();
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

    public List getValue() {
        return this.urls;
    }

    public void setValue(T value) {
        this.urls.add(value);
    }
}
