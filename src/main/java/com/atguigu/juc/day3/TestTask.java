package com.atguigu.juc.day3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestTask {


    public static void main(String[] args) {
        MyTaskDemo myTaskDemo = new MyTaskDemo(1, 100);
        ForkJoinPool forkJoinPool =new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTaskDemo);
        try {
            System.out.println(forkJoinTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            forkJoinPool.shutdown();
        }

    }
}
