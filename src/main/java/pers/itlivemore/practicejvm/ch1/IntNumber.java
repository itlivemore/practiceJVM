package pers.itlivemore.practicejvm.ch1;

/**
 * 打印整数的补码
 * Created by laigc on 2017/7/8.
 */
public class IntNumber {
    public static void main(String[] args) {
        int a = -10;
        /*进行32次循环（因为int是32位），每次循环取出int值中的一位，
        *0x80000000是首位为1，其余位为0的整数，通过右移i位，定位到要获取的第i位，
        * 并将除该位其他位都设置为0，而该位不变，最后右移至最右，打印*/
        for (int i = 0; i < 32; i++) {
            int t = (a & 0x80000000 >>> i) >>> (31 - i);
            System.out.print(t);
        }
        System.out.println();
        System.out.println(Integer.toBinaryString(a));
    }
}
