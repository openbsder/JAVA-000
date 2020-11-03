package com.test.filter.request;

import com.test.filter.Filter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestDebugFilter implements HttpRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestDebugFilter.class);

    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext ctx) {
        LOGGER.info("#Print request info:");
        LOGGER.info("## Request uri: " + request.uri());

        LOGGER.info("## Header:");
        for (Map.Entry<String, String> header : request.headers()) {
            LOGGER.info("           " + header.getKey() + ": " + header.getValue());
        }

        LOGGER.info("## Body:");
        LOGGER.info(request.content().toString(StandardCharsets.UTF_8));
    }

    @Override
    public int order() {
        return Filter.LAST_FILTER_ORDER - 1;
    }
}
