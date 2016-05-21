package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.Layer.*;
import com.chenjie.bpnet.comp.elem.DefaultNode;
import com.chenjie.bpnet.comp.elem.DefaultWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要功能是构建一个网路结构。
 * Created by yinhui on 2016/5/21.
 */
public abstract class AbsBPNet implements IBPNet<InputLayer, HiddenLayer, OutputLayer> {

    private InputLayer inputLayer;
    private OutputLayer outputLayer;
    private List<HiddenLayer> hiddenLayers;

    @Override
    public void setUp(int input, int[] hidden, int out) {
        if (input <= 0)
            throw new RuntimeException("输入层至少一个节点");
        if (hidden.length <= 0)
            throw new RuntimeException("至少一个隐藏层");
        if (out <= 0)
            throw new RuntimeException("输出层一个节点");
        //初始化输入层
        inputLayer = new InputLayer();
        inputLayer.init(input);

        //初始化隐藏层
        hiddenLayers = new ArrayList<>();
        HiddenLayer h = null;
        for (int i = 0; i < hidden.length; ++i) {
            if (hidden[i] <= 0) {
                continue;
            }
            h = new HiddenLayer();
            h.init(hidden[i]);
            hiddenLayers.add(h);
        }
        if (hiddenLayers.size() == 0)
            throw new RuntimeException("隐藏层的大小不能是0");
        //初始化输出成
        outputLayer = new OutputLayer();
        outputLayer.init(out);
        initConnect();

    }

    @Override
    public void initConnect() {
        //输入层
        for (int i = 0; i < inputLayer().size(); ++i) {
            DefaultNode in = inputLayer().get(i);
            in.setFrontWeights(null);
            in.setNextWeights(new ArrayList<>());
        }
        //输出层
        for (int i = 0; i < outputLayer().size(); ++i) {
            DefaultNode out = outputLayer().get(i);
            out.setFrontWeights(new ArrayList<>());
            out.setNextWeights(null);
        }
        //隐藏层
        for(int i = 0 ;i<hiddenLayers.size(); ++ i){
            for (int j = 0 ; j < hiddenLayers.get(i).size() ;++ j){
                hiddenLayers.get(i).get(j).setFrontWeights(new ArrayList<>());
                hiddenLayers.get(i).get(j).setNextWeights(new ArrayList<>());
            }
        }

        List<DefaultCommLayer> all = new ArrayList<>();
        all.add(inputLayer);
        all.addAll(hiddenLayers);
        all.add(outputLayer);

        //链接各层网络
        for (int i = 0; i < all.size()-1; ++i) {
            connect2(all.get(i),all.get(i+1));
        }
    }

    private void connect2(DefaultCommLayer l1, DefaultCommLayer l2) {
        //链接两层网络
        for (int i = 0; i < l1.size(); ++i)
            l1.get(i).setTheta(this.thetaInit().get());
        for (int j = 0; j < l2.size(); ++j) {
            l2.get(j).setTheta(this.thetaInit().get());
        }
        for (int i = 0; i < l1.size(); ++i) {
            for (int j = 0; j < l2.size(); ++j) {
                DefaultWeight w = new DefaultWeight();
                w.setWeight(this.weightInit().get());
                w.setFront(l1.get(i));
                w.setNext(l2.get(j));
                l1.get(i).nextWeights().add(w);
                l2.get(j).frontWeights().add(w);
            }
        }
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
