package com.chenjie.bpnet.comp.Layer;

import com.chenjie.bpnet.comp.elem.INode;

import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public interface ILayer<NODE_T extends INode> {

    /**
     * 所有的神经
     * @return
     */
    List<NODE_T> nodes();

    /**
     * 初始化
     * @param n ： n个节点
     */
    void init(int n);

    /**
     * 添加一个节点
     * @param node
     */
    void add( NODE_T node);

    /**
     * layer节点数
     * @return
     */
    int size();

    /**
     * 后去第i个节点。
     * @param i
     * @return
     */
    NODE_T get(int i);
}
