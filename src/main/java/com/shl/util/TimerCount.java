package com.shl.util;

public final class TimerCount {
    private long start;

    public TimerCount() {
        initStart();
    }

    public void start() {
        initStart();
    }

    public void end(String msg) {
        System.out.println(msg + " 耗时" + ((System.currentTimeMillis() - start) / 1000.0) + "秒");
    }

    private void initStart() {
        start = System.currentTimeMillis();
    }
}
