package com.shl.java基础.并发;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadTest {

    public static void main(String[] args) {
        ThreadOne to = new ThreadOne();
        to.start();

        System.out.println(">>>>>>>>>The one code end");


        Thread t = new Thread(new ThreadTwo(), "实现Runable方式");
        t.start();
        System.out.println(">>>>>>>>The two code end");

        Date d = new Date(1704258229948L);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
    }
}

// 实现Runable方式
class ThreadTwo implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread name " + Thread.currentThread().getName());
        System.out.println("Thread id " + Thread.currentThread().getId());
    }
}

// 继承Thread类
class ThreadOne extends Thread {

    @Override
    public void run() {
        System.out.println("Thread name " + Thread.currentThread().getName());
        System.out.println("Thread id " + Thread.currentThread().getId());
    }
}
