package com.danilov.jdtl.core.parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

/**
 * Created by Semyon on 02.04.2016.
 */
@RunWith(JUnit4.class)
public class FunctionBlockTest {

    @Test
    public void testSimpleFunction() {
        String fn = "${myFunc}";
        FunctionBlock block = new FunctionBlock(fn);
        block.parse();
        Assert.assertEquals(block.getFunctionName(), "myFunc");
        Map<String, String> keyValueMap = block.getKeyValueMap();
        Assert.assertEquals(new HashSet<>(keyValueMap.keySet()), Collections.emptySet());
        Assert.assertEquals(new HashSet<>(keyValueMap.values()), Collections.emptySet());
    }

    @Test
    public void testFunctionWithOneParam() {
        String fn = "${myFunc foo=\"bar\"}";
        FunctionBlock block = new FunctionBlock(fn);
        block.parse();
        Assert.assertEquals(block.getFunctionName(), "myFunc");
        Map<String, String> keyValueMap = block.getKeyValueMap();
        Assert.assertEquals(new HashSet<>(keyValueMap.keySet()), new HashSet<String>(Arrays.asList("foo")));
        Assert.assertEquals(new HashSet<>(keyValueMap.values()), new HashSet<String>(Arrays.asList("bar")));
    }

    @Test
    public void testFunctionWithTwoParams() {
        String fn = "${myFunc foo=\"bar\" fridge=\"cookie\"}";
        FunctionBlock block = new FunctionBlock(fn);
        block.parse();
        Assert.assertEquals(block.getFunctionName(), "myFunc");
        Map<String, String> keyValueMap = block.getKeyValueMap();
        Assert.assertEquals(new HashSet<>(keyValueMap.keySet()), new HashSet<String>(Arrays.asList("foo", "fridge")));
        Assert.assertEquals(new HashSet<>(keyValueMap.values()), new HashSet<String>(Arrays.asList("bar", "cookie")));
    }

    @Test
    public void testFunctionWithStrangeParams() {
        String fn = "${myFunc foo=\"/bar/zzz1.text\" fridge=\"cookie\"}";
        FunctionBlock block = new FunctionBlock(fn);
        block.parse();
        Assert.assertEquals(block.getFunctionName(), "myFunc");
        Map<String, String> keyValueMap = block.getKeyValueMap();
        Assert.assertEquals(new HashSet<>(keyValueMap.keySet()), new HashSet<String>(Arrays.asList("foo", "fridge")));
        Assert.assertEquals(new HashSet<>(keyValueMap.values()), new HashSet<String>(Arrays.asList("/bar/zzz1.text", "cookie")));
    }

}
