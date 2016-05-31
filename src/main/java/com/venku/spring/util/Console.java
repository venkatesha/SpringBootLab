package com.venku.spring.util;

import java.util.stream.Stream;

/**
 * Utility class to print to console
 *
 * Created by venkatesha.chandru on 5/25/2016.
 */
public class Console {

    public static void printLines(Stream<String> lines) {
        lines.forEach(System.out::println);
    }

}
