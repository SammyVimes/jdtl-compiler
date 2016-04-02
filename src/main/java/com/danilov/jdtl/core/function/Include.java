package com.danilov.jdtl.core.function;

import com.danilov.jdtl.core.context.Context;
import com.danilov.jdtl.core.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.*;

import static com.danilov.jdtl.core.util.TextUtils.format;

/**
 * Created by Semyon on 02.04.2016.
 */
public class Include extends BaseFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(Include.class);

    private static final String FILE = "file";
    private static Set<String> required = new HashSet<>(Arrays.asList(FILE));

    @Nonnull
    @Override
    public String name() {
        return "include";
    }

    @Override
    public String process(@Nonnull final Map<String, String> args, @Nonnull final Context context) {
        final String filePath = args.get(FILE);
        InputStream file = null;
        try {
            file = context.getFile(filePath);
        } catch (IOException e) {
            LOGGER.warn(format("Failed to process inclusion of {} because {}", filePath, e.getMessage()), e);
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file));
        try {
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            LOGGER.warn(format("Failed to process inclusion of {} because {}", filePath, e.getMessage()), e);
            return "";
        }
        return buffer.toString();
    }

    @Nonnull
    @Override
    public Set<String> getRequiredArguments() {
        return required;
    }

}