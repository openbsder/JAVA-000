package com.example.springbean;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void sayHello() {
        System.out.println("通过注解自动装配UserService, say hello!");
    }
}
