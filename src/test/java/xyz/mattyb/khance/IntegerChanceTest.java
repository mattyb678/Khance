package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static xyz.mattyb.khance.testutils.TestUtils.thousand;

public class IntegerChanceTest {

    @Test
    public void testInteger_ReturnsInteger() {
        final Chance chance = ChanceFactory.chance();
        final AtomicInteger positive = new AtomicInteger(0);
        thousand((iteration) -> {
            int rand = chance.integer(-9, 9);
            if (rand > 0) {
                positive.incrementAndGet();
            }
        });

        int positiveCount = positive.get();
        assertThat(positiveCount, is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testInteger_ZeroMin() {
        final Chance chance = ChanceFactory.chance();
        thousand((iteration) -> assertThat(chance.integer(0), is(greaterThan(0))));
    }

    @Test
    public void testInteger_NegativeMin() {
        final Chance chance = ChanceFactory.chance();
        thousand((iteration) -> assertThat(chance.integer(-25), is(greaterThan(-26))));
    }

    @Test
    public void testInteger_BothNegative() {
        final Chance chance = ChanceFactory.chance();
        thousand((iteration) -> assertThat(chance.integer(-25, -1), is(allOf(greaterThan(-26), lessThan(0)))));
    }

    @Test
    public void testInteger_Abs() {
        final Chance chance = ChanceFactory.chance();
        final AtomicInteger count = new AtomicInteger(0);

        thousand(iteration -> {
            if (Math.abs(chance.integer(-1, 1_000_000)) < 2) {
                count.incrementAndGet();
            }
        });

        assertThat(count.get(), is(lessThan(900)));
    }

    @Test
    public void testInteger_MinGreater() {
        final Chance chance = ChanceFactory.chance();
        assertThrows(IllegalArgumentException.class, () -> {
            chance.integer(10_000, 5);
        });
    }
}
