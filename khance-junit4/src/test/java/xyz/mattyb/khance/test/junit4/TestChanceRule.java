package xyz.mattyb.khance.test.junit4;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class TestChanceRule {

    @Rule
    public ChanceRule chanceRule = new ChanceRule();

    @Rule
    public ChanceRule seeded = new ChanceRule(1986L);

    @Test
    public void testChance() {
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (chanceRule.getChance().bool()) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testPopulate() {
        thousand(i -> {
            TestPopulatedClass populated = (TestPopulatedClass) chanceRule.populate(TestPopulator.class);

            assertThat(populated.getAge(), is(allOf(greaterThan(-1), lessThan(101))));
            assertThat(populated.getYo().length(), is(15));
        });
    }

    @Test
    public void testSeeded() {
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (seeded.getChance().bool()) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }
}
