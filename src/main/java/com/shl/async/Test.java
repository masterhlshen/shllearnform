package com.shl.async;

import java.util.concurrent.*;


public class Test {

    private static ExecutorService pool = Executors.newFixedThreadPool(3);
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        pool.execute(new DemoThread());

        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MAX_TURN; i++) {
                    System.out.println(Thread.currentThread() + " ，轮次" + i);
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Future<Long> res = pool.submit(new ReturnableTask());
        System.out.println(">>>异步任务执行结果" + res.get());
        Thread.sleep(COMPUTE_TIMES);
    }

    static class DemoThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread() + " ，轮次" + i);
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "开始执行");
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread() + " ，轮次" + i);
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long used = System.currentTimeMillis() - start;
            System.out.println(Thread.currentThread().getName() + " 执行结束");
            return used;
        }
    }

}
