package xyz.mattyb.datafaker;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static xyz.mattyb.datafaker.testutils.TestUtils.thousand;

public class IntegerFakerTest {

    @Test
    public void testInteger_ReturnsInteger() {
        final Faker faker = FakerFactory.faker();
        final AtomicInteger positive = new AtomicInteger(0);
        thousand((iteration) -> {
            int rand = faker.integer(-9, 9);
            if (rand > 0) {
                positive.incrementAndGet();
            }
        });

        int positiveCount = positive.get();
        assertThat(positiveCount, is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testInteger_ZeroMin() {
        final Faker faker = FakerFactory.faker();
        thousand((iteration) -> assertThat(faker.integer(0), is(greaterThan(0))));
    }

    @Test
    public void testInteger_NegativeMin() {
        final Faker faker = FakerFactory.faker();
        thousand((iteration) -> assertThat(faker.integer(-25), is(greaterThan(-26))));
    }

    @Test
    public void testInteger_BothNegative() {
        final Faker faker = FakerFactory.faker();
        thousand((iteration) -> assertThat(faker.integer(-25, -1), is(allOf(greaterThan(-26), lessThan(0)))));
    }

    @Test
    public void testInteger_Abs() {
        final Faker faker = FakerFactory.faker();
        final AtomicInteger count = new AtomicInteger(0);

        thousand(iteration -> {
            if (Math.abs(faker.integer(-1, 1_000_000)) < 2) {
                count.incrementAndGet();
            }
        });

        assertThat(count.get(), is(lessThan(900)));
    }

    @Test
    public void testInteger_MinGreater() {
        final Faker faker = FakerFactory.faker();
        assertThrows(IllegalArgumentException.class, () -> {
            faker.integer(10_000, 5);
        });
    }
}
