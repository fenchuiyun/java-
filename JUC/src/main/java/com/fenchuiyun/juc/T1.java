package com.fenchuiyun.juc;

/**
 * Created by 12441 on 2019/10/17
 */
public class T1 {
    volatile int n=0;

    public void add(){
        n++;
    }

    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //Thread.sleep();

                System.out.println("asdfasfd"+Thread.interrupted());
            }
        });
        t1.start();
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.isInterrupted());

    }
}
