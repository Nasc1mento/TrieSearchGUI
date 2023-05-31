package com.example.app.services;

import com.example.app.datastructures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HTMLGeneratorService {

    private TrieTree tree;

    @Autowired
    public HTMLGeneratorService(TrieTree tree) {
        this.tree = tree;
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
        String[] urlList = node.getUrls().list().split(" ");
        sb.append("<tr><td>")
            .append(word)
            .append("</td>")
            .append("<td>");
        for (String url: urlList) {
            sb.append("<a href='")
                    .append(url)
                    .append("'>")
                    .append(url)
                    .append("<br>")
                    .append("</a>");
        }

        LinkedListTT children = node.getChildren();
        NodeLL<NodeTT> currentNode = children.getHead();
        while (currentNode != null) {
            treeAsTable(currentNode.getValue(), word + currentNode.getValue().getKey(), sb);
            currentNode = currentNode.getNext();
            sb.append("</td>");
            sb.append("</tr></td>");
        }
    }
}
