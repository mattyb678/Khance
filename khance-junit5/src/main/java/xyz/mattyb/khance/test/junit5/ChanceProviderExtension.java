package xyz.mattyb.khance.test.junit5;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import xyz.mattyb.khance.Chance;
import xyz.mattyb.khance.ChanceFactory;
import xyz.mattyb.khance.enums.Month;
import xyz.mattyb.khance.test.core.TimerProviderUtils;
import xyz.mattyb.khance.test.core.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChanceProviderExtension implements ParameterResolver, TestInstancePostProcessor {
    @Override
    public boolean supportsParameter(ParameterContext paramCtx, ExtensionContext extCtx) throws ParameterResolutionException {
        Parameter param = paramCtx.getParameter();
        return isAnnotated(param, BoolProvider.class, IntegerProvider.class, ChanceProvider.class,
                NaturalProvider.class, StringProvider.class, HashProvider.class, DieProvider.class,
                DiceProvider.class, IpProvider.class)
                || locationAnnotation(param) || timeAnnotation(param);
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
        if (isAnnotated(param, HashProvider.class) && assignable(param, String.class, CharSequence.class)) {
            HashProvider provider = param.getAnnotation(HashProvider.class);
            return chance.hash(provider.length(), provider.casing());
        }
        if (isAnnotated(param, IpProvider.class) && assignable(param, String.class, CharSequence.class)) {
            IpProvider provider = param.getAnnotation(IpProvider.class);
            return chance.web.ip();
        }
        if (isAnnotated(param, CityProvider.class) && assignable(param, String.class, CharSequence.class)) {
            CityProvider provider = param.getAnnotation(CityProvider.class);
            if (provider.value().length == 0) {
                return chance.location.city();
            } else {
                return chance.location.city(provider.value());
            }
        }
        if (isAnnotated(param, ZipProvider.class) && assignable(param, String.class, CharSequence.class)) {
            ZipProvider provider = param.getAnnotation(ZipProvider.class);
            if (provider.value().length == 0) {
                return chance.location.zip();
            } else {
                return chance.location.zip(provider.value());
            }
        }
        if (isAnnotated(param, DieProvider.class) && assignable(param, Integer.class, int.class)) {
            DieProvider provider = param.getAnnotation(DieProvider.class);
            int limit = 6;
            if (provider.value() > 0) {
                limit = provider.value();
            }
            return chance.dice.die(limit);
        }
        if (isAnnotated(param, DiceProvider.class) && assignableList(param, Integer.class)) {
            DiceProvider provider = param.getAnnotation(DiceProvider.class);
            int limit = 6;
            if (provider.value() > 0) {
                limit = provider.value();
            }
            int rolls = 3;
            if (provider.rolls() > 0) {
                rolls = provider.rolls();
            }
            return chance.dice.die(limit, rolls);
        }
        if (isAnnotated(param, NaturalProvider.class) && assignable(param, Integer.class, int.class)) {
            NaturalProvider provider = param.getAnnotation(NaturalProvider.class);
            if (provider.numerals() > 0) {
                return chance.naturalNumerals(provider.numerals());
            } else {
                return chance.natural(provider.min(), provider.max());
            }
        }
        if (isAnnotated(param, HourProvider.class) && assignable(param, Integer.class, int.class)) {
            HourProvider provider = param.getAnnotation(HourProvider.class);
            if (provider.value()) {
                return chance.time.hour(provider.value());
            } else {
                return chance.time.hour();
            }
        }
        if (isAnnotated(param, MonthProvider.class)) {
            MonthProvider provider = param.getAnnotation(MonthProvider.class);
            if (assignable(param, Integer.class, int.class)) {
                return chance.time.monthNumeric();
            } else if (assignable(param, String.class, CharSequence.class)) {
                return TimerProviderUtils.getMonth(provider, chance);
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

    private boolean timeAnnotation(AnnotatedElement param) {
        return isAnnotated(param, HourProvider.class, MonthProvider.class);
    }

    private boolean locationAnnotation(AnnotatedElement param) {
        return isAnnotated(param, CityProvider.class, ZipProvider.class);
    }

    private boolean assignableList(
            Parameter param,
            Class<?>... genericTypes
    ) {
        boolean isCollection = param.getType().isAssignableFrom(List.class);
        ParameterizedType parameterizedType = (ParameterizedType) param.getParameterizedType();
        List<String> genericNames = Arrays.stream(genericTypes)
                .map(Class::getName)
                .collect(Collectors.toList());
        boolean genericsMatch = Arrays.stream(parameterizedType.getActualTypeArguments())
                .allMatch(typeArg -> genericNames.contains(typeArg.getTypeName()));
        return isCollection && genericsMatch;
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
