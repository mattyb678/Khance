package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Continent;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

class LocationTest extends BaseChanceTest {

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

}
