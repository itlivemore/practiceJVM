package pers.itlivemore.practicejvm.ch2;

/**
 * 打印函数最大的调用深度
 * Created by laigc on 2017/7/9.
 */
public class TestStackDeep {
    private static int count = 0;

    /*递归调用，无出口，最终会导致StackOverflowrErro*/
    public static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Exception e) {
            System.out.println("1deep of calling = " + count);
            e.printStackTrace();
        } finally {
            System.out.println("2deep of calling = " + count);
        }
    }
}
