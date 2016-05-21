package com.chenjie.bpnet.comp.elem;

/**
 * Created by yinhui on 2016/5/21.
 */
public class DefaultWeight implements IWeight<DefaultNode> {

    private double weight;
    private  DefaultNode front;
    private  DefaultNode next;

    @Override
    public DefaultNode frontNode() {
        return front;
    }

    @Override
    public DefaultNode nextNode() {
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

    public void setFront(DefaultNode node){
        this.front = node;
    }
    public void setNext(DefaultNode node){
        this.next = node;
    }
}
