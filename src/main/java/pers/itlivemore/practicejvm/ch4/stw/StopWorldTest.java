package pers.itlivemore.practicejvm.ch4.stw;

import java.util.HashMap;

/**
 * 垃圾回收中的停顿现象：Stop-The-World
 * 25行代码当使用下列代码时
 * map.size() * 512 / 1024 / 1024 >= 900
 * 使用-Xmx1g -Xms1g -Xmn1m -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails
 * 使用下列代码时
 * map.size() * 512 / 1024 / 1024 >= 550
 * 使用-Xmx1g -Xms1g -Xmn900m -XX:SurvivorRatio=1 -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails
 * Created by laigc on 2017/7/30.
 */
public class StopWorldTest {
    // 线程不停消耗内存资源，以引起GC
    public static class MyThread extends Thread {
        HashMap map = new HashMap();

        @Override
        public void run() {
            try {
                while (true) {
                    // 内存消耗大于900M时，清空内存，防止内存溢出
                    if (map.size() * 512 / 1024 / 1024 >= 550) {
                        map.clear();
                        System.out.println("clear map");
                    }
                    byte[] b1;
                    for (int i = 0; i < 100; i++) {
                        b1 = new byte[512];
                        map.put(System.nanoTime(), b1);
                    }
                    Thread.sleep(1);
                }
            } catch (Exception e) {
            }
        }
    }

    // 第0.1秒在控制台上进行一次时间戳的输出
    public static class PrintThread extends Thread {
        public static final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        PrintThread printThread = new PrintThread();
        myThread.start();
        printThread.start();
    }
}
