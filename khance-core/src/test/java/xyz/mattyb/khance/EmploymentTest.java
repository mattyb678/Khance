package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class EmploymentTest extends BaseChanceTest {

    @Test
    public void testCompanies() {
        thousand(i -> assertThat(CompaniesKt.getCompanies(), hasItem(chance.employment.company())));
    }

    @Test
    public void testProfession() {
        thousand(i -> assertThat(ProfessionsKt.getProfessions(), hasItem(chance.employment.profession())));
    }
}
