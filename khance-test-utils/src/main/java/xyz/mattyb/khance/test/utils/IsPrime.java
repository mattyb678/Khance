package xyz.mattyb.khance.test.utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsPrime extends TypeSafeMatcher<Integer> {

    @Override
    protected boolean matchesSafely(Integer integer) {
        if (integer == null) {
            return false;
        }
        for(int i = 2; i <= integer/2; ++i) {
            if(integer % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a prime number");
    }

    public static Matcher<Integer> prime() {
        return new IsPrime();
    }
}
