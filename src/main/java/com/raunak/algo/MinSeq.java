package com.raunak.algo;

// Java program to find smallest window containing
// all characters of a pattern.

public class MinSeq {
    static final int no_of_chars = 256;

    // Function to find smallest window containing
    // all characters of 'pat'
    static String findSubString(String mainString, String pat) {
        int len1 = mainString.length();
        int len2 = pat.length();

        // check if string's length is less than pattern's
        // length. If yes then no such window can exist
        if (len1 < len2) {
            System.out.println("No such window exists");
            return "";
        }

        int patternFreq[] = new int[no_of_chars];
        int mainStringFreq[] = new int[no_of_chars];

        // store occurrence ofs characters of pattern
        for (int i = 0; i < len2; i++) {
            patternFreq[pat.charAt(i)]++;
        }

        int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;

        // start traversing the string
        int count = 0; // count of characters
        for (int i = 0; i < len1; i++) {
            // count occurrence of characters of string
            char charAti = mainString.charAt(i);
            mainStringFreq[charAti]++;

            // If string's char matches with pattern's char
            // then increment count
            if (patternFreq[charAti] != 0 && mainStringFreq[charAti] <= patternFreq[charAti]) {
                count++;
            }

            // if all the characters are matched
            if (count == len2) {
                // Try to minimize the window i.e., check if
                // any character is occurring more no. of times
                // than its occurrence in pattern, if yes
                // then remove it from starting and also remove
                // the useless characters.
                while (mainStringFreq[mainString.charAt(start)] > patternFreq[mainString.charAt(start)]
                        || patternFreq[mainString.charAt(start)] == 0) {

                    if (mainStringFreq[mainString.charAt(start)] > patternFreq[mainString.charAt(start)]) {

                        mainStringFreq[mainString.charAt(start)]--;
                    }
                    start++;
                }

                // update window size
                int len_window = i - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }

        // If no window found
        if (start_index == -1) {
            System.out.println("No such window exists");
            return "";
        }

        // Return substring starting from start_index
        // and length min_len
        return mainString.substring(start_index, start_index + min_len);
    }

    // Driver Method
    public static void main(String[] args) {
        String str = "hthis is a test string";
        String pat = "tist";

        System.out.print("Smallest window is :  " + findSubString(str, pat));
    }
}
