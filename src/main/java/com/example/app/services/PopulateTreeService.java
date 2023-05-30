package com.example.app.services;

import com.example.app.datastructures.*;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Pattern;

@Service
public class PopulateTreeService {

    private final TrieTree tree;
    private final String[] urls;

    @Autowired
    public PopulateTreeService(TrieTree tree, @Qualifier("urls") String[] urls) {
        this.tree = tree;
        this.urls = urls;
    }

    @PostConstruct
    public void populateTree() {

        for (String url : this.urls) {
            Document document;
            try {
                document = Jsoup.connect(url).get();
            } catch (IOException e) {
                System.out.println("Erro ao conectar com a URL: " + url);
                continue;
            }
            String text = document.text();
            // Pega tudo exceto numeros(digit), pra pegar colocar \\d.
            Pattern pattern = Pattern.compile("[^\\p{L}]");
            String[] words = pattern.split(text);

            for (String word : words) {
                if (!word.isEmpty())
                    this.tree.insert(word.toLowerCase(), url);
            }
        }
    }
}
