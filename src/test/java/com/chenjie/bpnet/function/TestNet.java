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

    int scale =1;
    int f = 0;

    @Test
    void testNet(){
        DefaultBPNet net = new DefaultBPNet();
        net.setUp(2,new int []{10},2);

        SampleTrainer<DefaultBPNet> trainer = new SampleTrainer<>(net);
        List<DefaultData> dataSet = mockData(10);
//        trainer.train(net,Arrays.asList(
//                new DefaultData(Arrays.asList(10d,5d),Arrays.asList(0d,1d)),
//                new DefaultData(Arrays.asList(5d,15d),Arrays.asList(1d,0d)))
//        );

        trainer.train(dataSet);
        System.out.println(net.predict(new DefaultData(Arrays.asList(0.8,0.3),Arrays.asList())).lable());
        System.out.println(net.predict(new DefaultData(Arrays.asList(0.3,0.8),Arrays.asList())).lable());

    }

    public List<DefaultData> mockData(int size){
        List<DefaultData> dataSet = new ArrayList<>();
        for (int i = 0 ;i<size ;++ i){
            DefaultData d = new DefaultData();
            dataSet.add(d);
            double a = randGet().get();
            double b = randGet().get();
//            double c = randGet().get();
            d.setProps(Arrays.asList(a,b));
            d.setLable(Arrays.asList(a+b,a-b));
        }
        return  dataSet;
    }

    private Supplier<Double> randGet(){
        return ()-> Math.random()*scale+f;
    }
}
