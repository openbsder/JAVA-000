package com.test.filter.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    /**
     * Filter http request
     *
     * @param request The full http request
     * @param ctx     The channel handler context
     */
    void filter(FullHttpRequest request, ChannelHandlerContext ctx);

    /**
     * The filter order.
     *
     * <p>The lower the value, the higher the priority
     *
     * @return int
     */
    int order();
}
