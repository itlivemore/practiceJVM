package pers.itlivemore.practicejvm.ch2;

/**
 * 栈上分配
 * Created by laigc on 2017/7/22.
 */
public class OnStackTest {
    public static class User {
        public int id = 0;
        public String name = "";
    }

    public static void alloc() {
        User u = new User();
        u.id = 5;
        u.name = "abcd";
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
