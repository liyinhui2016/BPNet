package com.chenjie.bpnet.comp.elem;

/**
 * 轴突
 * Created by yinhui on 2016/5/21.
 */
public interface IWeight {

    /**
     * 前置树突
     * @return
     */
    INode frontNode();

    /**
     * 后置树突
     * @return
     */
    INode nextNode();

    /**
     * 权重
     * @return
     */
    double weight();

    /**
     * 设置权重
     */
    void  setWeight( double weight);
}
