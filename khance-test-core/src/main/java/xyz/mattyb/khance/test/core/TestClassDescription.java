package xyz.mattyb.khance.test.core;

import xyz.mattyb.khance.test.core.annotations.Bool;
import xyz.mattyb.khance.test.core.annotations.Integer;

@Bool(value = "some_field",likelihood = 75)
@Integer(value = "other_field", min = -10, max = 107)

public class TestClassDescription {
}
