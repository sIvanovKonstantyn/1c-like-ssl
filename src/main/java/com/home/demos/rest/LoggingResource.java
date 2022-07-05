package com.home.demos.rest;

import com.home.demos.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logging")
@CrossOrigin(origins = "http://localhost:3000")
public class LoggingResource {

    @Autowired
    private LoggingService loggingService;

    @GetMapping
    public String searchByFilterText(@RequestParam(required = false) String filterText) {
        return loggingService.search(filterText);
    }
}
