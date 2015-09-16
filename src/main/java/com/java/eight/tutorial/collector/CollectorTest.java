package com.java.eight.tutorial.collector;

import com.java.eight.tutorial.streams.Dish;

import java.util.*;

import static com.java.eight.tutorial.streams.DishRoster.getMenu;
import static java.util.stream.Collectors.*;

/**
 * Created by raunak.agrawal on 9/15/15.
 */
public class CollectorTest {

    public static void main(String[] args) {

        // Group dish by type.
        Map<Dish.Type, List<Dish>> dishMap = getMenu().stream().collect(groupingBy(Dish::getType));

        System.out.println(dishMap);

        // How many dishes?
        long howManyDishes = getMenu().stream().collect(counting());
        System.out.println(howManyDishes);
        // Another way
        System.out.println(getMenu().stream().count());


        // Max calorie dish?
        Comparator<Dish> dishCalorieCom = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCalarieDish = getMenu().stream().collect(maxBy(dishCalorieCom));
        System.out.println(mostCalarieDish);
        // Another way
        System.out.println(getMenu().stream().max(dishCalorieCom));

        // Total Calories
        long totalCal = getMenu().stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCal);
        // ANother way
        System.out.println(getMenu().stream().mapToInt(Dish::getCalories).reduce(0, (x, y) -> x + y));

        // Avg Calorie
        double avgCalories = getMenu().stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        // Stats on calories
        IntSummaryStatistics stats = getMenu().stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(stats);

        // Print all dish name as a string
        String shortName = getMenu().stream().map(Dish::getName).collect(joining());
        System.out.println(shortName);

        shortName = getMenu().stream().map(Dish::getName).collect(joining("||"));
        System.out.println(shortName);

    }
}
