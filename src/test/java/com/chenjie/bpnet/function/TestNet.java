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
        net.setUp(1,new int []{2,2,2},2);

        SampleTrainer<DefaultBPNet> trainer = new SampleTrainer<>();
        List<DefaultData> dataSet = mockData(200);
//        DefaultData d = new DefaultData();
//        d.setLable(Arrays.asList(0.8));
//        d.setProps(Arrays.asList(0.8));
//         net.predict(d);
//        d.setProps(Arrays.asList(0.1));
//         net.predict(d);
//        net.persist();
        trainer.train(net,dataSet);

    }

    public List<DefaultData> mockData(int size){
        List<DefaultData> dataSet = new ArrayList<>();
        for (int i = 0 ;i<size ;++ i){
            DefaultData d = new DefaultData();
            dataSet.add(d);
            double a = randGet().get();
            double b = randGet().get();
            double c = randGet().get();
            List<Double> props= new ArrayList<>();
            props.add(a);//props.add(b);props.add(c);
//            double detla = b*b-4*a*c;
            List<Double> lable = new ArrayList<>();
            lable.add(1d);
            lable.add(0d);
//            if(a>=0.8){
//                lable.add(1d);
//                lable.add(0d);
//            }
//            else{
//                lable.add(0d);
//                lable.add(1d);
//            }
            d.setLable(lable);
            d.setProps(props);
        }
        return  dataSet;
    }

    private Supplier<Double> randGet(){
        return ()-> Math.random()*scale+f;
    }
}
