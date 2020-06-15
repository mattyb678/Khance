package xyz.mattyb.khance.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import xyz.mattyb.khance.CityNames;
import xyz.mattyb.khance.enums.Continent;
import xyz.mattyb.khance.enums.State;
import xyz.mattyb.khance.test.core.annotations.CityProvider;
import xyz.mattyb.khance.test.core.annotations.ZipProvider;
import xyz.mattyb.khance.test.junit5.ChanceProviderExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;

@ExtendWith(ChanceProviderExtension.class)
class TestLocationProviders {

    private static final List<String> ALL_CITIES = new ArrayList<>();

    private static final List<String> AMERICAN_CITIES = new ArrayList<>();

    static {
        ALL_CITIES.addAll(CityNames.getAfricanCityNames());
        ALL_CITIES.addAll(CityNames.getAsianCityNames());
        ALL_CITIES.addAll(CityNames.getAustralianCityNames());
        ALL_CITIES.addAll(CityNames.getEuropeanCityNames());
        ALL_CITIES.addAll(CityNames.getNorthAmericanCityNames());
        ALL_CITIES.addAll(CityNames.getSouthAmericanCityNames());

        AMERICAN_CITIES.addAll(CityNames.getNorthAmericanCityNames());
        AMERICAN_CITIES.addAll(CityNames.getSouthAmericanCityNames());
    }

    @RepeatedTest(50)
    void testCity(@CityProvider String city) {
        assertThat(city, is(notNullValue()));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testCity_African(@CityProvider(Continent.AFRICA) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(CityNames.getAfricanCityNames(), hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testCity_Asian(@CityProvider(Continent.ASIA) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(CityNames.getAsianCityNames(), hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testCity_Australia(@CityProvider(Continent.AUSTRALIA) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(CityNames.getAustralianCityNames(), hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testCity_Europe(@CityProvider(Continent.EUROPE) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(CityNames.getEuropeanCityNames(), hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testCity_NorthAmerica(@CityProvider(Continent.NORTH_AMERICA) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(CityNames.getNorthAmericanCityNames(), hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testCity_SouthAmerica(@CityProvider(Continent.SOUTH_AMERICA) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(CityNames.getSouthAmericanCityNames(), hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(25)
    void testCity_Multiple(@CityProvider({Continent.NORTH_AMERICA, Continent.SOUTH_AMERICA}) String city) {
        assertThat(city, is(notNullValue()));
        assertThat(AMERICAN_CITIES, hasItem(city));
        assertThat(ALL_CITIES, hasItem(city));
    }

    @RepeatedTest(10)
    void testZip(@ZipProvider String zip) {
        assertThat(zip, matchesPattern("\\d{5}"));
    }

    @RepeatedTest(10)
    void testZip_Colorado(@ZipProvider(State.CO) String zip) {
        assertThat(zip, matchesPattern("802\\d{2}"));
    }
}
