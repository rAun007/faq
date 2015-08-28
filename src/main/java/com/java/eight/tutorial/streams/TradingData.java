package com.java.eight.tutorial.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by raunak.agrawal on 8/28/15.
 */
public class TradingData {

    public static List<Transaction> getTransactions() {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012,
                1000), new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710), new Transaction(mario,
                2012, 700), new Transaction(alan, 2012, 950));

        return transactions;
    }

    public static List<Trader> getTraders() {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");



        return Arrays.asList(alan, brian, mario, raoul);
    }

    public static void main(String[] args) {

        // 1. Find all transactions in the year 2011 and sort them by value (small to high).

        List<Transaction> answer1 = getTransactions().stream().filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        System.out.println(answer1);

        //2. What are all the unique cities where the traders work?
        List<String> answer2 = getTraders().stream().map(Trader::getCity).distinct().collect(Collectors.toList());
        System.out.println(answer2);
                
    }
}
