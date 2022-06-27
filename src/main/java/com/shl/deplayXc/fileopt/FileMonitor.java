package com.shl.deplayXc.fileopt;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileMonitor {


    public FileMonitor(long inv, String filePath, FileAlterationListener listener) throws Exception {
        this.monitor = new FileAlterationMonitor(inv);
        FileAlterationObserver observer = new FileAlterationObserver(new File(filePath));
        this.monitor.addObserver(observer);
        observer.addListener(listener);
        monitor.start();
    }


    public void stop() {
        try {
            monitor.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FileAlterationMonitor monitor;
}
