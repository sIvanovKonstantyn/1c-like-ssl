package com.home.demos.usermanagement;

import lombok.Data;

@Data
public class UserContext {
    private String name;
    private String activeSessions;//todo: list
    private String roles;//todo: list

    public UserContext(String name) {
        this.name = name;
    }

    public UserContext() {
    }
}
