package com.chenjie.bpnet.function;

import com.chenjie.bpnet.comp.data.DefaultData;
import com.chenjie.bpnet.comp.net.DefaultBPNet;
import com.chenjie.bpnet.comp.net.ITrainer;
import com.chenjie.bpnet.comp.net.SampleTrainer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by liyh on 2016/5/23.
 */
public class TestNet {

    int scale =2;
    int f = -1;

    @Test
    void testNet(){
        DefaultBPNet net = new DefaultBPNet();
        net.setUp(2,new int []{5,5,5},2);

        SampleTrainer<DefaultBPNet> trainer = new SampleTrainer<>();
//        List<DefaultData> dataSet = mockData(1);
//        DefaultData d = new DefaultData();
//        d.setLable(Arrays.asList(0.8));
//        d.setProps(Arrays.asList(0.8));
//         net.predict(d);
//        d.setProps(Arrays.asList(0.1));
//         net.predict(d);
//        net.persist();
        trainer.train(net,Arrays.asList(new DefaultData(Arrays.asList(10d,5d),Arrays.asList(0d,1d))));
        trainer.train(net,Arrays.asList(new DefaultData(Arrays.asList(5d,15d),Arrays.asList(1d,0d))));

        System.out.println(net.predict(new DefaultData(Arrays.asList(10d,5d),Arrays.asList(0d,1d))).lable());
        System.out.println(net.predict(new DefaultData(Arrays.asList(5d,10d),Arrays.asList(0d,1d))).lable());
        System.out.println(net.predict(new DefaultData(Arrays.asList(100d,50d),Arrays.asList(0d,1d))).lable());

    }

    public List<DefaultData> mockData(int size){
        List<DefaultData> dataSet = new ArrayList<>();
        for (int i = 0 ;i<size ;++ i){
            DefaultData d = new DefaultData();
            dataSet.add(d);
            d.setLable(Arrays.asList(0d,1d));
            d.setProps(Arrays.asList(10d,5d));
        }
        return  dataSet;
    }

    private Supplier<Double> randGet(){
        return ()-> Math.random()*scale+f;
    }
}
