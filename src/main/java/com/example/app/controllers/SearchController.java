package com.example.app.controllers;

import com.example.app.models.LinkedList;
import com.example.app.models.LinkedListNode;
import com.example.app.models.TrieTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.regex.Pattern;

@RestController
public class SearchController {

    private final TrieTree tree;

    @Autowired
    public SearchController() throws IOException {

        String url = "https://en.wikipedia.org/wiki/Trie";
        String url2 = "https://pt.wikipedia.org/wiki/Business_Process_Model_and_Notation";

        Document document = Jsoup.connect(url).get();
        Document document2 = Jsoup.connect(url2).get();
        String text = document.text();
        String text2 = document2.text();

        Pattern pattern = Pattern.compile("\\W+");


        String[] words = pattern.split(text);
        String[] words2 = pattern.split(text2);


        this.tree = new TrieTree();

        for (String word : words) {
            if (!word.isEmpty()) {
                tree.insert(word.toLowerCase(), url);
            }
        }

        for (String word : words2) {
            if (!word.isEmpty()) {
                tree.insert(word.toLowerCase(), url2);
            }
        }
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "param") String param) {
        LinkedList<String> searchResults = tree.search(param.toLowerCase());
        StringBuilder sb = new StringBuilder();

        sb.append("<html><body><ul>");

        if (searchResults != null) {
            LinkedListNode current = searchResults.getHead();
            while (current != null) {
                sb.append("<li><a href=\"").append(current.getValue()).append("\">").append(current.getValue()).append("</a></li>");
                current = current.getNext();
            }
        } else {
            sb.append("<li>Não encontramos nada</li>");
        }
        sb.append("</ul></body></html>");
        return sb.toString();
    }
}
