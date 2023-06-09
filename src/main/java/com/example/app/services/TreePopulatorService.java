package com.example.app.services;

import com.example.app.datastructures.*;
import jakarta.annotation.PostConstruct;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

@Service
public class TreePopulatorService {

    private final TrieTree tree;
    private final String[] urls;
    private final ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    public TreePopulatorService(TrieTree tree, String[] urls, @Qualifier("taskExecutor") ThreadPoolTaskExecutor taskExecutor) {
        this.tree = tree;
        this.urls = urls;
        this.taskExecutor = taskExecutor;
    }

    @PostConstruct
    public void initialize() {
        LinkedList<String> visited = new LinkedList<>();
        for (String url: this.urls) {
            this.taskExecutor.execute(()-> performCrawl(0, url, visited));
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

    public void performCrawl(int level, String url, LinkedList<String> visited) {


        if (level <= 5) {
            if (!this.isSameDomain(url, "www.ifpe.edu.br"))
                return;
            Document document = this.sendRequest(url, visited);
            if (document != null) {
                for (Element element : document.select("a[href]")) {
                    String nextUrl = element.absUrl("href");
                    String[] words = this.tokenizeText(document.text());
                    String title = document.title();
                    this.addToTree(url, words, title);
                    if (!visited.contains(nextUrl))
                        this.taskExecutor.execute(()->performCrawl(level + 1, nextUrl, visited));
                }
            }
        }
    }

    public String[] tokenizeText(String text) {
        Pattern pattern = Pattern.compile("[^\\p{L}]");
        return pattern.split(text);
    }

    public boolean isSameDomain(String url, String domain) {
        URI uri;
        try {
            uri = new URI(url);
            String domainName = uri.getHost();
            return domainName.equals(domain);
        } catch (Exception e) {
            return false;
        }
    }
}
