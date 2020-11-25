package com.atguigu.juc.day2;

/**
 *
 ☆☆ （1）乐观锁和悲观锁

 ☆☆ （2）表锁和行锁

 ☆☆ （3）读写锁
 ☆☆☆ 读写锁：读锁和写锁都会发生死锁
 ☆☆☆ 读锁：共享锁
 ☆☆☆ 写锁：独占锁

 ☆☆ （4）读写锁 ReentrantReadWriteLock
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
class MyCache{
    //模拟缓存效果，不断向map中放数据，从map中获取数据
    private volatile Map<String,Object> map = new HashMap<>();
    //创建读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //向map中放数据的方法
    public void put(String key,Object value){
        //添加写锁
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"正在写数据");
        try {
            TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"已经写完了数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放锁
            readWriteLock.writeLock().unlock();
        }
    }
    //从map中获取数据的方法
    public Object get(String key){
        //添加读锁
        readWriteLock.readLock().lock();
        Object result = null;

        try {
            System.out.println(Thread.currentThread().getName()+"正在读");
            TimeUnit.SECONDS.sleep(1);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读到了数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
        return result;
    }
}
public class Test06 {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;

            new Thread(()->{
                myCache.put(finalI+"", finalI);
            },i+"").start();
        }

        TimeUnit.SECONDS.sleep(3);

        for (int i = 1; i <= 5; i++) {
            int finalI = i;

            new Thread(()->{
                myCache.get(finalI+"");
            },i+"").start();
        }
    }
}
