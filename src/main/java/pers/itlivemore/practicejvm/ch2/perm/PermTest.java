package pers.itlivemore.practicejvm.ch2.perm;

import java.lang.management.ManagementFactory;
import java.util.HashMap;

/**
 * JDK1.6 1.7 -XX:+PrintGCDetails -XX:PermSize=5M -XX:MaxPermSize=5m
 * <p>
 * JDK1.8 -XX:+PrintGCDetails -XX:MaxMetaspaceSize=5M
 *
 * @author Geym
 */
public class PermTest {
    public static void main(String[] args) {
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);
        int i = 0;
        try {
            for (i = 0; i < 100000; i++) {
                CglibBean bean = new CglibBean("pers.itlivemore.practicejvm.ch2.perm" + i, new HashMap());
            }
        } catch (Exception e) {
            System.out.println("total create count:" + i);
            throw e;
        }
    }
}
