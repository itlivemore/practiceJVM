package pers.itlivemore.practicejvm.ch4.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用队列
 * Created by laigc on 2017/7/29.
 */
public class WeakRefQ {
    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[id=" + String.valueOf(id) + ",name=" + name + "]";
        }
    }

    static ReferenceQueue<User> softQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            // 跟踪引用队列，打印对象的回收情况
            while (true) {
                if (softQueue != null) {
                    UserWeakReference obj = null;
                    try {
                        obj = (UserWeakReference) softQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (obj != null) {
                        System.out.println("user id " + obj.uid + " is delete");
                    }
                }
            }
        }
    }

    /**
     * 自定义的弱引用类，目的是记录User.id，后续在引用队列中就可以通过uid字段知道哪个类被回收了
     */
    public static class UserWeakReference extends WeakReference<User> {
        int uid;

        public UserWeakReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            uid = referent.id;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "geym");
        softQueue = new ReferenceQueue<User>();
        /*在创建弱引用时，指定了一个弱引用队列，当给定的对象实例被回收时，就会被加入
        * 这个引用队列，通过访问该队列可以跟踪对象的回收情况*/
        UserWeakReference userSoftRef = new UserWeakReference(u, softQueue);
        u = null;
        System.out.println(userSoftRef.get());
        System.gc();
        // 不管当前内存空间足够与否，都会回收它的内存
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        Thread.sleep(1000);
    }
}
