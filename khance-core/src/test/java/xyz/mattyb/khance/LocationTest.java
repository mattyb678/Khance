package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Continent;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class LocationTest extends BaseChanceTest {

    @Test
    public void testCity_Africa() {
        thousand(i -> {
            String cityName = chance.location.city(Continent.AFRICA);
            assertThat(CityNamesKt.getAfricanCityNames(), hasItem(cityName));
        });
    }

}
