package com.smconsulting.backend.controller;

import com.smconsulting.backend.dto.PageContentResponse;
import com.smconsulting.backend.service.PageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/api/vitrine/page")
    public PageContentResponse getPage(@RequestParam(defaultValue = "home") String slug) {
        return pageService.getPageContent(slug);
    }
}