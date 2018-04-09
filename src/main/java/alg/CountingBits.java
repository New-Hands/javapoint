package alg;

import annotation.Notice;

/**
 * 计算一个非负数n的（0）二进制表示中一的个数
 *
 * @author lst
 *
 */
public class CountingBits {
    public static void main(String[] args) {
        final int[] ints = countBits(58);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }

    /**
     * @param num
     * @return 返回数组
     */
    @Notice(createTime = "20180101", author = "lst", explain = "计算二进制表达式1的位数")
    public static int[] countBits(int num) {

        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }

}
