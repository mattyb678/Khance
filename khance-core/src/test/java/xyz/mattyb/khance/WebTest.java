package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.TldType;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class WebTest extends BaseChanceTest {

    private final Chance seeded1 = ChanceFactory.chance(1337L);

    private final Chance seeded2 = ChanceFactory.chance(1337L);

    @Test
    void testIp() {
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
    void testIpv6() {
        Pattern ipv6Pattern = Pattern.compile("([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}");
        thousand(i -> assertThat(chance.web.ipv6(), matchesPattern(ipv6Pattern)));
    }

    @Test
    void testTld_Original() {
        thousand(i -> {
            assertThat(TldsKt.getOriginalTlds(), hasItem(chance.web.tld()));
            String tld1 = seeded1.web.tld();
            String tld2 = seeded2.web.tld();
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_Brand() {
        thousand(i -> {
            assertThat(TldsKt.getBrandTlds(), hasItem(chance.web.tld(TldType.BRAND)));
            String tld1 = seeded1.web.tld(TldType.BRAND);
            String tld2 = seeded2.web.tld(TldType.BRAND);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_Country() {
        thousand(i -> {
            assertThat(TldsKt.getCountryTlds(), hasItem(chance.web.tld(TldType.COUNTRY)));
            String tld1 = seeded1.web.tld(TldType.COUNTRY);
            String tld2 = seeded2.web.tld(TldType.COUNTRY);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_Generic() {
        thousand(i -> {
            assertThat(TldsKt.getGenericTlds(), hasItem(chance.web.tld(TldType.GENERIC)));
            String tld1 = seeded1.web.tld(TldType.GENERIC);
            String tld2 = seeded2.web.tld(TldType.GENERIC);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericChinese() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_CHINESE);
            assertThat(TldsKt.getGenericChineseTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_CHINESE);
            String tld2 = seeded2.web.tld(TldType.GENERIC_CHINESE);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericFrench() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_FRENCH);
            assertThat(TldsKt.getGenericFrenchTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_FRENCH);
            String tld2 = seeded2.web.tld(TldType.GENERIC_FRENCH);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericGerman() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_GERMAN);
            assertThat(TldsKt.getGenericGermanTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_GERMAN);
            String tld2 = seeded2.web.tld(TldType.GENERIC_GERMAN);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericHindi() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_HINDI);
            assertThat(TldsKt.getGenericHindiTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_HINDI);
            String tld2 = seeded2.web.tld(TldType.GENERIC_HINDI);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericItalian() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_ITALIAN);
            assertThat(TldsKt.getGenericItalianTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_ITALIAN);
            String tld2 = seeded2.web.tld(TldType.GENERIC_ITALIAN);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericPortuguese() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_PORTUGUESE);
            assertThat(TldsKt.getGenericPortugueseTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_PORTUGUESE);
            String tld2 = seeded2.web.tld(TldType.GENERIC_PORTUGUESE);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GenericSpanish() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GENERIC_SPANISH);
            assertThat(TldsKt.getGenericSpanishTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GENERIC_SPANISH);
            String tld2 = seeded2.web.tld(TldType.GENERIC_SPANISH);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GeographicAfrica() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GEOGRAPHIC_AFRICA);
            assertThat(TldsKt.getGeoAfricaTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GEOGRAPHIC_AFRICA);
            String tld2 = seeded2.web.tld(TldType.GEOGRAPHIC_AFRICA);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GeographicAsia() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GEOGRAPHIC_ASIA);
            assertThat(TldsKt.getGeoAsiaTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GEOGRAPHIC_ASIA);
            String tld2 = seeded2.web.tld(TldType.GEOGRAPHIC_ASIA);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GeographicEurope() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GEOGRAPHIC_EUROPE);
            assertThat(TldsKt.getGeoEuropeTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GEOGRAPHIC_EUROPE);
            String tld2 = seeded2.web.tld(TldType.GEOGRAPHIC_EUROPE);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GeographicNorthAmerica() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GEOGRAPHIC_NORTH_AMERICA);
            assertThat(TldsKt.getGeoNorthAmericaTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GEOGRAPHIC_NORTH_AMERICA);
            String tld2 = seeded2.web.tld(TldType.GEOGRAPHIC_NORTH_AMERICA);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GeographicOceania() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GEOGRAPHIC_OCEANIA);
            assertThat(TldsKt.getGeoOceaniaTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GEOGRAPHIC_OCEANIA);
            String tld2 = seeded2.web.tld(TldType.GEOGRAPHIC_OCEANIA);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_GeographicSouthAmerica() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.GEOGRAPHIC_SOUTH_AMERICA);
            assertThat(TldsKt.getGeoSouthAmericaTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.GEOGRAPHIC_SOUTH_AMERICA);
            String tld2 = seeded2.web.tld(TldType.GEOGRAPHIC_SOUTH_AMERICA);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testTld_SpecialUse() {
        thousand(i -> {
            String tld = chance.web.tld(TldType.SPECIAL_USE);
            assertThat(TldsKt.getSpecialUseTlds(), hasItem(tld));
            String tld1 = seeded1.web.tld(TldType.SPECIAL_USE);
            String tld2 = seeded2.web.tld(TldType.SPECIAL_USE);
            assertThat(tld1, is(tld2));
        });
    }

    @Test
    void testDomain() {
        thousand(i -> System.out.println(chance.web.domain()));
    }
}
