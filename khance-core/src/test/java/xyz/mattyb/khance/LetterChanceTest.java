package xyz.mattyb.khance;

import org.junit.jupiter.api.Test;
import xyz.mattyb.khance.enums.Casing;
import xyz.mattyb.khance.testutils.BaseChanceTest;

import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static xyz.mattyb.khance.test.utils.CharacterMatchesPattern.charMatchesPattern;
import static xyz.mattyb.khance.test.utils.MatchesPattern.matchesPattern;
import static xyz.mattyb.khance.test.utils.TestUtils.thousand;

public class LetterChanceTest extends BaseChanceTest {

    @Test
    public void testLetter() {
        thousand(i -> {
            final String letter = chance.letter();
            assertThat(letter.length(), is(1));
            assertThat(letter, matchesPattern("[a-z]"));
        });
    }

    @Test
    public void testChar() {
        thousand(i -> {
            final char character = chance.character();
            assertThat(character, charMatchesPattern("[a-z]"));
        });
    }

    @Test
    public void testLetter_UpperCasing() {
        thousand(i -> {
            final String letter = chance.letter(Casing.UPPER);
            assertThat(letter.length(), is(1));
            assertThat(letter, matchesPattern("[A-Z]"));
        });
    }

    @Test
    public void testChar_UpperCasing() {
        thousand(i -> {
            final char character = chance.character(Casing.UPPER);
            assertThat(character, charMatchesPattern("[A-Z]"));
        });
    }

    @Test
    public void testLetter_MixedCasing() {
        thousand(i -> {
            final String letter = chance.letter(Casing.MIXED);
            assertThat(letter.length(), is(1));
            assertThat(letter, matchesPattern("[a-zA-Z]"));
        });
    }

    @Test
    public void testChar_MixedCasing() {
        thousand(i -> {
            final char character = chance.character(Casing.MIXED);
            assertThat(character, charMatchesPattern("[a-zA-Z]"));
        });
    }

    @Test
    public void testChar_Pool() {
        thousand(i -> {
            final char character = chance.character(Casing.LOWER, "abcdef");
            assertThat(character, charMatchesPattern("[abcdef]"));
        });
    }

    @Test
    public void testChar_PoolNumbers() {
        thousand(i -> {
            final char character = chance.character(Casing.LOWER, "0123456789");
            assertThat(character, charMatchesPattern("[0-9]"));
        });
    }

    @Test
    public void testHex() {
        thousand(i -> {
            final char character = chance.hex();
            assertThat(character, charMatchesPattern("[0-9a-f]"));
        });
    }

    @Test
    public void testHex_Upper() {
        thousand(i -> {
            final char character = chance.hex(Casing.UPPER);
            assertThat(character, charMatchesPattern("[0-9A-F]"));
        });
    }

    @Test
    public void testHash() {
        Pattern hashPattern = Pattern.compile("^[0-9a-f]{40}$");
        thousand(i -> {
            String hash = chance.hash();
            assertThat(hash, matchesPattern(hashPattern));
        });
    }

    @Test
    public void testHash_Length() {
        Pattern hashPattern = Pattern.compile("^[0-9a-f]{15}$");
        thousand(i -> {
            String hash = chance.hash(15);
            assertThat(hash, matchesPattern(hashPattern));
        });
    }

    @Test
    public void testHash_Casing() {
        Pattern upperPattern = Pattern.compile("^[0-9A-F]{40}$");
        Pattern mixedPattern = Pattern.compile("^[0-9A-Fa-f]{40}$");
        thousand(i -> {
            String upper = chance.hash(40, Casing.UPPER);
            assertThat(upper, matchesPattern(upperPattern));
            String mixed = chance.hash(40, Casing.MIXED);
            assertThat(mixed, matchesPattern(mixedPattern));
        });
    }
}
