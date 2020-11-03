package com.test.router;

import java.util.List;
import java.util.Random;

public class RandomRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        int len = endpoints.size();
        String endpoint = endpoints.get(new Random().nextInt(len));

        endpoint = endpoint.endsWith("/") ? endpoint.substring(0, endpoint.length() - 1) : endpoint;

        return endpoint;
    }
}
