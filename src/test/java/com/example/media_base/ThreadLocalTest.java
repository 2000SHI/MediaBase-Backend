package com.example.media_base;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocal() {
        ThreadLocal tl = new ThreadLocal();
        new Thread(() -> {
            tl.set("t1");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "thread1").start();
        new Thread(() -> {
            tl.set("t2");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "thread2").start();
    }
}
