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
        Map<Character, Node<T>> currentChildren = root.getChildren();
        for (char c : word.toCharArray()) {
            Node node;
            if (currentChildren.containsKey(c)) {
                node = currentChildren.get(c);
            } else {
                node = new Node();
                currentChildren.put(c, node);
            }
            currentChildren = node.getChildren();

            if (c == word.charAt(word.length() - 1)) {
                node.setEndOfWord(true);
            }
        }
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
