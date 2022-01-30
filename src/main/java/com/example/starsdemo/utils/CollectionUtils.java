package com.example.starsdemo.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CollectionUtils {

    private static final Random random = new Random();

    private CollectionUtils() {
    }

    public static <T> List<T> shuffle(Collection<T> collection) {
        return shuffle(collection, random.nextLong());
    }

    public static <T> List<T> shuffle(Collection<T> collection, long seed) {

        record ElementWithOrder<T>(T element, double order) {
        }

        Random random = new Random(seed);

        return collection.stream()
            .map(element -> new ElementWithOrder<>(element, random.nextDouble()))
            .sorted(Comparator.comparing(ElementWithOrder::order))
            .map(ElementWithOrder::element)
            .toList();
    }
}
