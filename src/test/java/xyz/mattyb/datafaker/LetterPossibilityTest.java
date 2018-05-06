package xyz.mattyb.datafaker;

import org.junit.jupiter.api.Test;
import xyz.mattyb.datafaker.enums.Casing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static xyz.mattyb.datafaker.testutils.CharacterMatchesPattern.charMatchesPattern;
import static xyz.mattyb.datafaker.testutils.MatchesPattern.matchesPattern;
import static xyz.mattyb.datafaker.testutils.TestUtils.thousand;

public class LetterPossibilityTest {

    @Test
    public void testLetter() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final String letter = possibility.letter();
            assertThat(letter.length(), is(1));
            assertThat(letter, matchesPattern("[a-z]"));
        });
    }

    @Test
    public void testChar() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.character();
            assertThat(character, charMatchesPattern("[a-z]"));
        });
    }

    @Test
    public void testLetter_UpperCasing() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final String letter = possibility.letter(Casing.UPPER);
            assertThat(letter.length(), is(1));
            assertThat(letter, matchesPattern("[A-Z]"));
        });
    }

    @Test
    public void testChar_UpperCasing() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.character(Casing.UPPER);
            assertThat(character, charMatchesPattern("[A-Z]"));
        });
    }

    @Test
    public void testLetter_MixedCasing() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final String letter = possibility.letter(Casing.MIXED);
            assertThat(letter.length(), is(1));
            assertThat(letter, matchesPattern("[a-zA-Z]"));
        });
    }

    @Test
    public void testChar_MixedCasing() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.character(Casing.MIXED);
            assertThat(character, charMatchesPattern("[a-zA-Z]"));
        });
    }

    @Test
    public void testChar_Pool() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.character(Casing.LOWER, "abcdef");
            assertThat(character, charMatchesPattern("[abcdef]"));
        });
    }

    @Test
    public void testChar_PoolNumbers() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.character(Casing.LOWER, "0123456789");
            assertThat(character, charMatchesPattern("[0-9]"));
        });
    }

    @Test
    public void testHex() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.hex();
            assertThat(character, charMatchesPattern("[0-9a-f]"));
        });
    }

    @Test
    public void testHex_Upper() {
        final Possibility possibility = FakerFactory.possibility();

        thousand(i -> {
            final char character = possibility.hex(Casing.UPPER);
            assertThat(character, charMatchesPattern("[0-9A-F]"));
        });
    }
}
