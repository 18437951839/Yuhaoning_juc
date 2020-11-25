package com.atguigu.juc.day01;


class ShareDateOne{
    //定义初始变量
    private int number=0;
    //+1
    public synchronized void numberAdd () throws InterruptedException {
        //判断
        while (number!=0) {
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName()+"==>"+number);
        //通知
        this.notifyAll();
    }

    //-1
    public synchronized void numberSub () throws InterruptedException {
        //判断
        while (number!=1) {
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName()+"==>"+number);
        //通知
        this.notifyAll();
    }

}

public class NotifyWaitDemo {
    public static void main(String[] args) {
        ShareDateOne shareDateOne = new ShareDateOne();
        new Thread(()->{
            for (int i = 1; i <= 40; i++) {
                try {
                    shareDateOne.numberAdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1; i <= 40; i++) {
                try {
                    shareDateOne.numberSub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1; i <= 40; i++) {
                try {
                    shareDateOne.numberAdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 1; i <= 40; i++) {
                try {
                    shareDateOne.numberSub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
