package com.venku.spring.util;

import javaslang.control.Try;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

/**
 * Try function demo
 *
 * Created by venkatesha.chandru on 5/25/2016.
 */
public class TryLab {

    public static void main(String[] args) {
        System.out.println(Try.run(() -> new Socket("google.com", 80).close()).isSuccess());

        readLinesFromFileExample();
    }

    private static void readLinesFromFileExample() {
        System.out.println("Lines before uppercase:\n");

        readLines()
                .andThen(Console::printLines);

        System.out.println("\nLines after uppercase:\n");

        readLines()
                .map(Parser::parseLinesToUpperCase)
                .andThen(Console::printLines);
    }

    private static Try<Stream<String>> readLines() {
        final Path path = Paths.get("src/main/resources/Hello.txt");
        return Try.of(() -> lines(path));
    }
}
