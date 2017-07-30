package pers.itlivemore.practicejvm.ch4.ref;

import java.lang.ref.SoftReference;

/**
 * 软引用会在系统堆内存不足时被回收
 * 使用-Xmx10m运行
 * Created by laigc on 2017/7/29.
 */
public class SoftRef {
    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 创建User实例，user是强引用
        User user = new User(1, "abc");
        // 通过强引用user建立软引用
        SoftReference<User> softReference = new SoftReference<>(user);
        // 去除强引用
        user = null;

        // 从软引用中重新获得强引用对象
        System.out.println(softReference.get());
        System.gc();
        System.out.println("After GC");
        // 虽然执行了GC，但是内存充足，软引用对象未被回收
        System.out.println(softReference.get());

        byte[] b = new byte[1024 * 925 * 7];
        System.gc();
        // 上面分配了一块大的内存，内存资源紧张，GC后软引用被回收
        System.out.println(softReference.get());
    }

}
