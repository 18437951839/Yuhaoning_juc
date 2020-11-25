package com.atguigu.juc.day01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按照顺序调用：实现 AA->BB->CC
 *
 * AA打印1-5  BB打印1-10  CC打印1-15
 *
 * 执行10轮
 *
 */

class ShareResource{
    //定义标志位
    private int number = 1;//1:AA;2:BB;3:CC
    //定义可重入锁
    private ReentrantLock lock = new ReentrantLock();
    //定义条件condition
    Condition c1 = lock.newCondition();//AA
    Condition c2 = lock.newCondition();//BB
    Condition c3 = lock.newCondition();//CC

    public void print5(int totalLoopNumber) throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while(number!=1){
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"===>" + i+"totalLoopNumber:"+totalLoopNumber);
            }
            number=2;
            //通知
            c2.signal();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }

    public void print10(int totalLoopNumber) throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while(number!=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"===>" + i+"totalLoopNumber:"+totalLoopNumber);
            }
            number=3;
            //通知
            c3.signal();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }

    public void print15(int totalLoopNumber) throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while(number!=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"===>" + i+"totalLoopNumber:"+totalLoopNumber);
            }
            number=1;
            //通知
            c1.signal();
        } finally {
            //关闭锁
            lock.unlock();
        }

    }
}


public class ThreadOrderDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    try {
                        shareResource.print5(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"AA").start();
        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    try {
                        shareResource.print10(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        },"BB").start();
        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    try {
                        shareResource.print15(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"CC").start();
    }
}
