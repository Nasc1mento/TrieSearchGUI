package com.example.app.datastructures;


import com.example.app.models.Site;

public class NodeTT {

    private final LinkedListTT children;
    private boolean isEndOfWord;
    private final LinkedList<Site> siteList;
    private char key;

    public NodeTT() {
        this.children = new LinkedListTT();
        this.siteList = new LinkedList<>();
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

    public LinkedList<Site> getSiteList() {
        return this.siteList;
    }

    public void site(Site site) {
        this.siteList.add(site);
    }

    public char getKey() {
        return this.key;
    }

    public void setKey(char key) {
        this.key = key;
    }

}
