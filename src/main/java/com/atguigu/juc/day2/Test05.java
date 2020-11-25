package com.atguigu.juc.day2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * （3）Semaphore信号灯
 * 6辆汽车抢3个车位
 */
public class Test05 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                }
            },i+"").start();
        }
    }
}
