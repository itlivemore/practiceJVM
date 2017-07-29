package pers.itlivemore.practicejvm.ch4.ref;

/**
 * 对象有可能在finalize()函数中复活自己
 * Created by laigc on 2017/7/29.
 */
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finialize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj is null");
        } else {
            System.out.println("obj is not null");
        }

        System.out.println("第2次GC");
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj is null");
        } else {
            System.out.println("obj is not null");
        }
    }
}
