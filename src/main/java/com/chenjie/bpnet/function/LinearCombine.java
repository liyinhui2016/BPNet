package com.chenjie.bpnet.function;

import java.time.temporal.ValueRange;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by yinhui on 2016/5/21.
 */
@FunctionalInterface
public interface LinearCombine {

    /**
     * 向量的线性组合
     * @param values
     * @param weight
     * @return
     */
    double combine(Collection<Double> values, Collection<Double> weight);

    /**
     * 浮点数的线性组合
     * @param values
     * @param weights
     * @return
     */
    static double combineNum(Collection<Double> values,Collection<Double> weights){
        if ( values ==  null || weights == null || values.size() == 0 || weights.size() == 0) {
            return 0d;
        }
        Iterator<Double> iter = values.iterator();
        Iterator<Double> iterw = weights.iterator();
        double res  = 0d;
        while (iter.hasNext()){
            double value = iter.next();
            double w = iterw.hasNext()?iterw.next():0d;
            res+=value*w;
        }
        return res;
    }
}
