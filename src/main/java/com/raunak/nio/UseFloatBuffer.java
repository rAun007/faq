package com.raunak.nio;

import java.nio.FloatBuffer;

/**
 * Created by raunak.agrawal on 6/10/15.
 */
public class UseFloatBuffer {

    public static void main(String[] args) {

        FloatBuffer floatBuffer = FloatBuffer.allocate(10);

        for (int i = 0; i < floatBuffer.capacity(); i++) {
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
            System.out.println("Putting " + f);
            floatBuffer.put(f);
        }

        floatBuffer.flip();

        while (floatBuffer.hasRemaining()) {
            float f = floatBuffer.get();
            System.out.println( f );
        }
    }
}
