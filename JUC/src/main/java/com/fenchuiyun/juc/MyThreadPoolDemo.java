package com.fenchuiyun.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 12441 on 2019/10/14
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //ExecutorService executorService = Executors.newCachedThreadPool();

        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 9; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务" +
                            new Random().nextInt(10));

                });
            }
        } finally {
            System.out.println("++++++++++++++");
            executorService.shutdown();
            System.out.println("关闭线程池");
        }

    }
}
