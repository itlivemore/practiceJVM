package pers.itlivemore.practicejvm.ch1;

/**
 * 获得float的IEEE754表示
 * Created by laigc on 2017/7/8.
 */
public class FloatNumber {
    public static void main(String[] args) {
        float a = -5;
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(a)));
    }
}
