package com.chenjie.bpnet.comp.elem;

import com.chenjie.bpnet.function.IOutFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public class DefaultNode implements INode<DefaultWeight> {

    private  double theta;

    private transient   List<DefaultWeight> frontWeights;

    private  List<DefaultWeight> nextWeights;


    @Override
    public IOutFunction outFun() {
        return IOutFunction::dfltProcess;
    }

    @Override
    public double theta() {
        return theta;
    }

    @Override
    public void setTheta(double theta) {
        this.theta = theta;
    }

    @Override
    public List<DefaultWeight> frontWeights() {
        return frontWeights;
    }

    @Override
    public List<DefaultWeight> nextWeights() {
        return nextWeights;
    }

    /**
     * 设置前缀权重
     * @param ws
     */
    public void setFrontWeights(List<DefaultWeight> ws){
        this.frontWeights = ws;
    }

    /**
     * 设置后置权重
     * @param ws
     */
    public void setNextWeights(List<DefaultWeight> ws){
        this.nextWeights = ws;
    }



}
