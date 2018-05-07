package xyz.mattyb.khance.testutils;

import org.junit.jupiter.api.BeforeEach;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.ChanceFactory;

public abstract class BaseChanceTest {
    protected Chance chance;

    @BeforeEach
    public void setUp() {
        chance = ChanceFactory.chance();
    }
}
