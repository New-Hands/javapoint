package alg.sort;

import java.util.Arrays;

public class SelectSort {
    private SelectSort() {

    }

    /**
     * 最值往前放或往后放有不同的实现
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int key = 0;
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[key] < arr[j + 1]) {
                    key = j+1;
                }
            }
            swap(arr, key, arr.length - i - 1);
        }
    }

    public static void heapSort(int arr) {

    }

    public static void swap(int[] arr, int fromIndex, int toIndex) {
        int temp = arr[toIndex];
        arr[toIndex] = arr[fromIndex];
        arr[fromIndex] = temp;
    }

    public static void main(String[] args) {
        int[] ints = {6, 8, 5, 77, 83, 65, 22, 4, 88};
        sort(ints);
        System.out.printf(Arrays.toString(ints));
    }
}
