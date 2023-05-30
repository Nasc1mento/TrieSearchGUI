package com.example.app.services;

import com.example.app.datastructures.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Pattern;

@Service
public class TreeService {

    private final TrieTree tree;
    private final String[] urls;

    @Autowired
    public TreeService(TrieTree tree, @Qualifier("urls") String[] urls) {
        this.tree = tree;
        this.urls = urls;
        this.populateTree();
    }

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

    public String treeSearchAsList(String param) {
        LinkedList<String> searchResults = this.tree.search(param.toLowerCase());
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        if (searchResults != null) {
            NodeLL<String> current = searchResults.getHead();
            while (current != null) {
                sb.append("<li><a href='")
                    .append(current.getValue())
                    .append("'>").append(current.getValue())
                    .append("</a></li>");
                current = current.getNext();
            }
        } else
            sb.append("<li>Não encontramos nada</li>");

        sb.append("</ul>");
        return sb.toString();
    }


    public String treeAsTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table><thead><tr><th>Conteúdo</th><th>URLs</th></tr></thead><tbody>");
        treeAsTable(this.tree.getRoot(), "", sb);
        sb.append("</tbody></table>");
        return sb.toString();
    }

    private void treeAsTable(NodeTT node, String word, StringBuilder sb) {

    }
}
