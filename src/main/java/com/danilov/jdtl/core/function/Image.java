package com.danilov.jdtl.core.function;

import com.danilov.jdtl.core.context.Context;
import com.danilov.jdtl.core.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Semyon on 02.04.2016.
 */
public class Image extends BaseFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(Image.class);

    private static final String FILE = "file";
    private static final String LOCAL = "local";
    private static Set<String> required = new HashSet<>(Arrays.asList(FILE, LOCAL));

    @Nonnull
    @Override
    public Set<String> getRequiredArguments() {
        return required;
    }

    @Nonnull
    @Override
    public String name() {
        return "image";
    }

    @Override
    public String process(@Nonnull final Map<String, String> args, @Nonnull final Context context) {
        final String filePath = args.get(FILE);
        String localValue = args.get(LOCAL);

        final String htmlImg = "<img src=\"{}\"/>";

        boolean isLocal = Boolean.valueOf(localValue);
        String path = isLocal ? context.getLocalPath(filePath) : filePath;
        return TextUtils.format(htmlImg, path);
    }

}