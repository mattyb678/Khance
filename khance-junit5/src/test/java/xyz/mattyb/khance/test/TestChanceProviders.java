package xyz.mattyb.khance.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.enums.Casing;
import xyz.mattyb.khance.test.core.annotations.*;
import xyz.mattyb.khance.test.junit5.ChanceProviderExtension;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

@ExtendWith(ChanceProviderExtension.class)
public class TestChanceProviders {

    @ChanceProvider
    private Chance chance;

    @Test
    public void testBool_BigBoolean(@BoolProvider Boolean val) {
        assertThat(val, is(not(nullValue())));
    }

    @Test
    public void testBool(@BoolProvider boolean val) {
        assertThat(val, is(not(nullValue())));
    }

    @Test
    public void testBool_NotAssignable(@BoolProvider String val) {
        assertThat(val, is(nullValue()));
    }

    @RepeatedTest(25)
    public void testInteger(@IntegerProvider(min = -10, max = 10) int val) {
        assertThat(val, is(allOf(greaterThan(-11), lessThan(11))));
    }

    @RepeatedTest(25)
    public void testInteger_BigInt(@IntegerProvider(min = -10, max = 10) Integer val) {
        assertThat(val, is(allOf(greaterThan(-11), lessThan(11))));
    }

    @Test
    public void testInteger_NotAssignable(@IntegerProvider(min = -10, max = 10) String val) {
        assertThat(val, is(nullValue()));
    }

    @RepeatedTest(25)
    public void testNatural_Numeral(@NaturalProvider(numerals = 1) int val) {
        assertThat(val, is(allOf(greaterThan(-1), lessThan(10))));
    }

    @RepeatedTest(25)
    public void testNatural_BigInt(@NaturalProvider(min = 10, max = 20) Integer val) {
        assertThat(val, is(allOf(greaterThan(9), lessThan(21))));
    }

    @RepeatedTest(25)
    public void testNatural(@NaturalProvider(min = 10, max = 20) int val) {
        assertThat(val, is(allOf(greaterThan(9), lessThan(21))));
    }

    @Test
    public void testNatural(@NaturalProvider(min = 10, max = 20) String val) {
        assertThat(val, is(nullValue()));
    }

    @RepeatedTest(10)
    public void testString_Casing(@StringProvider String str) {
        assertThat(str, matchesPattern("[a-z]+"));
    }

    @RepeatedTest(10)
    public void testString_UpperCasing(@StringProvider(casing = Casing.UPPER) String str) {
        assertThat(str, matchesPattern("[A-Z]+"));
    }

    @RepeatedTest(10)
    public void testString_MixedCasing(@StringProvider(casing = Casing.MIXED) String str) {
        assertThat(str, matchesPattern("[a-zA-Z]+"));
    }

    @RepeatedTest(10)
    public void testString_Length(@StringProvider(length = 22) String str) {
        assertThat(str.length(), is(22));
    }

    @Test
    public void testChance() {
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (chance.bool()) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testChance_Parameter(@ChanceProvider Chance otherChance) {
        final AtomicInteger trueCount = new AtomicInteger(0);

        thousand(i -> {
            if (otherChance.bool()) {
                trueCount.incrementAndGet();
            }
        });

        assertThat(trueCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }
}
