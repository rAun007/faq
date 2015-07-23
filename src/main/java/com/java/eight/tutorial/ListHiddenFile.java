package com.java.eight.tutorial;


import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * Created by raunak.agrawal on 7/18/15.
 */
public class ListHiddenFile {

    public static void main(String[] args) {

        // Old way
        File[] hiddenFiles = new File("/Users/raunak.agrawal").listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        System.out.println("OLD JAVA WAY");

        for (File hiddenFile : hiddenFiles) {
            System.out.println(hiddenFile);
        }
        System.out.println("------------");

        System.out.println("JAVA 8 WAY");

        hiddenFiles = new File("/Users/raunak.agrawal").listFiles(File::isHidden);

        for (File hiddenFile : hiddenFiles) {
            System.out.println(hiddenFile);
        }
        System.out.println("------------");
    }
}
