package com.test.outbound.httpclient4;

import com.test.filter.response.HttpResponseFilterManager;
import com.test.router.HttpEndpointRouter;
import com.test.router.RandomRouter;
import com.test.router.RouterManage;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class HttpOutboundHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpOutboundHandler.class);

    private final CloseableHttpAsyncClient httpClient;
    private final ExecutorService proxyService;
    private final HttpEndpointRouter httpEndpointRouter;
    private final HttpResponseFilterManager httpResponseFilterManager;

    public HttpOutboundHandler() {
        httpEndpointRouter = new RandomRouter();
        httpResponseFilterManager = new HttpResponseFilterManager();

        final RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;

        proxyService = new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactor("proxyService"), rejectedExecutionHandler);

        final IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpClient = HttpAsyncClients.custom()
                .setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((httpResponse, httpContext) -> 6000)
                .build();

        httpClient.start();
    }

    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        final String url = httpEndpointRouter.route(RouterManage.endPoints) + fullHttpRequest.uri();
        LOGGER.info("Gateway -> proxy service url: {}", url);

        proxyService.submit(() -> fetchRemote(fullHttpRequest, ctx, url));
    }

    private void fetchRemote(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, final String url) {
        final RequestBuilder requestBuilder = RequestBuilder.create(fullHttpRequest.method().toString())
                .setUri(url);

        // Forward http header to proxy server
        for (Map.Entry<String, String> header : fullHttpRequest.headers()) {
            requestBuilder.setHeader(header.getKey(), header.getValue());
        }

        requestBuilder.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        requestBuilder.removeHeaders(HTTP.CONTENT_LEN);

        // build http body
        requestBuilder.setEntity(new ByteArrayEntity(ByteBufUtil.getBytes(fullHttpRequest.content())));
        final HttpUriRequest httpUriRequest = requestBuilder.build();

        httpClient.execute(httpUriRequest, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    // apply http response filter
                    httpResponseFilterManager.execute(httpResponse, ctx);

                    handleResponse(fullHttpRequest, ctx, httpResponse);
                } catch (Exception e) {
                    buildFailedResponse(fullHttpRequest, ctx, e.getMessage());
                }
            }

            @Override
            public void failed(Exception e) {
                httpUriRequest.abort();
                e.printStackTrace();
                buildFailedResponse(fullHttpRequest, ctx, e.getMessage());
            }

            @Override
            public void cancelled() {
                httpUriRequest.abort();
            }
        });
    }

    private void buildFailedResponse(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String message) {
        FullHttpResponse response = null;

        try {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST,
                    Unpooled.wrappedBuffer(message.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", message.length());
        } finally {
            if (Objects.nonNull(fullHttpRequest)) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    private void handleResponse(final FullHttpRequest fullHttpRequest,
                                final ChannelHandlerContext ctx,
                                final HttpResponse endPointResponse) {
        FullHttpResponse response = null;

        try {
            byte[] body = EntityUtils.toByteArray(endPointResponse.getEntity());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, endPointResponse.getFirstHeader(HttpHeaderNames.CONTENT_TYPE.toString()).getValue());
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, Integer.parseInt(endPointResponse.getFirstHeader(HttpHeaderNames.CONTENT_LENGTH.toString()).getValue()));
        } catch (IOException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (Objects.nonNull(fullHttpRequest)) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
