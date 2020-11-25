package com.atguigu.juc.day2;

import java.util.concurrent.TimeUnit;

/**
 * 锁的8种情况演示
 */
class Phone{
    //发短信
    public static synchronized void sendSMS() throws InterruptedException {
        //停四秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-------------->短信已发送");
    }
    //发邮件
    public synchronized void sendEmail(){
        System.out.println("-------------->Email已发送");
    }
    //发送普通方法
    //发邮件
    public void sendWX(){
        System.out.println("-------------->WX已发送");
    }
}
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone1.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
        Thread.sleep(200);
        new Thread(()->{
            //phone1.sendEmail();
            phone2.sendEmail();
            //phone2.sendWX();
            //phone1.sendWX();
        },"BB").start();
    }
}
