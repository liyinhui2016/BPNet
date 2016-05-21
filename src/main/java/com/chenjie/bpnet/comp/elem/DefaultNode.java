package com.chenjie.bpnet.comp.elem;

import com.chenjie.bpnet.function.IOutFunction;

import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public class DefaultNode implements INode {

    private  double theta;

    private  List<? extends IWeight> frontWeights;

    private  List<? extends IWeight> nextWeights;


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
    public List<? extends IWeight> frontWeights() {
        return frontWeights;
    }

    @Override
    public List<? extends IWeight> nextWeights() {
        return nextWeights;
    }
}
