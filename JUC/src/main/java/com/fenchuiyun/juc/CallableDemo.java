package com.fenchuiyun.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t*****come in call");
        return "java";
    }
}
/**
 * Created by 12441 on 2019/10/14
 * Java多线程中，第3钟获得多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(2);
            return "hello World";
        });
        new Thread(futureTask,"AAA").start();
        String result = futureTask.get();//get方法会阻塞 get方法一般请放在最后一行
        System.out.println(result);

        System.out.println("********主线程");
    }
}
