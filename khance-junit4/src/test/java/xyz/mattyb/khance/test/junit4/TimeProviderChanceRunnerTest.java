package xyz.mattyb.khance.test.junit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.test.core.annotations.MonthProvider;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static xyz.mattyb.khance.test.utils.TestUtils.times;

@RunWith(ChanceRunner.class)
public class TimeProviderChanceRunnerTest {

    @MonthProvider
    private Supplier<String> monthSupplier;

    @MonthProvider
    private String month;

    @MonthProvider(true)
    private Supplier<String> monthAbbreviationSupplier;

    @MonthProvider(true)
    private String monthAbbreviation;

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
}
