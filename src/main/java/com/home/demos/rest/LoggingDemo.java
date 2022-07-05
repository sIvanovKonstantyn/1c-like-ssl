package com.home.demos.rest;

import com.home.demos.documents.LoggingDemoDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logging/demo")
public class LoggingDemo {
    @Autowired
    private LoggingDemoDocument document;
    @GetMapping
    public void test() {
        document.saveWithoutActions();
        document.save();
        document.cancelActions();
        document.markAsRemoved();
    }
}
