package com.shl.java基础.类.继承;

import java.io.File;
import java.io.IOException;

public class 继承的双面性 {

    public static void main(String[] args) throws IOException {
        Child c = new Child();
        c.addAll(new int[]{1, 2, 3});
        System.out.println(c.getSum());
        System.out.println(Test.ABC.getA());

        File f = new File("C:\\Users\\18256\\Desktop\\a");
        File d = new File("C:\\Users\\18256\\Desktop\\b");
        File[] fs = f.listFiles();
        for (File file : fs) {

            File dest = new File(d.getAbsolutePath() + "/" + file.getName());
            if (dest.exists()) {
                System.out.println("目标文件已存在，删除" + dest.delete());
            }
            System.out.println(file.renameTo(dest));
            // Files.move(Paths.get(file.getAbsolutePath()),Paths.get( d.getAbsolutePath() + "/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
        }


    }


}

class Base {
    private static final int MAX_NUM = 1000;
    private int[] arr = new int[MAX_NUM];
    private int count;

    public void add(int num) {
        if (count < MAX_NUM) {
            arr[count++] = num;
        }
    }

    public void addAll(int[] numArr) {
        for (int ele : numArr) {
            add(ele);
        }
    }
}

class Child extends Base {
    private long sum;

    @Override
    public void add(int num) {
        super.add(num);
        sum += num;
    }


    public long getSum() {
        return sum;
    }
}

interface IDemo {
    default void defaultMethod() {
        System.out.println("It is default method");

    }

    default void hi() {

    }
}

class Demo implements IDemo {
    // 可以实现default方法
    // 也可以不选择实现default方法
    // 这样的好处时，增强接口能力的同时，不影响子类实现，子类可以选择实现，也可以选择忽略
}

/**
 * 枚举
 */
enum Test {

    SMALL("1"), ABC("2");

    private String a;

    private Test(String a) {
        this.a = a;
    }

    public String getA() {
        return this.a;
    }

}
