package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Currency;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

class FinanceTest extends BaseChanceTest {

    @Test
    void testCurrency() {
        List<String> currencies = Currency.getAll()
                .stream()
                .map(Currency::getCurrencyName)
                .collect(Collectors.toList());
        thousand(i -> {
            String currency = chance.finance.currency();
            assertThat(currencies, hasItem(currency));
        });
    }

    @Test
    void testCurrency_Code() {
        List<String> currencyCodes = Currency.getAll()
                .stream()
                .map(Currency::toString)
                .collect(Collectors.toList());
        thousand(i -> {
            String currencyCode = chance.finance.currency(true);
            assertThat(currencyCodes, hasItem(currencyCode));
        });
    }
}
