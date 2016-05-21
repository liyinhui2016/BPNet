package com.chenjie.bpnet.function;

import com.chenjie.bpnet.comp.elem.DefaultWeight;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void testDefaultWeight(){
        List<DefaultWeight> ls = new ArrayList<>();
        DefaultWeight dw = new DefaultWeight();
        dw.setWeight(10);
        ls.add(dw);
        dw = new DefaultWeight();
        dw.setWeight(5);
        ls.add(dw);
        List<Double> ds = ls.stream().map(w->w.weight()).collect(Collectors.toList());
        Assert.assertEquals(ds.size()==2,true);
        Assert.assertEquals(Math.abs(ds.get(0)-10)<0.00001,true);
        Assert.assertEquals(Math.abs(ds.get(1)-5)<0.00001,true);
        System.out.println(ds);
    }
}
