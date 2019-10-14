package com.fenchuiyun.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private volatile Map<String,String> map = new HashMap<>();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            String result = null;
            System.out.println(Thread.currentThread().getName() + "\t 读取开始");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
/*    private Lock lock = new ReentrantLock();

    public void put(String key,String value){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void get(String key){
        lock.lock();
        try{
            String result = null;
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }*/

}
/**
 * Created by 12441 on 2019/10/14
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.put(finalI+"",finalI+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.get(finalI+"");
            },String.valueOf(i)).start();
        }
    }
}
