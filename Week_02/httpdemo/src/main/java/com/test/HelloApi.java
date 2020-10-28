package com.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HelloApi {
    /**
     * The endpoint of remote hello api
     */
    public static final String HELLO_API_ENDPOINT = "http://localhost:8801";

    private final CloseableHttpClient httpClient;

    public HelloApi() {
        // Use the default http client builder
        httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Call hello api
     *
     * @return String
     * @throws IOException IOException
     */
    public String hello() throws IOException {
        return get(HELLO_API_ENDPOINT);
    }

    /**
     * Execute http method get
     *
     * @param url The http endpoint
     * @return String
     * @throws IOException IOException
     */
    public String get(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);

        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        return httpClient.execute(httpget, responseHandler);
    }
}
