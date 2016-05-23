package com.chenjie.bpnet.function;

/**
 * Created by liyh on 2016/5/23.
 */

/**
 * 两个元素是否相等
 */
@FunctionalInterface
public interface GenerEq<T> {
    /**
     * 自定义两个元素是否相等。
     * @param t1
     * @param t2
     * @return
     */
    boolean eq(T t1,T t2);
}
