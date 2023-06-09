package com.example.app.datastructures;

import com.example.app.models.Site;

public class TrieTree {

    private final NodeTT root;

    public TrieTree() {
        this.root = new NodeTT();
    }

    public NodeTT getRoot() {
        return this.root;
    }

    public void insert(String word, String url, String title) {
        LinkedListTT currentChildren = root.getChildren();

        char[] wordArray = word.toCharArray();
        Site site = new Site(url, title);
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            NodeTT node = currentChildren.get(c);
            if (node != null) {
                if (!node.getSiteList().contains(site))
                    node.site(site);
            } else {
                node = new NodeTT();
                node.site(site);
                currentChildren.put(c, node);
            }
            currentChildren = node.getChildren();

            if (i == wordArray.length - 1)
                node.setEndOfWord(true);
        }
    }

    public LinkedList<Site> search(String word) {
        LinkedListTT currentChildren = root.getChildren();
        NodeTT currentNode = null;

        for (char c : word.toCharArray()) {
            currentNode = currentChildren.get(c);
            if (currentNode != null)
                currentChildren = currentNode.getChildren();
            else
                return null;
        }
        return currentNode.getSiteList();
    }
}


