package com.home.demos.usermanagement.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-management/demo")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest createUserRequest) {
        return service.create(createUserRequest);
    }

    @PutMapping
    public UserResponse update(@RequestBody UpdateUserRequest updateUserRequest) {
        return service.update(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        return service.remove(id);
    }

    @GetMapping
    public String test(@RequestParam String state) {
        userContext.setActiveSessions(userContext.getActiveSessions() == null ? state : userContext.getActiveSessions() + state);
        return userContext.toString();
    }
}
