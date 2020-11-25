package com.atguigu.juc.day3;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * 常驻线程数               int corePoolSize,
         *   最大线程数             int maximumPoolSize,
         *   线程空闲时间           long keepAliveTime,
         *   时间类型               TimeUnit unit,
         *   队列                   BlockingQueue<Runnable> workQueue) {
         *   线程工厂               Executors.defaultThreadFactory(),
         *   拒绝策略               defaultHandler
         *
         */
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <= 9; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

    }
}
