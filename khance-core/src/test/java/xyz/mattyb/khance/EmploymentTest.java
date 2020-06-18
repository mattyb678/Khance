package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class EmploymentTest extends BaseChanceTest {

    @Test
    void testCompanies() {
        thousand(i -> assertThat(CompaniesKt.getCompanies(), hasItem(chance.employment.company())));
    }

    @Test
    void testCompany_Seeded() {
        Chance seeded1 = ChanceFactory.chance(1492L);
        Chance seeded2 = ChanceFactory.chance(1492L);
        thousand(i -> {
            String company1 = seeded1.employment.company();
            String company2 = seeded2.employment.company();
            assertThat(company1, is(company2));
        });
    }

    @Test
    void testProfession() {
        thousand(i -> assertThat(ProfessionsKt.getProfessions(), hasItem(chance.employment.profession())));
    }

    @Test
    void testProfession_Seeded() {
        Chance seeded1 = ChanceFactory.chance(2020L);
        Chance seeded2 = ChanceFactory.chance(2020L);
        thousand(i -> {
            String profession1 = seeded1.employment.profession();
            String profession2 = seeded2.employment.profession();
            assertThat(profession1, is(profession2));

            String withRank1 = seeded1.employment.profession(true);
            String withRank2 = seeded2.employment.profession(true);
            assertThat(withRank1, is(withRank2));
        });
    }
}
