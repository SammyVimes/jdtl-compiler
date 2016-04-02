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


    @Test
    public void testImage() {
        InputStream file = this.getClass().getClassLoader().getResourceAsStream("test2.html");
        URL resources = this.getClass().getClassLoader().getResource("test2.html");

        String test1path = resources.getFile();
        String resPath = test1path.substring(0, test1path.indexOf("test2.html"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Context context = new Context(resPath);
        Parser parser = new Parser(context);
        parser.parse(file, baos);
        String s = baos.toString();
        Assert.assertTrue(s.contains("https://avatars1.githubusercontent.com/u/4058545?v=3&s=460"));
        Assert.assertTrue(s.contains("test.png"));
    }


    @Test
    public void testGoogleImage() {
        InputStream file = this.getClass().getClassLoader().getResourceAsStream("test3.html");
        URL resources = this.getClass().getClassLoader().getResource("test3.html");

        String test1path = resources.getFile();
        String resPath = test1path.substring(0, test1path.indexOf("test3.html"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Context context = new Context(resPath);
        Parser parser = new Parser(context);
        parser.parse(file, baos);
        String s = baos.toString();
        Assert.assertTrue(s.contains("obama.png"));
    }

}
