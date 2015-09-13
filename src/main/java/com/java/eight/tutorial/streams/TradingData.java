package com.java.eight.tutorial.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

        // 2. What are all the unique cities where the traders work?
        List<String> answer2 = getTransactions().stream().map(Transaction::getTrader).map(Trader::getCity).distinct()
                .collect(Collectors.toList());

        System.out.println(answer2);

        // 3. Find all traders from Cambridge and sort them by name.
        List<Trader> answer3 = getTransactions().stream().map(Transaction::getTrader)
                .filter(x -> x.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName)).distinct()
                .collect(Collectors.toList());

        System.out.println(answer3);

        // 4. Return a string of all traders’ names sorted alphabetically.
        String answer4 = getTransactions().stream().map(transaction -> transaction.getTrader().getName()).distinct()
                .sorted().reduce("", (x, y) -> x + y);

        System.out.println(answer4);

        // 5. Are any traders based in Milan?
        boolean answer5 = getTransactions().stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println(answer5);

        // 6. Print all transactions’ values from the traders living in Cambridge.
        List<Integer> answer6 = getTransactions().stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).collect(Collectors.toList());

        System.out.println(answer6);

        // 7. What’s the highest value of all the transactions?
        Integer answer7 = getTransactions().stream().map(Transaction::getValue).reduce(Integer::max).get();

        System.out.println(answer7);

        // 8. Find the transaction with the smallest value.
        Optional<Transaction> answer8 = getTransactions().stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(answer8);

    }
}
