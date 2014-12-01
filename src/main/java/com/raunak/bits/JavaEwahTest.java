package com.raunak.bits;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.util.BitSet;

/**
 * Created by raunak.agrawal on 11/30/14.
 */
public class JavaEwahTest {

    public static void main(String[] args) {

        EWAHCompressedBitmap ewahBitmap1 = EWAHCompressedBitmap.bitmapOf(0,2,55,64);

        System.out.println("memory usage: " + ewahBitmap1.sizeInBytes() + " bytes");


        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(2);
        bitSet.set(55);
        bitSet.set(64);

        System.out.println(bitSet.size());
    }
}
