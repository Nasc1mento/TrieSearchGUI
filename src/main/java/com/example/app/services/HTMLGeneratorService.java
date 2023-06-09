package com.example.app.services;

import com.example.app.datastructures.*;
import com.example.app.models.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HTMLGeneratorService {

    private TrieTree tree;

    @Autowired
    public HTMLGeneratorService(TrieTree tree) {
        this.tree = tree;
    }

    public String treeSearchAsTable(String param) {
        LinkedList<Site> searchResults = this.tree.search(param.toLowerCase());
        StringBuilder sb = new StringBuilder();
        sb.append("<table><thead><tr><th>Título</th><th>URL</th></tr></thead><tbody>");
        if (searchResults != null) {
            NodeLL<Site> current = searchResults.getHead();
            while (current != null) {
                sb.append("<tr><td>")
                    .append(current.getValue().getTitle())
                    .append("</td>")
                    .append("<td><a href='")
                    .append(current.getValue().getUrl())
                    .append("'>").append(current.getValue().getUrl())
                    .append("</a></td></tr>");
                current = current.getNext();
            }
        } else
            sb.append("<tr><td>Não encontramos nada</td><td>...</td></tr>");

        sb.append("</tbody></table>");
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
        LinkedList<Site> siteList = node.getSiteList();
        NodeLL<Site> currentSite= siteList.getHead();
        sb.append("<tr><td>")
            .append(word)
            .append("</td>")
            .append("<td>");

        while (currentSite != null) {
            sb.append("<a href='")
                .append(currentSite.getValue().getUrl())
                .append("'>")
                .append(currentSite.getValue().getUrl())
                .append("<br>")
                .append("</a>");
            currentSite = currentSite.getNext();
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
