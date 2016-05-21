package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.Layer.DefaultCommLayer;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by yinhui on 2016/5/21.
 */
public interface IBPNet<IN extends DefaultCommLayer,HI extends DefaultCommLayer ,OUT extends DefaultCommLayer> {

    /**
     * 输入层
     * @return
     */
    IN inputLayer();

    /**
     * 输出层
     * @return
     */
    OUT outputLayer();

    /**
     * 隐藏层。
     * @return
     */
    List<HI> hiddenLayers();

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


    /**
     * 初始化链接各层。
     */
    void initConnect();

    /**
     * 初始化theta
     * @return
     */
    Supplier<Double> thetaInit();

    /**
     * 初始化weight
     * @return
     */
    Supplier<Double> weightInit();


}
