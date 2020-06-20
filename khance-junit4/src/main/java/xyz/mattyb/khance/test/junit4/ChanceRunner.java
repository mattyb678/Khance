package xyz.mattyb.khance.test.junit4;

import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
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

public class ChanceRunner extends BlockJUnit4ClassRunner implements Filterable {

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
                            field.set(target, TimerProviderUtils.getMonth(provider, chance));
                        }
                    }
                }
                base.evaluate();
            }
        };
    }

    @Override
    public void filter(Filter filter) throws NoTestsRemainException {
        super.filter(filter);
    }
}
