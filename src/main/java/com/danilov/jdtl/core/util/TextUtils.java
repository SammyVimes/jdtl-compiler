package com.danilov.jdtl.core.util;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Semyon on 02.04.2016.
 */
public class TextUtils {

    @Nonnull
    public static String format(@Nonnull final String text, String... params) {
        int idx = -1;
        int i = 0;
        String result = text;
        while ((idx = result.indexOf("{}")) != -1) {
            if (i > params.length - 1) {
                throw new ArrayIndexOutOfBoundsException("Arguments quantity doesn't match template");
            }
            String arg = params[i];
            result = result.substring(0, idx) + arg + result.substring(idx + 2);
        }
        return result;
    }


    public static String readFully(final InputStream inputStream) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = "";
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            return "";
        }
        return buffer.toString();
    }

}
