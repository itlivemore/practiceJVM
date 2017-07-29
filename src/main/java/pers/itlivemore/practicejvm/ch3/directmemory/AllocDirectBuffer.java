package pers.itlivemore.practicejvm.ch3.directmemory;

import java.nio.ByteBuffer;

/**
 * 测试堆内存与直接内存申请空间速度
 * Created by laigc on 2017/7/29.
 */
public class AllocDirectBuffer {
    // 直接内存的申请
    public void directAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("directAllocate:" + (endTime - startTime));
    }

    // 堆内存的申请
    public void bufferAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("bufferAllocate:" + (endTime - startTime));
    }

    public static void main(String[] args) {
        AllocDirectBuffer alloc = new AllocDirectBuffer();
        alloc.bufferAllocate();
        alloc.directAllocate();

        alloc.bufferAllocate();
        alloc.directAllocate();
    }
}
