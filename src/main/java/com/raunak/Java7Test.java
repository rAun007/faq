package com.raunak;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Java7Test {

    private static final int binaryNumber = 0b101;

    private static final int underscore   = 123_45;

    public static void main(String[] args) {

        System.out.println(binaryNumber);

        System.out.println(underscore);
    }

    // This try-with-resource works because BufferedReader implements Closeable which extends AutoCloseable
    static String readFirstLineFromFile(String path) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            return br.readLine();
        }
    }
}
