package com.chenjie.bpnet.comp.elem;

import com.chenjie.bpnet.function.IOutFunction;

import java.util.List;

/**
 * 树突
 * Created by yinhui on 2016/5/21.
 */
public interface INode {

    /**
     * 输出函数
     * @return
     */
    IOutFunction outFun();

    /**
     * 偏置值
     * @return
     */
    double theta();

    /**
     * 设置偏置值
     * @param theta
     */
    void setTheta(double theta);

    /**
     * 前置权重
     * @return
     */
    List<? extends IWeight> frontWeights();

    /**
     * 后置权重
     * @return
     */
    List<? extends IWeight> nextWeights();

}
