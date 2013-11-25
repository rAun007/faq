package com.raunak.sort;

public class InsertionSort {

    public static void main(String[] args) {

        int[] array = { 2, 1, 10, 4, 7, 5 };

        int[] sorted = insertionSort(array);
        
        for(int i = 0 ; i < sorted.length; i++){
            System.out.println(sorted[i]);
        }
    }

    private static int[] insertionSort(int[] array) {

        if (array.length < 2) {
            return array;
        }

        for (int i = 1; i < array.length; i++) {

            int key = array[i];

            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }
}
