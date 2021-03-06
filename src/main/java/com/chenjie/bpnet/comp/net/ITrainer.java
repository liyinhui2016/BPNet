package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.data.IData;
import com.chenjie.bpnet.function.Evaluate;
import com.chenjie.bpnet.function.GenerEq;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by yinhui on 2016/5/22.
 */
public interface ITrainer<T extends ITrainnable,D extends IData> {

    /**
     * 训练模型
     * @param dataSet ： 数据集
     */
    void train( Collection<D> dataSet);

    /**
     * 获取要训练的模型
     * @return
     */
    T model();
    /**
     * 模型评判指标
     * @return
     */
    Evaluate eval();

    /**
     * 最大循环
     * @return
     */
    int maxLoop();

    /**
     * 最小准确率
     * @return
     */
    double minRate();

    /**
     * 相等评判
     * @return
     */
    GenerEq resEq();

    /**
     * 均方误差
     * @return
     */
    double mse();


}
