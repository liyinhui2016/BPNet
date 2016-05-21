package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.Layer.IHiddenLayer;
import com.chenjie.bpnet.comp.Layer.IInputLayer;
import com.chenjie.bpnet.comp.Layer.IOutputLayer;

import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public interface IBPNet {

    /**
     * 输入层
     * @return
     */
    IInputLayer inputLayer();

    /**
     * 输出层
     * @return
     */
    IOutputLayer outputLayer();

    /**
     * 隐藏层。
     * @return
     */
    List<? extends IHiddenLayer> hiddenLayers();

    /**
     * 设置 结构。
     * @param input
     * @param hidden
     * @param out
     */
    void setUp(int input,int [] hidden,int out);

    /**
     * bp训练
     */
    void train();

    /**
     * 持久化
     */
    void persist();



}
