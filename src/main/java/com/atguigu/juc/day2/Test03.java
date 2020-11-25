package com.atguigu.juc.day2;
import java.util.concurrent.CountDownLatch;
/**
 * （1）CountDownLatch减少计数
 * 6个同学陆续离开教室后值班同学才可以关门。
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        //CountDownLatch减少计数
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了");
                //值-1
                countDownLatch.countDown();
            },i+"").start();
        }

        //等待
        countDownLatch.await();
        //班长锁门
        System.out.println(Thread.currentThread().getName()+"班长关门,main线程时班长");
    }
}
