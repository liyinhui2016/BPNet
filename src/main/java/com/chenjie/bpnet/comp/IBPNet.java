package com.chenjie.bpnet.comp;

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
     * bp训练
     */
    void train();

    /**
     * 持久化
     */
    void persist();

}
