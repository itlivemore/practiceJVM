package pers.itlivemore.practicejvm.ch4.ref;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * Created by laigc on 2017/7/30.
 */
public class WeakRef {
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

    public static void main(String[] args) {
        User u = new User(1, "aa");
        // 构造弱引用
        WeakReference<User> userWeakReference = new WeakReference<>(u);
        // 去除强引用
        u = null;
        System.out.println(userWeakReference.get());
        System.gc();
        // 不管当前内存空间足够与否，都会回收它的内存
        System.out.println("After GC");
        System.out.println(userWeakReference.get());
    }
}
