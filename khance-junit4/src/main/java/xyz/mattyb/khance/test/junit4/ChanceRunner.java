package xyz.mattyb.khance.test.junit4;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.ChanceFactory;
import xyz.mattyb.khance.test.core.TimerProviderUtils;
import xyz.mattyb.khance.test.core.annotations.MonthProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.function.Supplier;

public class ChanceRunner extends BlockJUnit4ClassRunner {

    private final Chance chance;

    public ChanceRunner(Class<?> klass) throws InitializationError {
        super(klass);
        chance = ChanceFactory.chance();
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        final Statement base = super.withBefores(method, target, statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                for (Field field : method.getDeclaringClass().getDeclaredFields()) {
                    for (Annotation annotation : field.getAnnotations()) {
                        if (annotation instanceof MonthProvider) {
                            MonthProvider provider = (MonthProvider) annotation;
                            field.setAccessible(true);
                            Supplier<String> stringSupplier =
                                    () -> TimerProviderUtils.getMonth(provider, chance);
                            Supplier<Integer> integerSupplier =
                                    () -> TimerProviderUtils.getMonthNumeric(provider, chance);
                            if (field.getType() == Supplier.class) {
                                ParameterizedType pType = ((ParameterizedType) field.getGenericType());
                                Type argType = pType.getActualTypeArguments()[0];
                                if (argType == String.class) {
                                    field.set(target, stringSupplier);
                                } else if (argType == Integer.class) {
                                    field.set(target, integerSupplier);
                                }
                            } else if (field.getType() == String.class) {
                                field.set(target, stringSupplier.get());
                            } else if (field.getType() == int.class || field.getType() == Integer.class) {
                                field.set(target, integerSupplier.get());
                            }
                        }
                    }
                }
                base.evaluate();
            }
        };
    }
}
