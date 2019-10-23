package com.fenchuiyun.sh.zk.distribute;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 12441 on 2019/10/22
 */
public class ZkDistributeLock extends ZkAbstractTemplateLock {
    @Override
    public boolean tryZkLock() {
        try {
            zkClient.createEphemeral(path);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public void waitZkLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch!=null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(path,iZkDataListener);
        if (zkClient.exists(path)){
            countDownLatch= new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(path,iZkDataListener);
    }
}
