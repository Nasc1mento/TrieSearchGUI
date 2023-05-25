package com.example.app.models;

import java.util.Map;

public class TrieTree<T> {

    private final TrieNode<T> root;

    public TrieTree() {
        this.root = new TrieNode<>();
    }

    public void insert(String word, String url) {
        Map<Character, TrieNode<T>> currentChildren = root.getChildren();
        for (char c : word.toCharArray()) {
            TrieNode node;
            if (currentChildren.containsKey(c)) {
                node = currentChildren.get(c);
                if (!node.getValue().contains(url))
                    node.setValue(url);
            } else {
                node = new TrieNode();
                node.setValue(url);
                currentChildren.put(c, node);
            }
            currentChildren = node.getChildren();

            if (c == word.charAt(word.length() - 1)) {
                node.setEndOfWord(true);
            }
        }
    }

    public LinkedList<String> search(String word) {
        Map<Character, TrieNode<T>> currentChildren = root.getChildren();
        TrieNode<T> currentNode = null;

        for (char c : word.toCharArray()) {
            if (currentChildren.containsKey(c)) {
                currentNode = currentChildren.get(c);
                currentChildren = currentNode.getChildren();
            } else {
                return null;
            }
        }
        return currentNode.getValue();
    }
}
