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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

class LocationTest extends BaseChanceTest {

    private static final Pattern STATE_ABBREV_PATTERN = Pattern.compile("[A-Z]{2}");

    @Test
    void testCity() {
        List<String> cities = new ArrayList<>();
        cities.addAll(CityNamesKt.getAfricanCityNames());
        cities.addAll(CityNamesKt.getAsianCityNames());
        cities.addAll(CityNamesKt.getAustralianCityNames());
        cities.addAll(CityNamesKt.getEuropeanCityNames());
        cities.addAll(CityNamesKt.getNorthAmericanCityNames());
        cities.addAll(CityNamesKt.getSouthAmericanCityNames());
        thousand(i -> {
            String cityName = chance.location.city();
            assertThat(cities, hasItem(cityName));
        });
    }

    @Test
    void testCity_Africa() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.AFRICA);
            assertThat(CityNamesKt.getAfricanCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_Asia() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.ASIA);
            assertThat(CityNamesKt.getAsianCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_Australia() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.AUSTRALIA);
            assertThat(CityNamesKt.getAustralianCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_NorthAmerica() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.NORTH_AMERICA);
            assertThat(CityNamesKt.getNorthAmericanCityNames(), hasItem(cityName));
        });
    }

    @Test
    void testCity_SouthAmerica() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.SOUTH_AMERICA);
            assertThat(CityNamesKt.getSouthAmericanCityNames(), hasItem(cityName));
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
