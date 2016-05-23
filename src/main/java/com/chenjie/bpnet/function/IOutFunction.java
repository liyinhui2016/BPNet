package com.chenjie.bpnet.function;

/**
 * Created by yinhui on 2016/5/21.
 */
@FunctionalInterface
public interface IOutFunction {

    /**
     * 映射函数，一般选择非线性模型，因为线性模型的线性组合还是线性模型。
     * @param input
     * @return
     */
    double process(double input);

    /**
     * 默认的映射函数，1/(1+e^-i)
     * @param input
     * @return
     */
    static double dfltProcess(double input){
        return  1/(1+Math.exp(-input/100));
    }

}
