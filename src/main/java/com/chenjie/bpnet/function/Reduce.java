package com.chenjie.bpnet.function;

/**
 * Created by yinhui on 2016/5/22.
 */
@FunctionalInterface
public interface Reduce<T> {
    T reduce (T t1,T t2);
}
