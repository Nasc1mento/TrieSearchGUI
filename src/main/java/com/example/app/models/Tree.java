package com.example.app.models;

import java.util.Map;

public class Tree <T> {

    private final Node<T> root;

    public Tree() {
        this.root = new Node<>();
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public void insert(String word) {
        Node<T> currentNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            Node<T> child = currentNode.getChildren().get(currentChar);
            if (child == null) {
                child = new Node<>();
                currentNode.getChildren().put(currentChar, child);
            }
            currentNode = child;
        }
        currentNode.setEndOfWord(true);
    }

    public boolean search(String word) {
        Map<Character, Node<T>> currentChildren = root.getChildren();
        Node<T> currentNode = null;

        for (char c : word.toCharArray()) {
            if (currentChildren.containsKey(c)) {
                currentNode = currentChildren.get(c);
                currentChildren = currentNode.getChildren();
            } else {
                return false;
            }
        }
        return true;
    }
}
