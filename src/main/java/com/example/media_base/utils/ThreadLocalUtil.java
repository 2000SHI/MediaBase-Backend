package com.example.media_base.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    public static <T> void set(T obj) {
        THREAD_LOCAL.set(obj);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
