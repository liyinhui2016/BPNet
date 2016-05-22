package com.chenjie.bpnet.function;

/**
 * Created by yinhui on 2016/5/22.
 */
@FunctionalInterface
public interface Evaluate {
    /**
     * 评价函数
     * @param r2r 阳性正确
     * @param r2e 假阴
     * @param e2r 假阳
     * @param e2e 阴性正确
     * @return
     */
    double eval(int r2r,int r2e,int e2r,int e2e);
}
