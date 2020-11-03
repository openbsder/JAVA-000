package com.test.filter.response;

import io.netty.channel.ChannelHandlerContext;
import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpResponseFilterManager {
    private final List<HttpResponseFilter> filterChain;

    public HttpResponseFilterManager() {
        List<HttpResponseFilter> filters = new ArrayList<>();
        filters.add(new NopResponseFilter());

        this.filterChain = new CopyOnWriteArrayList<>();
        this.filterChain.addAll(filters);

        this.filterChain.sort(Comparator.comparing(HttpResponseFilter::order));
    }

    public void addFilter(HttpResponseFilter filter) {
        this.filterChain.add(filter);
    }

    public void execute(final HttpResponse response, final ChannelHandlerContext ctx) {
        for (HttpResponseFilter responseFilter : filterChain) {
            responseFilter.filter(response, ctx);
        }
    }
}
