package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Casing;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static xyz.mattyb.khance.testutils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.testutils.TestUtils.thousand;

public class TextChanceTest extends BaseChanceTest {

    @Test
    public void testString_LengthUnspecified() {
        thousand(i -> assertThat(chance.string().length(), is(allOf(greaterThan(4), lessThan(21)))));
    }

    @Test
    public void testString_NegativeLength() {
        assertThrows(IllegalArgumentException.class, () -> chance.string(-5));
    }

    @Test
    public void testString_ZeroLength() {
        thousand(i -> assertThat(chance.string(0).length(), is(0)));
    }

    @Test
    public void testString_CasingDefault() {
        thousand(i -> assertThat(chance.string(), matchesPattern("[a-z]+")));
    }

    @Test
    public void testString_CasingUpper() {
        thousand(i -> assertThat(chance.string(Casing.UPPER), matchesPattern("[A-Z]+")));
    }

    @Test
    public void testString_CasingMixed() {
        thousand(i -> assertThat(chance.string(Casing.MIXED), matchesPattern("[a-zA-Z]+")));
    }
}
