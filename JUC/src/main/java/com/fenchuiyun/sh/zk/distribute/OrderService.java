package com.fenchuiyun.sh.zk.distribute;

/**
 * Created by 12441 on 2019/10/22
 */
public class OrderService {

    OrderNumberGenerateUtil orderNumberGenerateUtil = new OrderNumberGenerateUtil();

    private ZkLock zkLock= new ZkDistributeLock();
    public void getOrderNumber(){
        zkLock.lock();
        try {
            System.out.println("叮咚编号:    \t"+orderNumberGenerateUtil.getOrderNumber());
        } finally {
            zkLock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                new OrderService().getOrderNumber();
            },String.valueOf(i)).start();
        }
    }
}
