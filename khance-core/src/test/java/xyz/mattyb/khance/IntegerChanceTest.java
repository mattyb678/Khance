package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static xyz.mattyb.khance.testutils.TestUtils.thousand;

public class IntegerChanceTest extends BaseChanceTest {

    @Test
    public void testInteger_ReturnsInteger() {
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
        thousand((iteration) -> assertThat(chance.integer(0), is(greaterThan(0))));
    }

    @Test
    public void testInteger_NegativeMin() {
        thousand((iteration) -> assertThat(chance.integer(-25), is(greaterThan(-26))));
    }

    @Test
    public void testInteger_BothNegative() {
        thousand((iteration) -> assertThat(chance.integer(-25, -1), is(allOf(greaterThan(-26), lessThan(0)))));
    }

    @Test
    public void testInteger_Abs() {
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
        assertThrows(IllegalArgumentException.class, () -> chance.integer(10_000, 5));
    }

    @Test
    public void testNatural() {
        final AtomicInteger positive = new AtomicInteger(0);

        thousand(i -> {
            if (chance.natural() >= 0) {
                positive.incrementAndGet();
            }
        });

        assertThat(positive.get(), is(1000));
    }

    @Test
    public void testNatural_NegativeMin() {
        assertThrows(IllegalArgumentException.class, () -> chance.natural(-19));
    }

    @Test
    public void testNatural_Min() {
        thousand(i -> assertThat(chance.natural(10_000), is(greaterThan(9_999))));
    }

    @Test
    public void testNatural_Max() {
        thousand(i -> assertThat(chance.natural(0, 1776), is(lessThan(1777))));
    }

    @Test
    public void testNatural_MinMax() {
        thousand(i -> assertThat(chance.natural(500, 510), is(allOf(greaterThan(499), lessThan(511)))));
    }

    @Test
    public void testNatural_BothZero() {
        thousand(i -> assertThat(chance.natural(0, 0), is(0)));
    }

    @Test
    public void testNatural_MinGreater() {
        assertThrows(IllegalArgumentException.class, () -> chance.natural(1986, 40));
    }

    @Test
    public void testNatural_Numerals() {
        thousand(i -> assertThat(chance.naturalNumerals(3), is(allOf(greaterThan(99), lessThan(1000)))));
    }

    @Test
    public void testNatural_NegativeNumeral() {
        assertThrows(IllegalArgumentException.class, () -> chance.naturalNumerals(-3));
    }

    @Test
    public void testNatural_ZeroNumeral() {
        assertThrows(IllegalArgumentException.class, () -> chance.naturalNumerals(0));
    }
}
