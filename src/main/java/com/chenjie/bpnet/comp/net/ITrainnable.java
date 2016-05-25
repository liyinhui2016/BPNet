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
     * @param dataWeight :该数据权重
     */
    void train(D train_data,double dataWeight);

    /**
     * 预测
     * @param d
     */
    D predict(D d);

    /**
     * 进入训练模式
     */
    void enterTrainMode();

    /**
     * 离开训练模式
     */
    void leaveTrainMode();

}
