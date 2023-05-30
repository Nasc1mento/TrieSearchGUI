package com.example.app.controllers;

import com.example.app.services.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final TreeService treeService;

    @Autowired
    public SearchController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "param", defaultValue = " ") String param) {
        return treeService.treeSearchAsList(param);
    }

    @GetMapping("/table-tree")
    public String activateFunction() {
        return treeService.treeAsTable();
    }
}
