package jdkunsafe;

import sun.misc.Unsafe;

/**
 * @author 李尚庭
 * @date 2018-9-10 上午 9:51
 */
public class UnsafeDemo {
    /**
     * 内存收集接口方法
     */
    Unsafe unsafe = Unsafe.getUnsafe();

    public static void main(String[] args) {
        UnsafeDemo unsafeDemo = new UnsafeDemo();
    }
}
