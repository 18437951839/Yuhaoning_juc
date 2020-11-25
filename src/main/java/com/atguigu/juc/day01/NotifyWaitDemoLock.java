package com.atguigu.juc.day01;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareDataOneLock{
    //定义初始值
    private int number = 0;
    //创建可重入锁
    ReentrantLock lock = new ReentrantLock();
    //创建condition条件
    Condition condition = lock.newCondition();
    //增加方法
    public void numberAdd() throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while(number!=0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"==>"+number);
            //通知
            condition.signalAll();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }
    //减少方法
    public void numberSub() throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while (number!=1){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"==>"+number);
            //通知
            condition.signalAll();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }
}

public class NotifyWaitDemoLock {
    public static void main(String[] args) {
        ShareDataOneLock shareDataOneLock = new ShareDataOneLock();
        new Thread(()->{
            for (int i = 0; i < 40 ; i++) {
                try {
                    shareDataOneLock.numberAdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    shareDataOneLock.numberSub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 40 ; i++) {
                try {
                    shareDataOneLock.numberAdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    shareDataOneLock.numberSub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
