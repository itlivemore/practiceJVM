package pers.itlivemore.practicejvm.ch2;

/**
 * 函数的局部变量和参数对调用深度的影响。
 * recursion1()有3个参数和10个局部变量，因此其局部变量表中含有13个变量。
 * recursion2()没有参数和局部变量。所以recursion2()有更深的调用层次。
 * Created by laigc on 2017/7/9.
 */
public class TestStackDeep2 {
    private static int count = 0;

    public static void recursion1(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, j = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion1(a, b, c);
    }

    public static void recursion2() {
        count++;
        recursion2();
    }

    public static void main(String[] args) {
        try {
            recursion1(0L, 0L, 0L);
//            recursion2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("deep of calling = " + count);
        }
    }

}

