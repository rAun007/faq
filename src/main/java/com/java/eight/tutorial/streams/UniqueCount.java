package com.java.eight.tutorial.streams;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by raunak.agrawal on 9/12/15.
 */
public class UniqueCount {

    public static void findUniqueChars() {

        String[] arrayOfWords = { "My", "Name", "Is", "Raunak"};

        Stream<String> wordStream = Arrays.stream(arrayOfWords);

        Stream<String[]> wordSplitted = wordStream.map(x -> x.split(""));

        // Stream<Stream<String>> flattenedStream = wordSplitted.map(x -> Arrays.stream(x));
        Stream<String> flattenedStream = wordSplitted.flatMap(Arrays::stream);

        System.out.println(flattenedStream.distinct().collect(toList()));
    }

    public static void main(String[] args) {

        Stream.generate(Math::random).limit(5).forEach(System.out::println); //infinite stream
        Stream.iterate(2, c -> c+2).limit(5).forEach(System.out::println); //another infinite

        findUniqueChars();
        findWordCount();
    }

    private static void findWordCount() {

        ///Paths.get()
    }
}
