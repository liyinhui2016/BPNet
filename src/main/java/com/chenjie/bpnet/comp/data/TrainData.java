package com.chenjie.bpnet.comp.data;

import java.util.List;

/**
 * Created by yinhui on 2016/5/22.
 */
public interface TrainData {
    /**
     * 样本
     * @return
     */
    List<Double> train();

    /**
     * 标签
     * @return
     */
    List<Double> lable();
}
