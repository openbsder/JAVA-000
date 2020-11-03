package com.test.filter.request;

import com.test.filter.Filter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioHeaderFilter implements HttpRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioHeaderFilter.class);

    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext ctx) {
        LOGGER.info("#Execute nio header filter...");
        request.headers().add("nio", "zhangxudong");
    }

    @Override
    public int order() {
        return Filter.FIRST_FILTER_ORDER + 1;
    }
}
