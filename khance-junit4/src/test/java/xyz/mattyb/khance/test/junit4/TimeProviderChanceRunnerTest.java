package xyz.mattyb.khance.test.junit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.test.core.annotations.MonthProvider;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

@RunWith(ChanceRunner.class)
public class TimeProviderChanceRunnerTest {

    @MonthProvider
    private String month;

    @MonthProvider(true)
    private String monthAbbreviation;

    @Test
    public void testMonth() {
        List<String> months = Month.all.stream()
                .map(Month::getFullName)
                .collect(Collectors.toList());
        System.out.println("TimeProviderChanceRunner.testMonth: " + month);
        assertThat(months, hasItem(month));
    }

    @Test
    public void testMonth_Abbreviation() {
        List<String> abbreviations = Month.all.stream()
                .map(Month::getAbbreviation)
                .collect(Collectors.toList());
        assertThat(abbreviations, hasItem(monthAbbreviation));
    }
}
