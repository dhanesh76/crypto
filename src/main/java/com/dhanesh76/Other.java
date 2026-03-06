package com.dhanesh76.out.com.dhanesh76;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Other {

    static void main(String[] args) {
        var array = new int[]{12, 12, 12, 23, 43};

        int[]  res = Arrays
                .stream(array)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(3)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
