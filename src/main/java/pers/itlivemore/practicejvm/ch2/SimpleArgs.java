package pers.itlivemore.practicejvm.ch2;

/**
 * 设置虚拟机的参数示例
 * Created by laigc on 2017/7/9.
 */
public class SimpleArgs {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("参数" + (i + 1) + ":" + args[i]);
        }
        // 打印系统最大可用堆内存
        System.out.println("-Xmx" + Runtime.getRuntime().maxMemory() / 1000 / 1000 + "M");
    }
}
