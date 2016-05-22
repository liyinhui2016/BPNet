package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.data.IData;

import java.util.Collection;
import java.util.Iterator;

/**
 * 训练接口
 * Created by yinhui on 2016/5/22.
 */
public interface ITrainnable <D extends IData>{

    /**
     * 根据训练集训练模型
     * @param train_data
     */
    void train(Iterator<D> train_data);

    /**
     * 预测
     * @param d
     */
    void predict(D d);

}
