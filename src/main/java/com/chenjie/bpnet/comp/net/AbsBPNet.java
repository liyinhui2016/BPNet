package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.Layer.IHiddenLayer;
import com.chenjie.bpnet.comp.Layer.IInputLayer;
import com.chenjie.bpnet.comp.Layer.IOutputLayer;

import java.util.List;

/**
 * 主要功能是构建一个网路结构。
 * Created by yinhui on 2016/5/21.
 */
public abstract class AbsBPNet implements   IBPNet {

    private IInputLayer inputLayer;
    private IOutputLayer outputLayer;
    private List<? extends IHiddenLayer>  hiddenLayers;

    @Override
    public void setUp(int input, int[] hidden, int out) {

    }


    @Override
    public IInputLayer inputLayer() {
        return inputLayer;
    }

    @Override
    public IOutputLayer outputLayer() {
        return outputLayer;
    }

    @Override
    public List<? extends IHiddenLayer> hiddenLayers() {
        return hiddenLayers;
    }

}
