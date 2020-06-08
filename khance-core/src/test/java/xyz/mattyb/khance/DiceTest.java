package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class DiceTest extends BaseChanceTest {

    @Test
    public void testD4() {
        thousand(i -> {
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
        thousand(i -> {
            assertThat(chance.dice.d6(), allOf(greaterThan(0), lessThan(7)));
            List<Integer> rolls = chance.dice.d6(2);
            assertThat(rolls, hasSize(2));
            for (Integer roll : rolls) {
                assertThat(roll, allOf(greaterThan(0), lessThan(7)));
            }
        });
    }
}
