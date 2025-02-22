package com.blog.context;

import com.blog.vo.RequestContextManager;

public class RequestContext {
    private static final ThreadLocal<RequestContextManager> currentRequestThreadLocal = new ThreadLocal<>();

    public static void setRequestInfo(RequestContextManager requestContextManager) {
        currentRequestThreadLocal.set(requestContextManager);
    }

    public static RequestContextManager getRequestInfo() {
        return currentRequestThreadLocal.get();
    }
}
