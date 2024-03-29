package com.shl.deplayXc.fileopt;

import com.shl.deplayXc.DeplayedHelper;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.*;

public class CatalinalogListener extends FileAlterationListenerAdaptor {
    private FileMonitor monitor;
    private DeplayedHelper helper;
    private int index;

    public CatalinalogListener(DeplayedHelper helper, int index) {
        this.index = index;
        this.helper = helper;
    }

    public void setMonitor(FileMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
    }

    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("org.apache.catalina.startup.Catalina.start Server startup")) {
                    monitor.stop();
                    helper.startServer((index + 1));
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }
}
