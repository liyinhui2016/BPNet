package com.chenjie.bpnet.function;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
class TestOutfunction {

    @Test
    public void testIOutFunction(){
        IOutFunction f = IOutFunction::dfltProcess;
        double v1  = f.process(0d);
        Assert.assertEquals(v1>=0 && v1<=1,true);
        double v2  = f.process(0.8d);
        Assert.assertEquals(v2>=0 && v2<=1,true);
        double v3  = f.process(-0.8d);
        Assert.assertEquals(v3>=0 && v3<=1,true);
        double v4  = f.process(999999999);
        Assert.assertEquals(v4>=0 && v4<=1,true);
        double v5  = f.process(-999999999);
        Assert.assertEquals(v5>=0 && v5<=1,true);
        System.out.println(String.format("v1=%s,v2=%s,v3=%s,v4=%s,v5=%s",v1,v2,v3,v4,v5));
        Assert.assertEquals(v5<=v3&v3<=v1&v1<=v2&&v2<=v4,true);
    }


}
