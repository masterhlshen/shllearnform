package com.shl.xctest;

import com.sun.management.OperatingSystemMXBean;

import java.io.*;
import java.lang.management.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.*;

public class XCServerSystemInfo {

    public static void main(String[] args) throws IOException {
        XCServerSystemInfo info = new XCServerSystemInfo();
        Map<String, String> env = info.env();
        for (String s : env.keySet()) {
            System.out.println(s + ":" + env.get(s));

        }

        Map<String, Map<String, String>> disk = info.disk();
        for (String s : disk.keySet()) {
            Map<String, String> t = disk.get(s);
            System.out.println(s);
            for (String s1 : t.keySet()) {
                System.out.println(s1 + ":" + t.get(s1));

            }
        }

        Map<String, String> mem = info.mem();
        for (String s : mem.keySet()) {
            System.out.println(s + ":" + mem.get(s));
        }


        while (true) {
            System.out.println(new Date() + " " + info.cpu());
        }
    }

    public Map<String, String> env() throws UnknownHostException, UnknownHostException {
        Map<String, String> result = new HashMap<>();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        // 获取用户名
        String userName = map.get("USERNAME");
        // 获取计算机名
        String computerName = map.get("COMPUTERNAME");
        // 获取计算机域名
        String userDomain = map.get("USERDOMAIN");
        result.put("用户名", userName);
        result.put("计算机名", computerName);
        result.put("计算机域名", userDomain);
        result.put("本地ip地址", ip);
        result.put("本地主机名", addr.getHostName());
        result.put("JVM可以使用的总内存", r.totalMemory() + "");
        result.put("JVM可以使用的剩余内存", r.freeMemory() + "");
        result.put("JVM可以使用的处理器个数", r.availableProcessors() + "");
        result.put("Java的运行环境版本 ", props.getProperty("java.version"));
        result.put("Java的运行环境供应商 ", props.getProperty("java.vendor"));
        result.put("Java供应商的URL ", props.getProperty("java.vendor.url"));
        result.put("Java的安装路径 ", props.getProperty("java.home"));
        result.put("Java的虚拟机规范版本 ", props.getProperty("java.vm.specification.version"));
        result.put("Java的虚拟机规范供应商 ", props.getProperty("java.vm.specification.vendor"));
        result.put("Java的虚拟机规范名称 ", props.getProperty("java.vm.specification.name"));
        result.put("Java的虚拟机实现版本 ", props.getProperty("java.vm.version"));
        result.put("Java的虚拟机实现供应商 ", props.getProperty("java.vm.vendor"));
        result.put("Java的虚拟机实现名称 ", props.getProperty("java.vm.name"));
        result.put("Java运行时环境规范版本 ", props.getProperty("java.specification.version"));
        result.put("Java运行时环境规范供应商 ", props.getProperty("java.specification.vender"));
        result.put("Java运行时环境规范名称 ", props.getProperty("java.specification.name"));
        result.put("Java的类格式版本号 ", props.getProperty("java.class.version"));
        // result.put("Java的类路径 ", props.getProperty("java.class.path"));
        // result.put("加载库时搜索的路径列表 ", props.getProperty("java.library.path"));
        result.put("默认的临时文件路径 ", props.getProperty("java.io.tmpdir"));
        result.put("一个或多个扩展目录的路径 ", props.getProperty("java.ext.dirs"));
        result.put("操作系统的名称 ", props.getProperty("os.name"));
        result.put("操作系统的构架 ", props.getProperty("os.arch"));
        result.put("操作系统的版本 ", props.getProperty("os.version"));
        result.put("文件分隔符 ", props.getProperty("file.separator"));
        result.put("路径分隔符 ", props.getProperty("path.separator"));
        result.put("行分隔符 ", props.getProperty("line.separator"));
        result.put("用户的账户名称 ", props.getProperty("user.name"));
        result.put("用户的主目录 ", props.getProperty("user.home"));
        result.put("用户的当前工作目录 ", props.getProperty("user.dir"));
        return result;
    }

