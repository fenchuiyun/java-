package com.fenchuiyun.sh.zk.distribute;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 12441 on 2019/10/22
 */
public abstract class ZkAbstractTemplateLock implements ZkLock {

    public static final String ZKSERVER="192.168.110.128:2181";
    public static final int TIME_OUT =45*1000;
    ZkClient zkClient = new ZkClient(ZKSERVER,TIME_OUT);

    protected String path ="/myzkLock";
    protected CountDownLatch countDownLatch =null;
    @Override
    public void lock() {
        if (tryZkLock()){
            System.out.println(Thread.currentThread().getName()+"\t 抢占锁成功");
        }else {
            waitZkLock();
            lock();
        }
    }

    public abstract boolean tryZkLock();
    public abstract void waitZkLock();
    @Override
    public void unlock() {


        if (zkClient!=null){
            System.out.println(Thread.currentThread().getName()+"\t 释放锁成功");
            zkClient.close();
        }

        System.out.println();
        System.out.println();
    }
}
