package com.atguigu.juc;

interface Foo{
    int add(int x,int y);

    default int div(int x,int y){
        return x/y;
    }

    static int mul(int x, int y){
        return x*y;
    }

}
/**
 * Created by 12441 on 2019/10/11
 */

/**
 * 2 Lambda Express(前提是函数式接口，只能有一个方法)
 *
 * 2.1 拷贝小括号，写死右箭头，落地大括号
 * 2.2 注解@FunctionalInterface
 * 2.3 default方法
 * 2.4 static
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
    }
}
