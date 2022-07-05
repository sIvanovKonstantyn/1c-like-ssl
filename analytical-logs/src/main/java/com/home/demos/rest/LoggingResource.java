package main.java.com.home.demos.rest;

import main.java.com.home.demos.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
