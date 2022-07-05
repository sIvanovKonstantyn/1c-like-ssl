package com.home.demos.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class LoggingService {
    public String search(String filterText) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\projects\\java\\other\\1c-like-ssl\\logs\\app.json.log")))) {
            return "[" + reader.lines()
                    .filter(s -> s.contains("\"context\":{"))
                    .filter(s -> filterByText(s, filterText))
                    .collect(Collectors.joining(",")) + "]";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean filterByText(String string, String filterText) {
        if (filterText == null || filterText.isBlank()) {
            return true;
        }

        String[] filters = filterText.split("&&");
        for (String filter : filters) {
            String[] keyValue = filter.trim().split("=");
            if (!string.contains(String.format("\"%s\":\"%s\"", keyValue[0], keyValue[1]))) {
                return false;
            }
        }

        return true;
    }
}
