package com.fenchuiyun.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by 12441 on 2019/10/14
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车场 3个资源

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                boolean flag = false;

                try {
                    semaphore.acquire();
                    flag=true;
                    System.out.println(Thread.currentThread().getName()+"\t-----抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                        System.out.println(Thread.currentThread().getName()+"\t------离开停车场");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (flag==true){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
