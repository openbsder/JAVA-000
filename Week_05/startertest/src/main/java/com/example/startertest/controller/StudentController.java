package com.example.startertest.controller;

import org.example.starterdemo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private Student student;

    @RequestMapping("/")
    public Student get() {
        return student;
    }
}
