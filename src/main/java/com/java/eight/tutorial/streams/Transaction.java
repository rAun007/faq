package com.java.eight.tutorial.streams;

import lombok.Data;

/**
 * Created by raunak.agrawal on 8/28/15.
 */
@Data
public class Transaction {

    private final Trader trader;
    private final int    year;
    private final int    value;
}