    public Map<String, Map<String, String>> disk() {
        Map<String, Map<String, String>> result = new HashMap<>();
        // 磁盘使用情况
        File[] files = File.listRoots();
        for (File file : files) {
            String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024 / 1024);
            String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024 / 1024);
            String un = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024 / 1024);
            String path = file.getPath();
            Map<String, String> pathMap = new HashMap<>();
            pathMap.put("总空间", total);
            pathMap.put("可用空间", un);
            pathMap.put("空闲空间", free);
            result.put(path, pathMap);
        }
        return result;
    }

    public Map<String, String> mem() {
        Map<String, String> result = new HashMap<>();
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 堆内存使用情况
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        // 初始的总内存
        long initTotalMemorySize = memoryUsage.getInit();
        // 最大可用内存
        long maxMemorySize = memoryUsage.getMax();
        // 已使用的内存
        long usedMemorySize = memoryUsage.getUsed();
        Map<String, String> memoryUsageMap = new HashMap<>();
        memoryUsageMap.put("初始的总内存", initTotalMemorySize + "");
        memoryUsageMap.put("最大可用内存", maxMemorySize + "");
        memoryUsageMap.put("已使用的内存", usedMemorySize + "");
        // 总的物理内存
        String totalMemorySize = new DecimalFormat("#.##").format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024) + "G";
        // 剩余的物理内存
        String freePhysicalMemorySize = new DecimalFormat("#.##").format(osmxb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024) + "G";
        // 已使用的物理内存
        String usedMemory = new DecimalFormat("#.##").format((osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024) + "G";
        memoryUsageMap.put("总的物理内存", totalMemorySize);
        memoryUsageMap.put("剩余的物理内存", freePhysicalMemorySize);
        memoryUsageMap.put("已使用的物理内存", usedMemory);
        String jvmInitMem = new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024) + "M";
        String jvmMaxMem = new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024 / 1024) + "M";
        String jvmUsedMem = new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024) + "M";
        memoryUsageMap.put("JVM初始总内存", jvmInitMem);
        memoryUsageMap.put("JVM最大可用内存", jvmMaxMem);
        memoryUsageMap.put("JVM已使用的内存", jvmUsedMem);
        return memoryUsageMap;
    }

    public Map jvm() {
        Map result = new HashMap();
        // 获得线程总数
        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup();
             parentThread.getParent() != null;
             parentThread = parentThread.getParent()) {
        }
        int totalThread = parentThread.activeCount();
        result.put("总线程数", totalThread);
        result.put("PID", System.getProperty("PID"));
        result.put("LibraryPath", ManagementFactory.getRuntimeMXBean().getLibraryPath());
        result.put("BootClassPath", ManagementFactory.getRuntimeMXBean().getBootClassPath());
        result.put("ClassPath", ManagementFactory.getRuntimeMXBean().getClassPath());
        result.put("ObjectPendingFinalizationCount", ManagementFactory.getMemoryMXBean().getObjectPendingFinalizationCount());
        result.put("HeapMemoryUsage", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        result.put("NonHeapMemoryUsage", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
        result.put("ObjectName", ManagementFactory.getMemoryMXBean().getObjectName());
        result.put("LoadedClassCount", ManagementFactory.getClassLoadingMXBean().getLoadedClassCount());
        result.put("TotalLoadedClassCount", ManagementFactory.getClassLoadingMXBean().getTotalLoadedClassCount());
        result.put("TotalCompilationTime", ManagementFactory.getCompilationMXBean().getTotalCompilationTime());
        result.put("Compilation", ManagementFactory.getCompilationMXBean().getName());
        result.put("OperatingSystemMXBean", ManagementFactory.getOperatingSystemMXBean().getName());
        result.put("OperatingSystemMXArch", ManagementFactory.getOperatingSystemMXBean().getArch());
        result.put("AvailableProcessors", ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors());
        result.put("SystemLoadAverage", ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage());
        Map jvmMemPool = new HashMap();
        //内存池对象
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools) {
            jvmMemPool.put(pool.getName(), new HashMap() {
                {
                    put("name", pool.getName());
                    put("Type", pool.getType());
                    put("ObjectName", pool.getObjectName());
                    put("Usage", pool.getUsage().toString());
                    put("PeakUsage", pool.getPeakUsage());
                    put("CollectionUsage", pool.getCollectionUsage());
                }
            });
        }
        result.put("MemoryPool", jvmMemPool);
        Map garbageCollector = new HashMap();
        // gc
        List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();
        // ParallelOld("ParallelOld"),
        //    SerialOld("SerialOld"),
        //    PSMarkSweep("PSMarkSweep"),
        //    ParallelScavenge("ParallelScavenge"),
        //    DefNew("DefNew"),
        //    ParNew("ParNew"),
        //    G1New("G1New"),
        //    ConcurrentMarkSweep("ConcurrentMarkSweep"),
        //    G1Old("G1Old"),
        //    GCNameEndSentinel("GCNameEndSentinel");
        for (GarbageCollectorMXBean gc : gcs) {
            garbageCollector.put(gc.getName(), gc);
        }
        result.put("GarbageCollector", garbageCollector);
        return result;
    }


    public int cpu() {
        Process process = null;
        BufferedReader bufferedReader = null;
        try {
            String CMD_PYTHON = "wmic cpu get loadpercentage";
            process = Runtime.getRuntime().exec(CMD_PYTHON);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String res;
            while ((res = bufferedReader.readLine()) != null) {
                // 去除输出发前后空格
                String strs = res.trim();
                if (!"".equals(strs) && !"LoadPercentage".equals(strs)) {
                    return Integer.parseInt(strs);
                }
            }
            bufferedReader.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        // 获取失败，或出现异常
        return -1;
    }

}
