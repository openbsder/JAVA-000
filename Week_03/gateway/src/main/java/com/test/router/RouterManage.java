package com.test.router;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RouterManage {
    public static final List<String> endPoints = new ArrayList<>();

    static {
        final String proxyServer = System.getProperty("proxyServer", "http://127.0.0.1:8088");
        String[] join = proxyServer.split(";");

        Collections.addAll(endPoints, join);
    }
}