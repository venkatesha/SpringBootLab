package com.venku.spring.util;

import javaslang.Lazy;
import javaslang.Value;
import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.collection.Stream;
import javaslang.control.Try;

/**
 * Peek values
 *
 * Created by venkatesha.chandru on 5/25/2016.
 */
public class PeekSomeValues {

    public static void main(String[] args) {
        Seq<Value<String>> values = List.of(

                // a lazy value
                Lazy.of(() -> "test"),

                // a Success/Failure
                Try.of(() -> {
                    throw new Error();
                }),

                // a lazy linked list
                Stream.of("Hi")
                );

        values.forEach(value -> {

            value
                    .peek(s -> System.out.printf("Hashcode of %s is %s", s, s.hashCode()))
                    .getOrElse("")
                    .isEmpty();

        });
    }
}
