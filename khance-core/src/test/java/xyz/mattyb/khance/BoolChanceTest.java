package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class BoolChanceTest  extends BaseChanceTest {

    @Test
    public void testBool() {
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (chance.bool()) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testBool_Likelihood() {
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (chance.bool(30)) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(400))));

        trueCount.set(0);

        thousand(i -> {
            if (chance.bool(99)) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(greaterThan(900)));
    }

    @Test
    public void testBool_LikelihoodOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> chance.bool(-6));

        assertThrows(IllegalArgumentException.class, () -> chance.bool(19_000));
    }
}
