package pers.itlivemore.practicejvm.ch2;

/**
 * Java堆、方法区、Java栈之间的关系示例
 * Created by laigc on 2017/7/9.
 */
public class SimpleHeap {
    private int id;

    public SimpleHeap(int id) {
        this.id = id;
    }

    public void show() {
        System.out.println("My ID is " + id);
    }

    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);
        s1.show();
        s2.show();
    }
}
