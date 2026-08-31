package com.smconsulting.backend.controller;

import com.smconsulting.backend.dto.AboutSectionResponse;
import com.smconsulting.backend.service.AboutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutController {

    private final AboutService aboutService;

    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping("/api/vitrine/about")
    public AboutSectionResponse getAbout(@RequestParam(defaultValue = "home") String page) {
        return aboutService.getAboutSection(page);
    }
}