package com.danilov.jdtl.core.function;

import com.danilov.jdtl.core.context.Context;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Semyon on 02.04.2016.
 */
public interface IFunction {

    @Nonnull
    String name();

    String process(@Nonnull final Map<String, String> args, @Nonnull final Context context);

    public boolean isApplicable(@Nonnull final String functionName,
                                @Nonnull final Set<String> keys,
                                @Nonnull final Collection<String> values,
                                @Nonnull final String raw);
}
