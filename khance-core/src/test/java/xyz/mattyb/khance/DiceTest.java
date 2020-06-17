package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;
import static xyz.mattyb.khance.test.utils.TestUtils.times;

public class DiceTest extends BaseChanceTest {

    @Test
    public void testD4() {
        times(50, i -> {
            assertThat(chance.dice.d4(), allOf(greaterThan(0), lessThan(5)));
            List<Integer> rolls = chance.dice.d4(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(5)));
            }
        });
    }

    @Test
    public void testD6() {
        times(50, i -> {
            assertThat(chance.dice.d6(), allOf(greaterThan(0), lessThan(7)));
            List<Integer> rolls = chance.dice.d6(2);
            assertThat(rolls, hasSize(2));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(7)));
            }
        });
    }

    @Test
    public void testD8() {
        times(60, i -> {
            assertThat(chance.dice.d8(), allOf(greaterThan(0), lessThan(9)));
            List<Integer> rolls = chance.dice.d8(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(9)));
            }
        });
    }

    @Test
    public void testD10() {
        times(100, i -> {
            assertThat(chance.dice.d10(), allOf(greaterThan(0), lessThan(11)));
            List<Integer> rolls = chance.dice.d10(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(11)));
            }
        });
    }

    @Test
    public void testD12() {
        times(100, i -> {
            assertThat(chance.dice.d12(), allOf(greaterThan(0), lessThan(13)));
            List<Integer> rolls = chance.dice.d12(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(13)));
            }
        });
    }

    @Test
    public void testD20() {
        times(200, i -> {
            assertThat(chance.dice.d20(), allOf(greaterThan(0), lessThan(21)));
            List<Integer> rolls = chance.dice.d20(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(21)));
            }
        });
    }

    @Test
    public void testD30() {
        times(300, i -> {
            assertThat(chance.dice.d30(), allOf(greaterThan(0), lessThan(31)));
            List<Integer> rolls = chance.dice.d30(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(31)));
            }
        });
    }

    @Test
    public void testD100() {
        thousand(i -> {
            assertThat(chance.dice.d100(), allOf(greaterThan(0), lessThan(101)));
            List<Integer> rolls = chance.dice.d100(4);
            assertThat(rolls, hasSize(4));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(101)));
            }
        });
    }
}
