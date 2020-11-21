package com.example.springbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserController {
    @Autowired
    private UserService userService;

    public void sayHello() {
        userService.sayHello();
    }
}
