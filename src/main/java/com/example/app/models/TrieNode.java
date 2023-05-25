package com.example.app.models;


import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {

    private final Map<Character, TrieNode<T>> children;
    private boolean isEndOfWord;
    private LinkedList<String> urls;

    public TrieNode() {
        this.children = new HashMap<>();
        this.urls = new LinkedList<>();
    }

    public Map<Character, TrieNode<T>> getChildren() {
        return this.children;
    }

    public boolean getIsEndOfWord() {
        return this.isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public LinkedList<String> getValue() {
        return this.urls;
    }

    public void setValue(String value) {
        this.urls.add(value);
    }
}
