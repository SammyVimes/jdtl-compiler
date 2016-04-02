package com.danilov.jdtl.core.function;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Semyon on 02.04.2016.
 */
public abstract class BaseFunction implements IFunction {

    @Override
    public boolean isApplicable(@Nonnull final String functionName,
                                @Nonnull final Set<String> keys,
                                @Nonnull final Collection<String> values,
                                @Nonnull final String raw) {
        if (!name().equals(functionName)) {
            return false;
        }
        return keys.containsAll(getRequiredArguments());
    }

    @Nonnull
    public abstract Set<String> getRequiredArguments();

}
