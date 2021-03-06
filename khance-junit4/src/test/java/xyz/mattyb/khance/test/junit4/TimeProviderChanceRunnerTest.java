package xyz.mattyb.khance.test.junit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.test.core.annotations.HourProvider;
import xyz.mattyb.khance.test.core.annotations.MonthProvider;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.times;

@RunWith(ChanceRunner.class)
public class TimeProviderChanceRunnerTest {

    @MonthProvider
    private Supplier<String> monthSupplier;

    @MonthProvider
    private Supplier<Integer> monthNumericSupplier;

    @MonthProvider
    private String month;

    @MonthProvider
    private int monthNumeric;

    @MonthProvider(true)
    private Supplier<String> monthAbbreviationSupplier;

    @MonthProvider(true)
    private String monthAbbreviation;

    @HourProvider
    private int twelveHour;

    @HourProvider
    private Supplier<Integer> twelveHourSupplier;

    @HourProvider
    private Integer twentyFourHour;

    @HourProvider
    private Supplier<Integer> twentyFourHourSupplier;

    @Test
    public void testTwelveHour() {
        assertThat(twelveHour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
        times(48, i -> {
            int twelveHour = twelveHourSupplier.get();
            assertThat(twelveHour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
        });
    }

    @Test
    public void testTwentyFourHour() {
        assertThat(twentyFourHour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(24)));
        times(48, i -> {
            Integer twentyFourHour = twentyFourHourSupplier.get();
            assertThat(twentyFourHour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(24)));
        });
    }

    @Test
    public void testMonth() {
        List<String> months = Month.all.stream()
                .map(Month::getFullName)
                .collect(Collectors.toList());
        assertThat(months, hasItem(month));
        times(120, i -> {
            String month = monthSupplier.get();
            assertThat(months, hasItem(month));
        });
    }

    @Test
    public void testMonth_Abbreviation() {
        List<String> abbreviations = Month.all.stream()
                .map(Month::getAbbreviation)
                .collect(Collectors.toList());
        assertThat(abbreviations, hasItem(monthAbbreviation));
        times(120, i -> {
            String monthAbbreviation = monthAbbreviationSupplier.get();
            assertThat(abbreviations, hasItem(monthAbbreviation));
        });
    }

    @Test
    public void testMonth_Numeric() {
        List<Integer> numerics = Month.all.stream()
                .map(Month::getNumeric)
                .collect(Collectors.toList());
        assertThat(numerics, hasItem(monthNumeric));
        times(120, i -> {
            int monthNumeric = monthNumericSupplier.get();
            assertThat(numerics, hasItem(monthNumeric));
        });
    }
}
