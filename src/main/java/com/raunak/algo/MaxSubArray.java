package com.raunak.algo;

import org.javatuples.Triplet;

public class MaxSubArray {

    public static void main(String[] args) {

        int[] a = { 13, -3, -25, 20, -3, -16, -23, -41, 18, 20, -7, 12, -5, -22, 15, -4, 7};

        int low = 0;
        int high = a.length - 1;

        System.out.println(maxSub(a, low, high));

        System.out.println(kadaneAlgo(a));
    }

    private static int kadaneAlgo(int[] a) {

        /**
         * Simple idea of the Kadane's algorithm is to look for all positive contiguous segments of the array
         * (max_ending_here is used for this). And keep track of maximum sum contiguous segment among all positive
         * segments (max_so_far is used for this). Each time we get a positive sum compare it with max_so_far and update
         * max_so_far if it is greater than max_so_far
         */
        int max_ending_here = 0;
        int max_so_far = 0;

        for (int i = 0; i < a.length; i++) {

            max_ending_here = max_ending_here + a[i];

            if (max_ending_here < 0) {
                max_ending_here = 0;
            }

            if (max_ending_here > max_so_far) {
                max_so_far = max_ending_here;
            }
        }

        return max_so_far;
    }

    private static Triplet<Integer, Integer, Integer> maxSub(int[] a, int low, int high) {

        if (low >= high) {
            return new Triplet<Integer, Integer, Integer>(low, high, a[low]);
        }
        int mid = (int) Math.floor((low + high) / 2);

        Triplet<Integer, Integer, Integer> left = maxSub(a, low, mid);
        Triplet<Integer, Integer, Integer> right = maxSub(a, mid + 1, high);
        Triplet<Integer, Integer, Integer> cross = findMaxCrossing(a, low, mid, high);

        if (left.getValue2() > right.getValue2() && left.getValue2() > cross.getValue2()) {
            return left;
        } else if (right.getValue2() > left.getValue2() && right.getValue2() > cross.getValue2()) {
            return right;
        } else {
            return cross;
        }

    }

    public static Triplet<Integer, Integer, Integer> findMaxCrossing(int[] a, int low, int mid, int high) {

        int lowerSum = -100000000;
        int sum = 0;

        int higherSum = -100000000;
        int maxLeft = mid;
        int maxRight = mid + 1;

        for (int i = mid; i >= low; i--) {
            sum = sum + a[i];
            if (sum > lowerSum) {
                lowerSum = sum;
                maxLeft = i;
            }
        }

        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum = sum + a[i];
            if (sum > higherSum) {
                higherSum = sum;
                maxRight = i;
            }
        }

        return new Triplet<Integer, Integer, Integer>(maxLeft, maxRight, lowerSum + higherSum);
    }
}
