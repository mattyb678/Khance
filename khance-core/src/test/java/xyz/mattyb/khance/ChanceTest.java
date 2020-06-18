package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ChanceTest extends BaseChanceTest {

    @Test
    void testRandom_Empty() {
        assertThrows(NoSuchElementException.class, () -> chance.random(Collections.emptyList()));
    }
}
