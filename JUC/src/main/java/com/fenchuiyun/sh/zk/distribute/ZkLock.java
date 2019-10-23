package com.fenchuiyun.sh.zk.distribute;

/**
 * Created by 12441 on 2019/10/22
 */
public interface ZkLock {
    void lock();
    void unlock();
}
