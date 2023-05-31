package com.example.app.datastructures;


public class NodeTT {

    private final LinkedListTT children;
    private boolean isEndOfWord;
    private final LinkedList<String> urls;
    private char key;

    public NodeTT() {
        this.children = new LinkedListTT();
        this.urls = new LinkedList<>();
    }

    public LinkedListTT getChildren() {
        return this.children;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public LinkedList<String> getUrls() {
        return this.urls;
    }

    public void addUrl(String value) {
        this.urls.add(value);
    }

    public char getKey() {
        return this.key;
    }

    public void setKey(char key) {
        this.key = key;
    }

}
