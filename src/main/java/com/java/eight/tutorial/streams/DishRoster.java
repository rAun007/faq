package com.java.eight.tutorial.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raunak.agrawal on 8/6/15.
 */
public class DishRoster {


    public static List<Dish> getMenu() {

        return Arrays.asList(new Dish("pork", false, 80, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER), new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER), new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }


    public static void main(String[] args) {

        List<Integer> number1 = new ArrayList<>();

        number1.add(1);
        number1.add(2);
        number1.add(3);

        List<Integer> number2 = new ArrayList<>();

        number2.add(1);
        number2.add(2);

        System.out.println(number1.stream().flatMap(x -> number2.stream().map(j -> new int[]{x, j}))
                .collect(Collectors.toList()));

    }
}
