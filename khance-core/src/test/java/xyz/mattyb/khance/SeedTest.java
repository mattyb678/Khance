package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class SeedTest {

    @Test
    public void testSameSeed_SameInteger() {
        Chance chance1 = ChanceFactory.chance(1776L);
        Chance chance2 = ChanceFactory.chance(1776L);

        assertThat(chance1.integer(), is(chance2.integer()));
    }

    @Test
    public void testDifferentSeed_DifferentInteger() {
        Chance chance1 = ChanceFactory.chance(1776L);
        Chance chance2 = ChanceFactory.chance(1492L);

        assertThat(chance1.integer(), is(not(chance2.integer())));
    }
}
