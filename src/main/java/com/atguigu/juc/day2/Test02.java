package com.atguigu.juc.day2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CallableTest implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"----->执行了200！");
        return 200;
    }
}

public class Test02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTest());
        new Thread(futureTask,"AA").start();


        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(()->{
            System.out.println(Thread.currentThread().getName()+"-->执行了1024！");
            return 1024;
        });
        new Thread(futureTask1,"BB").start();
        Integer integer1 = futureTask.get();
        System.out.println("integer1 = " + integer1);
        Integer integer = futureTask1.get();
        System.out.println("integer = " + integer);

    }



}
