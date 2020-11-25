package com.atguigu.juc.day2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * （2）CyclicBarrier循环栅栏
 * 集齐7颗龙珠就可以召唤神龙
 */
public class Test04 {
    private static final int NUMBER=7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("你已经集齐了七龙珠，请说出你的愿望吧！");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"星球找到了！！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },i+"").start();

        }
    }

}
