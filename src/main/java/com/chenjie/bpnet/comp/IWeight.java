package com.chenjie.bpnet.comp;

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
}
