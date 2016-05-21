package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.Layer.HiddenLayer;
import com.chenjie.bpnet.comp.Layer.InputLayer;
import com.chenjie.bpnet.comp.Layer.OutputLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要功能是构建一个网路结构。
 * Created by yinhui on 2016/5/21.
 */
public abstract class AbsBPNet implements IBPNet<InputLayer,HiddenLayer,OutputLayer> {

    private InputLayer inputLayer;
    private OutputLayer outputLayer;
    private List<HiddenLayer>  hiddenLayers;

    @Override
    public void setUp(int input, int[] hidden, int out) {
        if(input <= 0)
            throw new RuntimeException("输入层至少一个节点");
        if(hidden.length<=0 )
            throw new RuntimeException("至少一个隐藏层");
        if(out <=0 )
            throw new RuntimeException("输出层一个节点");
        //初始化输入层
        inputLayer = new InputLayer();
        inputLayer.init(input);

        //初始化隐藏层
        hiddenLayers = new ArrayList<>();
        HiddenLayer h = null;
        for (int i = 0 ; i<hidden.length ;++ i){
            if( hidden [i] <=0 ){
                continue;
            }
            h = new HiddenLayer();
            h.init(hidden[i]);
            hiddenLayers.add(h);
        }
        //初始化输出成
        outputLayer = new OutputLayer();
        outputLayer.init(out);
        initConnect();

    }

    @Override
    public void initConnect() {

    }

    @Override
    public InputLayer inputLayer() {
        return inputLayer;
    }

    @Override
    public OutputLayer outputLayer() {
        return outputLayer;
    }

    @Override
    public List<HiddenLayer> hiddenLayers() {
        return hiddenLayers;
    }

}
