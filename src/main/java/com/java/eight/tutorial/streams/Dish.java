package com.java.eight.tutorial.streams;

import lombok.Data;

/**
 * Created by raunak.agrawal on 8/6/15.
 */
@Data
public class Dish {

    private final String  name;
    private final boolean vegetarian;
    private final int     calories;
    private final Type    type;


    public enum Type {
        MEAT, FISH, OTHER
    }
}
