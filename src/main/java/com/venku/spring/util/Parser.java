package com.venku.spring.util;

import java.util.stream.Stream;

/**
 * Stream parser
 *
 * Created by venkatesha.chandru on 5/25/2016.
 */
public class Parser {
    public static Stream<String> parseLinesToUpperCase(Stream<String> lines) {
        return lines.map(String::toUpperCase);
    }
}
