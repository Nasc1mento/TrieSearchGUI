package com.example.app.initializer;

import com.example.app.datastructures.*;
import jakarta.annotation.PostConstruct;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Component
public class TreePopulatorInitializer {

    private final TrieTree tree;
    private final String[] urls;
    private final ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    public TreePopulatorInitializer(TrieTree tree, String[] urls, @Qualifier("taskExecutor") ThreadPoolTaskExecutor taskExecutor) {
        this.tree = tree;
        this.urls = urls;
        this.taskExecutor = taskExecutor;
    }

    @PostConstruct
    public void initialize() {
        LinkedList<String> visited = new LinkedList<>();
        for (String url: this.urls) {
            String domain = this.extractDomain(url);
            this.taskExecutor.execute(()-> performCrawl(0, url, domain, visited));
        }
    }

    public void addToTree(String url, String[] words, String title) {
        for (String word : words) {
            if (!word.isEmpty())
                this.tree.insert(word.toLowerCase(), url, title);
        }
    }

    public Document sendRequest(String url, LinkedList<String> visited) {
        try{
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            if (connection.response().statusCode() == 200) {
                visited.add(url);
                System.out.println("Link: " + url);
                System.out.println("Título: " + document.title());
                return document;
            }
        }catch (IOException e){
            System.out.println("Erro ao conectar com a URL: " + url);
        }

        return null;
    }

    public void performCrawl(int level, String url, String domain, LinkedList<String> visited) {

        final int MAX_LEVEL = 5;
        final String extractedDomain = this.extractDomain(url);

        if (level <= MAX_LEVEL) {
            if (extractedDomain == null || !extractedDomain.equals(domain) || visited.contains(url))
                return;
            Document document = this.sendRequest(url, visited);
            if (document != null) {
                for (Element element : document.select("a[href]")) {
                    String nextUrl = element.absUrl("href");
                    String[] words = this.tokenizeText(document.text());
                    String title = document.title();
                    this.addToTree(url, words, title);
                    if (!visited.contains(nextUrl))
                        this.taskExecutor.execute(()->performCrawl(level + 1, nextUrl, domain, visited));
                }
            }
        }
    }

    public String[] tokenizeText(String text) {
        Pattern pattern = Pattern.compile("[^\\p{L}]");
        return pattern.split(text);
    }

    public String extractDomain(String url) {
        try{
            URI uri = new URI(url);
            return uri.getHost();
        }catch(URISyntaxException e){
           return null;
        }
    }
}
