package com.test;

import java.io.IOException;

/**
 * App
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Start call Hello api...");

        HelloApi helloApi = new HelloApi();

        String body;
        try {
            body = helloApi.hello();
        } catch (IOException e) {
            System.out.println("Call Hello api failed, reason: " + e.getMessage());
            return;
        }

        System.out.println("Success, response: " + body);
    }
}
