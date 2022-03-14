package xyz.mattyb.khance.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.test.core.annotations.*;
import xyz.mattyb.khance.test.junit5.ChanceProviderExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(ChanceProviderExtension.class)
class TestTImeProviders {

    private static final List<String> monthsFull = Month.all.stream()
            .map(Month::getFullName)
            .collect(Collectors.toList());

    private static final List<String> monthsAbbr = Month.all.stream()
            .map(Month::getAbbreviation)
            .collect(Collectors.toList());

    @RepeatedTest(2000)
    void testMillisecond(@MillisecondProvider int millisecond) {
        assertThat(millisecond, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(999)));
    }

    @RepeatedTest(240)
    void testSecond(@SecondProvider int second) {
        assertThat(second, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(59)));
    }

    @RepeatedTest(240)
    void testMinute(@MinuteProvider int minute) {
        assertThat(minute, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(59)));
    }

    @RepeatedTest(120)
    void testHour(@HourProvider int hour) {
        assertThat(hour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
    }

    @RepeatedTest(240)
    void testHour_TwentyFour(@HourProvider(true) Integer hour) {
        assertThat(hour, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(23)));
    }

    @RepeatedTest(120)
    void testMonth_Numeric(@MonthProvider int month) {
        assertThat(month, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
    }

    @RepeatedTest(120)
    void testMonth_Full(@MonthProvider String month) {
        assertThat(monthsFull, hasItem(month));
    }

    @RepeatedTest(120)
    void testMonth_Abbreviation(@MonthProvider(true) String month) {
        assertThat(monthsAbbr, hasItem(month));
    }

    @RepeatedTest(250)
    void testYear(@YearProvider int year) {
        int currentYear = LocalDate.now().getYear();
        assertThat(year, allOf(greaterThan(currentYear - 1), lessThan(currentYear + 101)));
    }

    @RepeatedTest(50)
    void testYear_Min(@YearProvider(min = 2090) int year) {
        int currentYear = LocalDate.now().getYear();
        assertThat(year, allOf(greaterThan(2089), lessThan(currentYear + 101)));
    }

    @RepeatedTest(50)
    void testYear_Range(@YearProvider(min = 1990, max = 2000) int year) {
        assertThat(year, allOf(greaterThan(1989), lessThan(2001)));
    }
}
