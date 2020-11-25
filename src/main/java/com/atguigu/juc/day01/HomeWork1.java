package com.atguigu.juc.day01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareDemo{
    //定义标志位
    private int number=0;//0:打印数字  1：打印字母
    ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    //打印数字方法
    public void printNum(int num) throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while (number!=0){
                condition.await();
            }
            //干活
            System.out.print((2*num)-1+""+2*num);
            number++;
            //通知
            condition.signalAll();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }

    //打印字母方法
    public void printLetter(char letter) throws InterruptedException {
        //打开锁
        lock.lock();
        try {
            //判断
            while (number!=1){
                condition.await();
            }
            //干活
                System.out.print(letter);
            number--;
            //通知
            condition.signalAll();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }

}

public class HomeWork1 {
    public static void main(String[] args) {
        ShareDemo shareDemo = new ShareDemo();
        new Thread(()->{
            try {
                for (int i = 1; i <= 26; i++) {
                    shareDemo.printNum(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"pNum").start();
        new Thread(()->{
            try {
                for (char i = 65; i < 65+26; i++) {
                    shareDemo.printLetter(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"pLetter").start();

    }
}
