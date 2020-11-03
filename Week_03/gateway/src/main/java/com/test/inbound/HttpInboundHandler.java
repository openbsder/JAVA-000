package com.test.inbound;

import com.test.filter.request.HttpRequestFilterManager;
import com.test.outbound.httpclient4.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private final HttpOutboundHandler handler;
    private final HttpRequestFilterManager filterManager;

    public HttpInboundHandler(HttpRequestFilterManager filterManager) {
        this.handler = new HttpOutboundHandler();
        this.filterManager = filterManager;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;

            // Apply http request filter
            this.filterManager.execute(fullHttpRequest, ctx);

            handler.handle(fullHttpRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
