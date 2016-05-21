package com.chenjie.bpnet.function;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public class TestLinearCombine {

    @Test
    public void testLinearCombine(){
        List<Double> values = Arrays.asList(0d,0d,1d,2d,3.7d);
        List<Double> weights = Arrays.asList(0d,0d,1d,0.5d);
        double c = LinearCombine.combineNum(values,weights);
        System.out.printf("combine result : %s \n ",c);
        Assert.assertEquals(Math.abs(c-2)<0.001,true);
    }
}
