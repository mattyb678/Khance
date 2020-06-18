package xyz.mattyb.khance;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Continent;
import xyz.mattyb.khance.enums.CountryCode;
import xyz.mattyb.khance.enums.State;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;
import static xyz.mattyb.khance.test.utils.TestUtils.times;

class LocationTest extends BaseChanceTest {

    private static final Pattern STATE_ABBREV_PATTERN = Pattern.compile("[A-Z]{2}");

    @Test
    void testCity() {
        List<String> cities = new ArrayList<>();
        cities.addAll(CityNames.getAfricanCityNames());
        cities.addAll(CityNames.getAsianCityNames());
        cities.addAll(CityNames.getAustralianCityNames());
        cities.addAll(CityNames.getEuropeanCityNames());
        cities.addAll(CityNames.getNorthAmericanCityNames());
        cities.addAll(CityNames.getSouthAmericanCityNames());
        thousand(i -> {
            String cityName = chance.location.city();
            assertThat(cities, hasItem(cityName));
        });
    }

    @Test
    void testCity_Africa() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.AFRICA);
            assertThat(CityNames.getAfricanCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_Asia() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.ASIA);
            assertThat(CityNames.getAsianCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_Australia() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.AUSTRALIA);
            assertThat(CityNames.getAustralianCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_NorthAmerica() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.NORTH_AMERICA);
            assertThat(CityNames.getNorthAmericanCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_SouthAmerica() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.SOUTH_AMERICA);
            assertThat(CityNames.getSouthAmericanCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCountry() {
        List<String> countries = CountryCode.getAll()
                .stream()
                .map(CountryCode::getFullName)
                .collect(Collectors.toList());
        thousand(i -> {
            String country = chance.location.country();
            assertThat(countries, hasItem(country));
        });
    }

    @Test
    void testCountry_Seeded() {
        Chance seeded1 = ChanceFactory.chance(1986L);
        Chance seeded2 = ChanceFactory.chance(1986L);
        times(25, i -> {
            String firstCountry = seeded1.location.country();
            String secondCountry = seeded2.location.country();
            assertThat(firstCountry, is(secondCountry));
        });
    }

    @Test
    void testCountry_CodeSeeded() {
        Chance seeded1 = ChanceFactory.chance(1776L);
        Chance seeded2 = ChanceFactory.chance(1776L);
        times(25, i -> {
            String firstCountry = seeded1.location.country(true);
            String secondCountry = seeded2.location.country(true);
            assertThat(firstCountry, is(secondCountry));
        });
    }

    @Test
    void testCountry_Code() {
        List<String> countries = CountryCode.getAll()
                .stream()
                .map(CountryCode::toString)
                .collect(Collectors.toList());
        thousand(i -> {
            String countryCode = chance.location.country(true);
            assertThat(countries, hasItem(countryCode));
        });
    }

    @Test
    void testZip() {
        Pattern utahPattern = Pattern.compile("84\\d{3}");
        Pattern zipPattern = Pattern.compile("\\d{5}");
        thousand(i -> {
            String utahZip = chance.location.zip(State.UT);
            assertThat(utahZip, matchesPattern(utahPattern));
            String allZip = chance.location.zip();
            assertThat(allZip, matchesPattern(zipPattern));
        });
    }

    @RepeatedTest(100)
    void testState() {
        assertThat(chance.location.state(), not(matchesPattern(STATE_ABBREV_PATTERN)));
    }

    @RepeatedTest(100)
    void testState_Abbreviation() {
        assertThat(chance.location.state(true), matchesPattern(STATE_ABBREV_PATTERN));
    }
}
