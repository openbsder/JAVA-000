package com.test.filter.response;

import com.test.filter.Filter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NopResponseFilter implements HttpResponseFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NopResponseFilter.class);

    @Override
    public void filter(HttpResponse response, ChannelHandlerContext ctx) {
        LOGGER.info("Execute Nop response filter...");
    }

    @Override
    public int order() {
        return Filter.FIRST_FILTER_ORDER + 1;
    }
}
