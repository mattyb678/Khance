package xyz.mattyb.khance.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.test.core.annotations.HourProvider;
import xyz.mattyb.khance.test.core.annotations.MonthProvider;
import xyz.mattyb.khance.test.junit5.ChanceProviderExtension;

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

    @RepeatedTest(120)
    void testHour(@HourProvider int hour) {
        assertThat(hour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
    }

    @RepeatedTest(240)
    void testHour_TwentyFour(@HourProvider(true) Integer hour) {
        assertThat(hour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(24)));
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
}
