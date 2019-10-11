package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//资源类
    private int number = 30;

/*    public synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出第:"+(number--)+"\t还剩下:"+number);
        }
    }*/
    Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try{
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第:"+(number--)+"\t还剩下:"+number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
/**
 * Created by 12441 on 2019/10/11
 */

/**
 * 题目：三个售票员     卖出      30张票
 * 目的：如何写出企业级的多线程程序
 *
 * ** 高内聚，低耦合
 *
 *  1 高内低耦的前提下，线程       操作          资源类
 */
public class SaleTicket {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(()->{ for (int i = 0; i <40 ; i++) ticket.sale();},"A").start();
        new Thread(()->{ for (int i = 0; i <40 ; i++) ticket.sale();},"B").start();
        new Thread(()->{ for (int i = 0; i <40 ; i++) ticket.sale();},"C").start();

/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <40 ; i++) {
                    ticket.sale();
                }
            }
        },"D").start();
*/

    }
}
