package com.test;

import com.test.inbound.HttpInboundServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayApp {
    public static final Logger LOGGER = LoggerFactory.getLogger(GatewayApp.class);
    public static final String GATEWAY_NAME = "NIOGateway";
    public static final String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        final String proxyServer = System.getProperty("proxyServer", "http://127.0.0.1:8088");
        final String proxyPort = System.getProperty("proxyPort", "8888");
        final int port = Integer.parseInt(proxyPort);

        LOGGER.info(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port);
        LOGGER.info(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + proxyServer);

        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
