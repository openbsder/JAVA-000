package com.example.springbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        User user = (User) context.getBean("user");
        System.out.println("Xml装配user: " + user);

        UserController userController = (UserController) context.getBean("userController");
        userController.sayHello();

        UserCache userCache = (UserCache) context.getBean("userCache");
        userCache.sayHello2();
    }
}
