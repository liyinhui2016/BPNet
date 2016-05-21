package com.chenjie.bpnet.function;

import com.chenjie.bpnet.comp.net.AbsBPNet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Supplier;

/**
 * Created by yinhui on 2016/5/21.
 */
public class TestAbsBPNet {

    @Test
    public void testSetUp(){
        AbsBPNet net = new AbsBPNet() {
            @Override
            public void train() {

            }

            @Override
            public void persist() {

            }

            @Override
            public Supplier<Double> thetaInit() {
                return ()->Math.random();
            }

            @Override
            public Supplier<Double> weightInit() {
                return ()->Math.random();
            }
        };

        net.setUp(3,new int []{2,0,6},2);
        Assert.assertEquals(net.inputLayer().size()==3,true);
        Assert.assertEquals(net.outputLayer().size()==2,true);
        Assert.assertEquals(net.hiddenLayers().size()==2,true);
        Assert.assertEquals(net.hiddenLayers().get(0).size()==2,true);
        Assert.assertEquals(net.hiddenLayers().get(1).size()==6,true);

    }
}
