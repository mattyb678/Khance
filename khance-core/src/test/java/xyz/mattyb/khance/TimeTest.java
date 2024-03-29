package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;
import static xyz.mattyb.khance.test.utils.TestUtils.times;

class TimeTest extends BaseChanceTest {

    @Test
    void testPeriod() {
        final AtomicInteger am = new AtomicInteger(0);
        thousand(i -> {
            String period = chance.time.period();
            assertThat(period, anyOf(is("am"), is("pm")));
            if ("am".equals(period)) {
                am.incrementAndGet();
            }
        });
        assertThat(am.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    void testPeriod_Upper() {
        final AtomicInteger am = new AtomicInteger(0);
        thousand(i -> {
            String period = chance.time.period(true);
            assertThat(period, anyOf(is("AM"), is("PM")));
            if ("AM".equals(period)) {
                am.incrementAndGet();
            }
        });
        assertThat(am.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    void testMinute() {
        times(240, i -> {
            int minute = chance.time.minute();
            assertThat(minute, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(59)));
        });
    }

    @Test
    void testSecond() {
        times(240, i -> {
            int second = chance.time.second();
            assertThat(second, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(59)));
        });
    }

    @Test
    void testMillisecond() {
        times(2000, i -> {
            int millisecond = chance.time.millisecond();
            assertThat(millisecond, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(999)));
        });
    }

    @Test
    void testHour() {
        times(120, i -> {
            int hour = chance.time.hour();
            assertThat(hour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
        });
    }

    @Test
    void testHour_TwentyFour() {
        times(240, i -> {
            int hour = chance.time.hour(true);
            assertThat(hour, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(23)));
        });
    }

    @Test
    void testMonth() {
        List<String> monthNames = Month.all.stream()
                .map(Month::getFullName)
                .collect(Collectors.toList());
        times(100, i -> {
            String month = chance.time.month();
            assertThat(monthNames, hasItem(month));
        });
    }

    @Test
    void testMonth_Abbreviation() {
        List<String> monthAbbreviations = Month.all.stream()
                .map(Month::getAbbreviation)
                .collect(Collectors.toList());
        times(100, i -> {
            String abbreviation = chance.time.month(true);
            assertThat(monthAbbreviations, hasItem(abbreviation));
        });
    }

    @Test
    void testMonth_Numeric() {
        times(100, i -> {
            int month = chance.time.monthNumeric();
            assertThat(month, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
        });
    }

    @Test
    void testYear() {
        thousand(i -> {
            int currentYear = LocalDate.now().getYear();
            int year = chance.time.year();
            assertThat(year, allOf(greaterThan(currentYear - 1), lessThan(currentYear + 101)));
        });
    }

    @Test
    void testTime() {
        times(250, i -> {
            String time = chance.time.time();
            assertThat(time.length(), is(11));
            assertThat(time, anyOf(endsWith("pm"), endsWith("am")));
        });
    }

    @Test
    void testTime_Milliseconds() {
        times(250, i -> {
            String time = chance.time.time(true);
            assertThat(time.length(), is(15));
            assertThat(time, anyOf(endsWith("pm"), endsWith("am")));
        });
    }
}
