package com.chenjie.bpnet.function;

/**
 * Created by yinhui on 2016/5/22.
 */
@FunctionalInterface
public interface Evaluate {
    /**
     * 评价函数
     * @return
     */
    double eval(int r,int f);
}
