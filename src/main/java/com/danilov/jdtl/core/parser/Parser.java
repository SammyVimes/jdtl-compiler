package com.danilov.jdtl.core.parser;

import com.danilov.jdtl.core.context.Context;
import com.danilov.jdtl.core.function.GoogleImage;
import com.danilov.jdtl.core.function.IFunction;
import com.danilov.jdtl.core.function.Image;
import com.danilov.jdtl.core.function.Include;
import com.danilov.jdtl.core.util.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Semyon on 02.04.2016.
 */
public class Parser {

    private List<IFunction> functions = new ArrayList<>();
    private Context context;

    public Parser(final Context context) {
        this.context = context;
        functions.add(new Image());
        functions.add(new Include());
        functions.add(new GoogleImage());
    }

    public void parse(final InputStream file, final OutputStream outputStream) {
        String fullFile = TextUtils.readFully(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        int idxStartFn;
        while ((idxStartFn = fullFile.indexOf("${")) != -1) {
            int lastIdxFn = -1;
            if ((lastIdxFn = fullFile.indexOf("}", idxStartFn)) == -1) {
                throw new RuntimeException("Malformed file");
            }
            String function = fullFile.substring(idxStartFn, lastIdxFn + 1);
            FunctionBlock functionBlock = new FunctionBlock(function);
            functionBlock.parse();

            String functionName = functionBlock.getFunctionName();
            Map<String, String> keyValueMap = functionBlock.getKeyValueMap();
            String raw = functionBlock.getRaw();

            for (IFunction fn : functions) {
                if (fn.isApplicable(functionName, keyValueMap.keySet(), keyValueMap.values(), raw)) {
                    String parsed = fn.process(keyValueMap, context);
                    String pre = fullFile.substring(0, idxStartFn);
                    String post = fullFile.substring(lastIdxFn + 1);
                    fullFile = pre + parsed + post;
                    break;
                }
            }
        }
        try {
            writer.write(fullFile);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write");
        }
    }



}
