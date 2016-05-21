package com.chenjie.bpnet.comp.elem;

/**
 * Created by yinhui on 2016/5/21.
 */
public class DefaultWeight implements IWeight {

    private double weight;
    private  INode front;
    private  INode next;

    @Override
    public INode frontNode() {
        return front;
    }

    @Override
    public INode nextNode() {
        return next;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public void setWeight( double weight) {
        this.weight = weight;
    }
}
