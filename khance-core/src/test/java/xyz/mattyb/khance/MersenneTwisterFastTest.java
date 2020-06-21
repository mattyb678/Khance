package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MersenneTwisterFastTest {

    @Test
    void testProbabilityException() {
        MersenneTwisterFast mersenneTwisterFast = new MersenneTwisterFast(100L);
        assertThrows(IllegalArgumentException.class, () -> mersenneTwisterFast.nextBoolean(-0.5));
        assertThrows(IllegalArgumentException.class, () -> mersenneTwisterFast.nextBoolean(50));
    }

}