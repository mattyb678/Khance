package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Continent;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

class LocationTest extends BaseChanceTest {

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
}
