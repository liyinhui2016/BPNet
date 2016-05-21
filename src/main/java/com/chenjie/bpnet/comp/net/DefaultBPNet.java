package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.elem.DefaultNode;
import com.chenjie.bpnet.comp.elem.INode;
import com.chenjie.bpnet.function.LinearCombine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by yinhui on 2016/5/21.
 */
public class DefaultBPNet extends AbsBPNet {
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


    @Override
    public List<Double> predict(List<Double> input) {
        if (input == null || input.size() == 0)
            return null;
        List<Double> l1 = new ArrayList<>();
        List<Double> l2 = new ArrayList<>();
        List<Double> t;
        //输入
        for (int i = 0; i < inputLayer().size(); ++i) {
            l1.add(inputLayer().get(i).outFun().process(input.get(i)));
        }
        for (int i = 0; i < hiddenLayers().size(); ++i) {
            for (int j = 0; j < hiddenLayers().get(i).size(); ++j) {
                DefaultNode node = hiddenLayers().get(i).get(j);
                List<Double> frontWeights = node.frontWeights().stream().map((w) -> w.weight()).collect(Collectors.toList());
                l2.add(node.outFun().process(LinearCombine.combineNum(l1, frontWeights)));
            }
            l1.clear();
            t = l2;
            l2 = l1;
            l1 = t;
        }
        for (int i = 0; i<outputLayer().size() ;++ i){
            DefaultNode node  = outputLayer().get(i);
            List<Double> frontWeight = node .frontWeights().stream().map(w->w.weight()).collect(Collectors.toList());
            l2.add(node.outFun().process(LinearCombine.combineNum(l1,frontWeight)));
        }
        return l2;
    }
}
