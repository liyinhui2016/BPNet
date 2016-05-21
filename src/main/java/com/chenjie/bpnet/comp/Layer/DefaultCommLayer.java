package com.chenjie.bpnet.comp.Layer;

import com.chenjie.bpnet.comp.elem.DefaultNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public abstract class DefaultCommLayer implements ILayer<DefaultNode> {

    /**
     * 节点
     */
    List<DefaultNode> nodes;

    @Override
    public List<DefaultNode> nodes() {
        return nodes;
    }

    @Override
    public void init(int n) {
        if (n == 0)
            throw new RuntimeException("至少一个输入节点");
        nodes = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            nodes.add(new DefaultNode());
        }

    }


    @Override
    public void add(DefaultNode node) {

    }
}
