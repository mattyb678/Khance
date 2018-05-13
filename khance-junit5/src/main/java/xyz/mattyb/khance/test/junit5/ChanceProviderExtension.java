package xyz.mattyb.khance.test.junit5;

import org.junit.jupiter.api.extension.*;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.ChanceFactory;
import xyz.mattyb.khance.test.core.annotations.BoolProvider;
import xyz.mattyb.khance.test.core.annotations.ChanceProvider;
import xyz.mattyb.khance.test.core.annotations.IntegerProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

import static org.junit.platform.commons.support.AnnotationSupport.isAnnotated;

public class ChanceProviderExtension implements ParameterResolver, TestInstancePostProcessor {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter param = parameterContext.getParameter();
        return isAnnotated(param, BoolProvider.class) || isAnnotated(param, IntegerProvider.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Chance chance = getChance(extensionContext);

        Parameter param = parameterContext.getParameter();
        if (isAnnotated(param, BoolProvider.class) && assignable(param, Boolean.class, boolean.class)) {
            return chance.bool(param.getAnnotation(BoolProvider.class).likelihood());
        }
        if (isAnnotated(param, IntegerProvider.class) && assignable(param, Integer.class, int.class)) {
            IntegerProvider provider = param.getAnnotation(IntegerProvider.class);
            return chance.integer(provider.min(), provider.max());
        }
        return null;
    }

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        Chance chance = getChance(extensionContext);

        for (Field field : extensionContext.getRequiredTestClass().getDeclaredFields()) {
            if (isAnnotated(field, ChanceProvider.class)) {
                field.setAccessible(true);
                field.set(o, chance);
            }
        }
    }

    private boolean assignable(Parameter param, Class<?>... checks) {
        for (Class<?> toCheck : checks) {
            if (param.getType().isAssignableFrom(toCheck)) {
                return true;
            }
        }
        return false;
    }

    private Chance getChance(final ExtensionContext ctx) {
        ExtensionContext.Store store = ctx.getStore(ExtensionContext.Namespace.create("Chance"));
        return (Chance) store.getOrComputeIfAbsent("Chance", str -> ChanceFactory.chance());
    }
}
