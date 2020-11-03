package com.test.filter.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpRequestFilterManager {

    private final List<HttpRequestFilter> filterChain;

    public HttpRequestFilterManager() {
        List<HttpRequestFilter> filters = new ArrayList<>();
        filters.add(new NioHeaderFilter());
        filters.add(new RequestDebugFilter());

        this.filterChain = new CopyOnWriteArrayList<>();
        this.filterChain.addAll(filters);

        this.filterChain.sort(Comparator.comparing(HttpRequestFilter::order));
    }

    public void addFilter(HttpRequestFilter filter) {
        this.filterChain.add(filter);
    }

    public void execute(final FullHttpRequest request, final ChannelHandlerContext ctx) {
        for (HttpRequestFilter requestFilter : filterChain) {
            requestFilter.filter(request, ctx);
        }
    }
}
