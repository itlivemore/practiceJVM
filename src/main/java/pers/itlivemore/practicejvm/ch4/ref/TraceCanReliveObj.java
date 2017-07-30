package pers.itlivemore.practicejvm.ch4.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 使用虚引用跟踪一个可复活对象的回收
 * Created by laigc on 2017/7/30.
 */
public class TraceCanReliveObj {
    public static TraceCanReliveObj obj;

    static ReferenceQueue<TraceCanReliveObj> phantomQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<TraceCanReliveObj> objt = null;
                    try {
                        objt = (PhantomReference<TraceCanReliveObj>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objt != null) {
                        System.out.println("TraceCanReliveObj is delete by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("TraceCanReliveObj finialize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am TraceCanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();

        phantomQueue = new ReferenceQueue<>();
        obj = new TraceCanReliveObj();
        // 构造虚引用，构造函数必须传引用队列
        PhantomReference<TraceCanReliveObj> phantomReference = new PhantomReference<>(obj, phantomQueue);

        obj = null;
        System.gc();
        Thread.sleep(1000);
        // 第一次GC后，由于对象可复活，所以GC无法回收该对象，obj不为null
        if (obj == null) {
            System.out.println("obj is null");
        } else {
            System.out.println("obj is not null");
        }

        System.out.println("第2次GC");
        obj = null;
        System.gc();
        Thread.sleep(1000);
        /*第2次GC，由于finalize()只会被调用一次，因此第2次GC会回收该对象，obj为null。
        同时引用队列也会捕获到对象的回收*/
        if (obj == null) {
            System.out.println("obj is null");
        } else {
            System.out.println("obj is not null");
        }
    }
}
