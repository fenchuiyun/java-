package com.fenchuiyun.juc;


import java.util.concurrent.atomic.AtomicInteger;
class help{
    int number =0;
    public void addTo60(){
        number = 60;
    }
}
class VolatileTest{

    volatile int number =0;
    help help = new help();


    public void addTo60(){
        number = 60;
    }

    public void addPlus(){
        number++;
    }





}
/**
 * Created by 12441 on 2019/10/14
 */
public class test {
    public static void main(String[] args) {

            VolatileTest volatileTest = new VolatileTest();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            volatileTest.help.addTo60();
            System.out.println(volatileTest.help.number);
        }).start();
        while (volatileTest.help.number!=60);
        System.out.println("main线程"+volatileTest.help.number);
/*        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    volatileTest.addPlus();
                    volatileTest.addData();
                }
            },String.valueOf(i)).start();
        }
        //需要等待上面20个线程都全部计算完成，再用main线程取得最总的结果值是多少？
        while (Thread.activeCount()>2){//Returns an estimate of the number of active threads in the current thread's thread group and its subgroups.
            //为什么大于2？默认有2个线程 一个main线程一个GC线程
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t 最终值:"+volatileTest.number);
        System.out.println(volatileTest.number1.get());*/
    }

    //资源类使用volatile修饰，解决可见性问题
    private static void seeOkByVolatile(VolatileTest volatileTest) {
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            volatileTest.addTo60();
            System.out.println(volatileTest.number);
        }).start();
        while (volatileTest.number!=60);
        System.out.println("main线程"+volatileTest.number);
    }
}
