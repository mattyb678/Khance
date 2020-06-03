package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.AgeType;
import xyz.mattyb.khance.enums.Gender;
import xyz.mattyb.khance.enums.Nationality;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class PersonChanceTest extends BaseChanceTest {

    @Test
    public void testAge() {
        thousand(i -> assertThat(chance.person.age(), is(allOf(greaterThanOrEqualTo(0), lessThan(101)))));
    }

    @Test
    public void testAge_Baby() {
        thousand(i -> assertThat(chance.person.age(AgeType.BABY), is(allOf(greaterThanOrEqualTo(0), lessThan(3)))));
    }

    @Test
    public void testAge_Child() {
        thousand(i -> assertThat(chance.person.age(AgeType.CHILD), is(allOf(greaterThanOrEqualTo(0), lessThan(13)))));
    }

    @Test
    public void testAge_Teen() {
        thousand(i -> assertThat(chance.person.age(AgeType.TEEN), is(allOf(greaterThan(12), lessThan(20)))));
    }

    @Test
    public void testAge_Adult() {
        thousand(i -> assertThat(chance.person.age(AgeType.ADULT), is(allOf(greaterThan(17), lessThan(66)))));
    }

    @Test
    public void testAge_Senior() {
        thousand(i -> assertThat(chance.person.age(AgeType.SENIOR), is(allOf(greaterThan(64), lessThan(101)))));
    }

    @Test
    public void testAge_All() {
        thousand(i -> assertThat(chance.person.age(AgeType.ALL), is(allOf(greaterThanOrEqualTo(0), lessThan(101)))));
    }

    @Test
    public void testGender() {
        final AtomicInteger femaleCount = new AtomicInteger(0);
        thousand(i -> {
            if (chance.person.gender().equalsIgnoreCase("female")) {
                femaleCount.incrementAndGet();
            }
        });
        assertThat(femaleCount.get(), is(allOf(greaterThan(200), lessThan(800))));
    }

    @Test
    public void testGender_Extra() {
        final AtomicInteger femaleCount = new AtomicInteger(0);
        thousand(i -> {
            String gender = chance.person.gender("Genderqueer", "Trans", "Pangender", "Agender");
            if (gender.equalsIgnoreCase("female")) {
                femaleCount.incrementAndGet();
            }
        });
        assertThat(femaleCount.get(), is(allOf(greaterThan(100), lessThan(500))));
    }

    @Test
    public void testLastName() {
        thousand(i -> {
            String lastName = chance.person.last();
            assertThat(lastName, not(isEmptyString()));
        });
    }

    @Test
    public void testLastName_Specific() {
        thousand(i -> {
            String usaName = chance.person.last(Nationality.USA);
            assertThat(usaName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.USA_LAST_NAMES, hasItem(usaName));

            String italyName = chance.person.last(Nationality.ITALY);
            assertThat(italyName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.ITALY_LAST_NAMES, hasItem(italyName));

            String netherlandsName = chance.person.last(Nationality.NETHERLANDS);
            assertThat(netherlandsName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.NETHERLANDS_LAST_NAMES, hasItem(netherlandsName));

            String ukName = chance.person.last(Nationality.UK);
            assertThat(ukName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.UK_LAST_NAMES, hasItem(ukName));

            String germanName = chance.person.last(Nationality.GERMANY);
            assertThat(germanName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.GERMANY_LAST_NAMES, hasItem(germanName));

            String japanName = chance.person.last(Nationality.JAPAN);
            assertThat(japanName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.JAPAN_LAST_NAMES, hasItem(japanName));

            String spainName = chance.person.last(Nationality.SPAIN);
            assertThat(spainName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.SPAIN_LAST_NAMES, hasItem(spainName));

            String franceName = chance.person.last(Nationality.FRANCE);
            assertThat(franceName, not(isEmptyOrNullString()));
            assertThat(LastNamesKt.FRANCE_LAST_NAMES, hasItem(franceName));
        });
    }

    @Test
    public void testFirstName() {
        List<String> combined = new ArrayList<>();
        combined.addAll(FirstNamesKt.ENG_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.ENG_FEMALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.ITALY_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.ITALY_FEMALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.NETHERLANDS_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.NETHERLANDS_FEMALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.FRANCE_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.FRANCE_FEMALE_FIRST_NAMES);
        thousand(i -> {
            String name = chance.person.first();
            assertThat(name, not(isEmptyOrNullString()));
            assertThat(combined, hasItem(name));
        });
    }

    @Test
    public void testFirstName_Female() {
        List<String> combined = new ArrayList<>();
        combined.addAll(FirstNamesKt.ENG_FEMALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.ITALY_FEMALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.NETHERLANDS_FEMALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.FRANCE_FEMALE_FIRST_NAMES);
        thousand(i -> {
            String name = chance.person.first(Gender.FEMALE);
            assertThat(name, not(isEmptyOrNullString()));
            assertThat(combined, hasItem(name));
        });
    }

    @Test
    public void testFirstName_Male() {
        List<String> combined = new ArrayList<>();
        combined.addAll(FirstNamesKt.ENG_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.ITALY_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.NETHERLANDS_MALE_FIRST_NAMES);
        combined.addAll(FirstNamesKt.FRANCE_MALE_FIRST_NAMES);
        thousand(i -> {
            String name = chance.person.first(Gender.MALE);
            assertThat(name, not(isEmptyOrNullString()));
            assertThat(combined, hasItem(name));
        });
    }
}
