package xyz.mattyb.khance.test.utils;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class TestUtils {
    private TestUtils() {
        // empty on purpose
    }

    public static void times(int times, Consumer<Integer> fn) {
        IntStream.range(0, times)
                .forEach(fn::accept);
    }

    public static void thousand(Consumer<Integer> fn) {
        times(1000, fn);
    }
}
