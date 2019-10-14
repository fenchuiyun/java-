package com.fenchuiyun.juc;

import com.sun.org.apache.xpath.internal.functions.FuncRound;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 12441 on 2019/10/14
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println(Thread.currentThread().getName()+"收集7颗龙珠，能够召唤神龙");
        });

        for (int i = 0; i < 6; i++) {
            int finalI=i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t收集到第：" +
                            finalI+"\t");
                    cyclicBarrier.await();
                    System.out.println(finalI+"------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
