package com.main.hrms.common;

import org.springframework.stereotype.Component;

import com.main.hrms.model.RequestMeta;

@Component
public class RequestMetaContextHolder {
    private static final ThreadLocal<RequestMeta> requestMetaThreadLocal = new ThreadLocal<>();

    public static void setRequestMeta(RequestMeta requestMeta) {
        requestMetaThreadLocal.set(requestMeta);
    }

    public static RequestMeta getRequestMeta() {
        return requestMetaThreadLocal.get();
    }

    public static void clear() {
        requestMetaThreadLocal.remove();
    }
}
