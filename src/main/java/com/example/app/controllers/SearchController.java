package com.example.app.controllers;

import com.example.app.services.HTMLGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final HTMLGeneratorService htmlGeneratorService;

    @Autowired
    public SearchController(HTMLGeneratorService htmlGeneratorService) {
        this.htmlGeneratorService = htmlGeneratorService;
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "param", defaultValue = " ") String param) {
        return htmlGeneratorService.treeSearchAsList(param);
    }

    @GetMapping("/table-tree")
    public String activateFunction() {
        return htmlGeneratorService.treeAsTable();
    }
}
