package pers.itlivemore.practicejvm.ch3.directmemory;

import java.nio.ByteBuffer;

/**
 * 测试直接内存与堆内存的读写速度
 * Created by laigc on 2017/7/29.
 */
public class AccessDirectBuffer {

    // 对直接内存的读写
    public void directAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);
            }
            byteBuffer.flip();
            for (int j = 0; j < 99; j++) {
                byteBuffer.getInt();
            }
            byteBuffer.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testDirectWrite:" + (endTime - startTime));
    }

    // 对堆内存读写
    public void bufferAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);
            }
            byteBuffer.flip();
            for (int j = 0; j < 99; j++) {
                byteBuffer.getInt();
            }
            byteBuffer.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testDirectWrite:" + (endTime - startTime));
    }

    public static void main(String[] args) {
        AccessDirectBuffer alloc = new AccessDirectBuffer();
        alloc.bufferAccess();
        alloc.directAccess();

        alloc.bufferAccess();
        alloc.directAccess();
    }
}
