package alg.sort;

import java.util.Arrays;

public class BubbleSort {
    private int[] tess = new int[8];

    private BubbleSort() {

    }

    public static <T> T[] Bubble(T[] arr) {
        T[] it = (T[]) new Integer[8];
        return arr;
    }

    /**
     * 冒泡排序每一次比较为true后都会进行交换，简单选择排序区别在于它只记录最值的坐标每一轮最后进行交换
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int fromIndex, int toIndex) {
        int temp = arr[toIndex];
        arr[toIndex] = arr[fromIndex];
        arr[fromIndex] = temp;
    }

    public static void main(String[] args) {
        int[] ints = {6, 8, 5, 8, 3, 7, 2};
        sort(ints);
        System.out.printf(Arrays.toString(ints));

    }
}
