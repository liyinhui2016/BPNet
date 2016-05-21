package com.chenjie.bpnet.comp.elem;

/**
 * 轴突
 * Created by yinhui on 2016/5/21.
 */
public interface IWeight<NODE extends INode> {

    /**
     * 前置树突
     * @return
     */
    NODE frontNode();

    /**
     * 后置树突
     * @return
     */
    NODE nextNode();

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
