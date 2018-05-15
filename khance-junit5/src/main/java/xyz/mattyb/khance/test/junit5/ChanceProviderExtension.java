package xyz.mattyb.khance.test.junit5;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.ChanceFactory;
import xyz.mattyb.khance.test.core.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class ChanceProviderExtension implements ParameterResolver, TestInstancePostProcessor {
    @Override
    public boolean supportsParameter(ParameterContext paramCtx, ExtensionContext extCtx) throws ParameterResolutionException {
        Parameter param = paramCtx.getParameter();
        return isAnnotated(param, BoolProvider.class, IntegerProvider.class, ChanceProvider.class, NaturalProvider.class,
                StringProvider.class);
    }

    @Override
    public Object resolveParameter(ParameterContext paramCtx, ExtensionContext extCtx) throws ParameterResolutionException {
        Chance chance = getChance(extCtx);

        Parameter param = paramCtx.getParameter();
        if (isAnnotated(param, ChanceProvider.class) && assignable(param, Chance.class)) {
            return chance;
        }
        if (isAnnotated(param, StringProvider.class) && assignable(param, String.class, CharSequence.class)) {
            StringProvider strProvider = param.getAnnotation(StringProvider.class);
            if (strProvider.length() < 0) {
                return chance.string(strProvider.casing());
            }
            return chance.string(strProvider.length(), strProvider.casing());
        }
        if (isAnnotated(param, BoolProvider.class) && assignable(param, Boolean.class, boolean.class)) {
            return chance.bool(param.getAnnotation(BoolProvider.class).likelihood());
        }
        if (isAnnotated(param, IntegerProvider.class) && assignable(param, Integer.class, int.class)) {
            IntegerProvider provider = param.getAnnotation(IntegerProvider.class);
            return chance.integer(provider.min(), provider.max());
        }
        if (isAnnotated(param, NaturalProvider.class) && assignable(param, Integer.class, int.class)) {
            NaturalProvider provider = param.getAnnotation(NaturalProvider.class);
            if (provider.numerals() > 0) {
                return chance.naturalNumerals(provider.numerals());
            } else {
                return chance.natural(provider.min(), provider.max());
            }
        }

        return null;
    }

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extCtx) throws Exception {
        Chance chance = getChance(extCtx);

        for (Field field : extCtx.getRequiredTestClass().getDeclaredFields()) {
            if (isAnnotated(field, ChanceProvider.class)) {
                field.setAccessible(true);
                field.set(o, chance);
            }
        }
    }

    @SafeVarargs
    private final boolean isAnnotated(AnnotatedElement element, Class<? extends Annotation>... checks) {
        for (Class<? extends Annotation> toCheck : checks) {
            if (AnnotationSupport.isAnnotated(element, toCheck)) {
                return true;
            }
        }
        return false;
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
