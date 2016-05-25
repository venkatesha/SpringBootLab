package com.venku.spring.util;

import static javaslang.API.*;

import javaslang.control.Option;

/**
 * Pattern Matcher Demo
 *
 * Created by venkatesha.chandru on 5/25/2016.
 */
public class PatternMatcher {

    public static void main(String[] args) {
        int i = 0;
        Option<String> s = Match(i).option(
                Case($(0), "Zero"),
                Case($(1), "One")
                );
        System.out.println(s.get());
    }
}
