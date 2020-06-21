package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Casing;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.test.utils.TestUtils.times;

public class TextChanceTest extends BaseChanceTest {

    @Test
    void testString_LengthUnspecified() {
        times(250, i ->
                assertThat(chance.string().length(), is(allOf(greaterThan(4), lessThan(21)))));
    }

    @Test
    void testString_NegativeLength() {
        assertThrows(IllegalArgumentException.class, () -> chance.string(-5));
    }

    @Test
    void testString_ZeroLength() {
        times(250, i -> assertThat(chance.string(0).length(), is(0)));
    }

    @Test
    void testString_CasingDefault() {
        times(250, i -> assertThat(chance.string(), matchesPattern("[a-z]+")));
    }

    @Test
    void testString_CasingUpper() {
        times(250, i ->
                assertThat(chance.string(Casing.UPPER), matchesPattern("[A-Z]+")));
    }

    @Test
    void testString_CasingMixed() {
        times(250, i ->
                assertThat(chance.string(Casing.MIXED), matchesPattern("[a-zA-Z]+")));
    }

    @Test
    void testString_LengthCasing() {
        times(250, i ->
                assertThat(chance.string(13, Casing.UPPER), matchesPattern("[A-Z]{13}")));
    }
}
