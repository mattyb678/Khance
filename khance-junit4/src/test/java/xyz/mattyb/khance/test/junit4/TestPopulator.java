package xyz.mattyb.khance.test.junit4;

import xyz.mattyb.khance.test.core.ChancePopulator;
import xyz.mattyb.khance.test.core.annotations.Field;
import xyz.mattyb.khance.test.core.annotations.IntegerProvider;
import xyz.mattyb.khance.test.core.annotations.PopulatedClass;
import xyz.mattyb.khance.test.core.annotations.StringProvider;

@StringProvider(length = 15, field = @Field("yo"))
@IntegerProvider(min = 0, max = 100, field = @Field("age"))
@PopulatedClass(TestPopulatedClass.class)
public class TestPopulator implements ChancePopulator {
}
