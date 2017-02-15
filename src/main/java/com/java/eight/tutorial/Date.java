package com.java.eight.tutorial;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by raunak.agrawal on 2/15/17.
 */
public class Date {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);

        System.out.println(localDateTime);
    }
}
