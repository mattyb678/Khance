package xyz.mattyb.datafaker;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.datafaker.testutils.TestUtils.thousand;

public class BoolPossibilityTest {

    @Test
    public void testBool() {
        final Possibility possibility = FakerFactory.possibility();
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (possibility.bool()) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testBool_Likelihood() {
        final Possibility possibility = FakerFactory.possibility();
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (possibility.bool(30)) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(400))));

        trueCount.set(0);

        thousand(i -> {
            if (possibility.bool(99)) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(greaterThan(900)));
    }
}
