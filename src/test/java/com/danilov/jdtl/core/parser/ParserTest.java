package com.danilov.jdtl.core.parser;

import com.danilov.jdtl.core.context.Context;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

/**
 * Created by Semyon on 02.04.2016.
 */
@RunWith(JUnit4.class)
public class ParserTest {

    @Test
    public void testSimple() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream file = this.getClass().getClassLoader().getResourceAsStream("test1.html");
        URL resources = this.getClass().getClassLoader().getResource("test1.html");

        String test1path = resources.getFile();
        String resPath = test1path.substring(0, test1path.indexOf("test1.html"));

        Context context = new Context(resPath);
        Parser parser = new Parser(context);
        parser.parse(file, baos);
        String s = baos.toString();
        Assert.assertTrue(s.contains("SUCCESS"));
    }

}
