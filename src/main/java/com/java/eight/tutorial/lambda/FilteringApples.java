package com.java.eight.tutorial.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class FilteringApples {

    public static void main(String... args) {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"),
                new Apple(120, "black"));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        System.out.println("Not green apples");
        Predicate<Apple> greenP = a -> "green".equals(a.getColor());
        List<Apple> greenApples2 = filterApples(inventory, greenP.negate().and(a -> a.getWeight() == 120));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, a -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        List<Apple> weirdApples = filterApples(inventory,
                (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        System.out.println(weirdApples);

        // Sorting apples
        System.out.println("Sorting Apples");
        FilteringApples.printSortedApples(inventory);
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void printSortedApples(List<Apple> apples) {

        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor).reversed());

        apples.forEach(System.out::println);

    }

    public static class Apple {
        private final int    weight;
        private final String color;

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public String getColor() {
            return color;
        }

        public String toString() {
            BiFunction<Integer, String, Apple> getApp = Apple::new;
            return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
        }
    }
}
