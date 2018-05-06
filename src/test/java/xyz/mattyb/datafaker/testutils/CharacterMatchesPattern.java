package xyz.mattyb.datafaker.testutils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

public class CharacterMatchesPattern extends TypeSafeMatcher<Character> {
    private final Pattern pattern;

    public CharacterMatchesPattern(final Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    protected boolean matchesSafely(Character character) {
        return pattern.matcher(Character.toString(character)).matches();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a character matching the pattern '" + pattern + "'");
    }

    public static Matcher<Character> charMatchesPattern(String regex) {
        return new CharacterMatchesPattern(Pattern.compile(regex));
    }
}
