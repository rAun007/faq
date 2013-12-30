package com.raunak.sort;
public class QuickSort {

    public static void main(String[] args) {

        Integer[] input = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };

        quickSort(input, 0, input.length);
        
        for(int i = 0; i < input.length; i++){
            System.out.println(input[i]);
        }
    }

    public static int partition(Integer[] input, int l, int u) {

        int x = input[l];

        int t = l;

        for (int i = t + 1; i < u; i++) {

            if (input[i] <= x) {

                t = t + 1;

                int temp = input[t];
                input[t] = input[i];
                input[i] = temp;

            }
        }

        input[l] = input[t];
        input[t] = x;

        return t;
    }

    public static Integer[] quickSort(Integer[] input, int l, int u) {

        if (l >= u) {
            return input;
        }

        int t = partition(input, l, u);

        quickSort(input, l, t);

        quickSort(input, t + 1, u);

        return input;
    }
}
