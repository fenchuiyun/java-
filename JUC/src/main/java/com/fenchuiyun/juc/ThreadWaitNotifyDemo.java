package com.fenchuiyun.juc;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Count{
    private int count =0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
/*    public synchronized void incrementBy1(){
        //1 判断
        while (count!=0){//防止虚假唤醒，要用while进行判断
            try {

                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //2 干活
        count++;
        System.out.println(count);
        //通知
        this.notifyAll();
    }
    
    public synchronized void decrementBy1(){
        while (count==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(count);
        this.notifyAll();
    }*/
    public void incrementBy1() {
        lock.lock();
        try{
            while (count!=0){//防止虚假唤醒，要用while进行判断
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2 干活
            count++;
            System.out.println(count);
            //3 通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public synchronized void decrementBy1(){
        lock.lock();
        try{
            while (count==0){//防止虚假唤醒，要用while进行判断
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2 干活
            count--;
            System.out.println(count);
            //3 通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
/**
 * Created by 12441 on 2019/10/13
 * 题目：现在两个线程，可以操作初始化值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替，来10轮，变量初始值为零。
 *lock和synchronized有啥区别??
 * 1    高内聚低耦合的前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    小心，防止多线程中的虚假唤醒,判断时候用while而不是if
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) throws Exception{
        Count count = new Count();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                count.incrementBy1();
            }
        },"A").start();
        
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                count.decrementBy1();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                count.incrementBy1();
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                count.decrementBy1();
            }
        },"D").start();
    }
}
