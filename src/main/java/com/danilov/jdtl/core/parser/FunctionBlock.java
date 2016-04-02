package com.danilov.jdtl.core.parser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semyon on 02.04.2016.
 */
public class FunctionBlock {

    private static final String fnPattern = "\\$\\{(\\w+)([ ]?(\\w+)=\"([a-zA-Z0-9/\\.]+)\")*}";
    private static final String fnValuesPattern = "(\\w+)=\"([a-zA-Z0-9/\\.]+)\"";

    private Map<String, String> keyValueMap = new HashMap<>();
    private String raw;
    private String functionName;

    public FunctionBlock(final String raw) {
        this.raw = raw;
    }

    public void parse() {
        Pattern pattern = Pattern.compile(fnPattern);
        Matcher matcher = pattern.matcher(raw);
        if (matcher.matches()) {
            functionName = matcher.group(1);
            Pattern valuesPattern = Pattern.compile(fnValuesPattern);
            Matcher valuesMatcher = valuesPattern.matcher(raw);
            while (valuesMatcher.find()) {
                String key = valuesMatcher.group(1);
                String value = valuesMatcher.group(2);
                keyValueMap.put(key, value);
            }
        }
    }

    public Map<String, String> getKeyValueMap() {
        return keyValueMap;
    }

    public String getRaw() {
        return raw;
    }

    public String getFunctionName() {
        return functionName;
    }

}
