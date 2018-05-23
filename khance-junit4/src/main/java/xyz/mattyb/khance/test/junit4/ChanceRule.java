package xyz.mattyb.khance.test.junit4;

import org.junit.rules.TestWatcher;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.ChanceFactory;

public class ChanceRule extends TestWatcher {
    private Chance chance;

    public ChanceRule() {
        chance = ChanceFactory.chance();
    }

    public ChanceRule(long seed) {
        chance = ChanceFactory.chance(seed);
    }

    public Chance getChance() {
        return chance;
    }
}
