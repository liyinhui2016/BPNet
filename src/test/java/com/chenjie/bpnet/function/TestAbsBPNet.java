package com.chenjie.bpnet.function;

import com.chenjie.bpnet.comp.net.AbsBPNet;
import com.chenjie.bpnet.comp.net.DefaultBPNet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by yinhui on 2016/5/21.
 */
public class TestAbsBPNet {

    @Test
    public void testSetUp(){

        DefaultBPNet net = new DefaultBPNet();
        net.setUp(3,new int []{2,0,4},2);
        List<Double> pRes = net.predict(Arrays.asList(10d,199d,100d));
        Assert.assertEquals(pRes.size()==2,true);
        Assert.assertEquals(net.inputLayer().size()==3,true);
        Assert.assertEquals(net.outputLayer().size()==2,true);
        Assert.assertEquals(net.hiddenLayers().size()==2,true);
        Assert.assertEquals(net.hiddenLayers().get(0).size()==2,true);
        Assert.assertEquals(net.hiddenLayers().get(1).size()==4,true);
    }
}
