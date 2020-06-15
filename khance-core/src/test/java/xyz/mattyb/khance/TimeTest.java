package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

class TimeTest extends BaseChanceTest {

    @Test
    void testYear() {
        thousand(i -> {
            int currentYear = LocalDate.now().getYear();
            int year = chance.time.year();
            assertThat(year, allOf(greaterThan(currentYear - 1), lessThan(currentYear + 101)));
        });
    }
}
