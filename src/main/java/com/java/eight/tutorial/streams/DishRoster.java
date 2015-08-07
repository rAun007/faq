package com.java.eight.tutorial.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by raunak.agrawal on 8/6/15.
 */
public class DishRoster {


    public static List<Dish> getMenu() {

        return Arrays.asList(new Dish("pork", false, 80, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));
    }


    public static void main(String[] args) {

        Collection<String> names = getMenu().stream().filter(d -> {
            System.out.println("FILTERING " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("MAPPING " + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toCollection(HashSet::new));
    }
}
