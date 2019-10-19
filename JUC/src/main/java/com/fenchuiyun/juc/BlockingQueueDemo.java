package com.fenchuiyun.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 12441 on 2019/10/14
 */
public class BlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String>  blockingQueue = new ArrayBlockingQueue<>(3);

/*
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
*/

        /*System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

/*        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/
        blockingQueue.add("a");
        blockingQueue.add("a");
        blockingQueue.add("a");
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }


}
