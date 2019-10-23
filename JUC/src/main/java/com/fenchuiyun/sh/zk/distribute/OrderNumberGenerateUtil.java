package com.fenchuiyun.sh.zk.distribute;

/**
 * Created by 12441 on 2019/10/22
 */
public class OrderNumberGenerateUtil {
    private static int number =0;

    public String getOrderNumber(){
        return ""+(++number);
    }
}
