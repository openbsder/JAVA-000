package com.test.filter.response;

import io.netty.channel.ChannelHandlerContext;
import org.apache.http.HttpResponse;

public interface HttpResponseFilter {
    /**
     * Filter http request
     *
     * @param response The full http response
     * @param ctx      The channel handler context
     */
    void filter(HttpResponse response, ChannelHandlerContext ctx);

    /**
     * The filter order.
     *
     * <p>The lower the value, the higher the priority
     *
     * @return int
     */
    int order();
}
