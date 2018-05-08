package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.AgeType;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.testutils.TestUtils.thousand;

public class PersonChanceTest extends BaseChanceTest {

    @Test
    public void testAge() {
        thousand(i -> assertThat(chance.age(), is(allOf(greaterThanOrEqualTo(0), lessThan(101)))));
    }

    @Test
    public void testAge_Baby() {
        thousand(i -> assertThat(chance.age(AgeType.BABY), is(allOf(greaterThanOrEqualTo(0), lessThan(3)))));
    }

    @Test
    public void testAge_Child() {
        thousand(i -> assertThat(chance.age(AgeType.CHILD), is(allOf(greaterThanOrEqualTo(0), lessThan(13)))));
    }

    @Test
    public void testAge_Teen() {
        thousand(i -> assertThat(chance.age(AgeType.TEEN), is(allOf(greaterThan(12), lessThan(20)))));
    }

    @Test
    public void testAge_Adult() {
        thousand(i -> assertThat(chance.age(AgeType.ADULT), is(allOf(greaterThan(17), lessThan(66)))));
    }

    @Test
    public void testAge_Senior() {
        thousand(i -> assertThat(chance.age(AgeType.SENIOR), is(allOf(greaterThan(64), lessThan(101)))));
    }

    @Test
    public void testAge_All() {
        thousand(i -> assertThat(chance.age(AgeType.ALL), is(allOf(greaterThanOrEqualTo(0), lessThan(101)))));
    }
}
