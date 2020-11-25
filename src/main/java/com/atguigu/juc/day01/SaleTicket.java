package com.atguigu.juc.day01;

import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    //定义票数
    int number = 200;
    //创建买票方法

    public void sale(){
        //创建可重入锁
        ReentrantLock lock = new ReentrantLock();
        //打开锁
        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出："+(number--) +"还剩"+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭锁
            lock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 1; i <= 200; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 200; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 200; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}
