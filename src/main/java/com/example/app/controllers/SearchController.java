package com.example.app.controllers;

import com.example.app.models.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final Tree tree;

    @Autowired
    public SearchController() {
        this.tree = new Tree();
        tree.insert("agua");
    }

    @GetMapping("/search")
    public boolean search(@RequestParam(value = "param") String param) {
        return tree.search(param);
    }
}
