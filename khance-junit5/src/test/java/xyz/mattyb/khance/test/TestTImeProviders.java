package xyz.mattyb.khance.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import xyz.mattyb.khance.test.core.annotations.HourProvider;
import xyz.mattyb.khance.test.junit5.ChanceProviderExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(ChanceProviderExtension.class)
class TestTImeProviders {

    @RepeatedTest(120)
    void testHour(@HourProvider int hour) {
        assertThat(hour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
    }

    @RepeatedTest(240)
    void testHour_TwentyFour(@HourProvider(true) Integer hour) {
        assertThat(hour, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(24)));
    }
}
