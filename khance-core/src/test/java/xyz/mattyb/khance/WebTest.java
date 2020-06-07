package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.TldType;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class WebTest extends BaseChanceTest {

    @Test
    public void testIp() {
        thousand(i -> {
            String ip = chance.web.ip();
            String[] parts = ip.split("\\.");
            assertThat(parts, arrayWithSize(4));
            int first = Integer.parseInt(parts[0]);
            assertThat(first, allOf(greaterThan(0), lessThan(255)));
            int second = Integer.parseInt(parts[1]);
            assertThat(second, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
            int third = Integer.parseInt(parts[2]);
            assertThat(third, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
            int fourth = Integer.parseInt(parts[3]);
            assertThat(fourth, allOf(greaterThan(0), lessThan(255)));
        });
    }

    @Test
    public void testTld_Original() {
        thousand(i -> assertThat(TldsKt.getOriginalTlds(), hasItem(chance.web.tld())));
    }

    @Test
    public void testTld_Country() {
        thousand(i -> assertThat(TldsKt.getCountryTlds(), hasItem(chance.web.tld(TldType.COUNTRY))));
    }

    @Test
    public void testTld_Generic() {
        thousand(i -> assertThat(TldsKt.getGenericTlds(), hasItem(chance.web.tld(TldType.GENERIC))));
    }
}
