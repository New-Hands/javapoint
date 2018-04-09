package alg.sort;

import annotation.Notice;
import java.util.Arrays;

/**
 * 比较和交换 排序算法
 * @author lst
 */
public class InsertSort {
    private InsertSort() {}

    public static void main(String[] args) {
        int[] ints = {9, 5, 10, 5, 2, 6};
        sort(ints);
        System.out.printf(Arrays.toString(ints));
    }

    @Notice(createTime = "20180209", author = "lst", explain = "插入排序 与有序区进行比较")
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i + 1];
            int j = i + 1;
            while (j > 0 && temp > arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序
     * 冲破O（n^2）复杂度的第一批算法之一
     * 在直接插入排序的基础之上缩小增量
     * 为什么是缩小一半 不是缩小三分之一
     */
    @Notice()
    public static void shellSort() {

    }
}
