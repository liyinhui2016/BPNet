package com.chenjie.bpnet.function;

import java.util.List;

/**
 * 向量距离度量
 * Created by liyh on 2016/5/27.
 */
public interface Distance  {
    /**
     * 距离
     * @param v1
     * @param v2
     * @return
     */
    double dis(List<Double> v1, List<Double> v2) ;


    /**
     * 欧几里得距离
     * @param v1
     * @param v2
     * @return
     */
    static double eucDis(List<Double> v1, List<Double> v2){
        if ( v1 ==  null || v2 == null)
            return 0d;
        int len = v1.size()>v2.size()?v2.size():v1.size();
        double dis = 0d;
        for (int i = 0 ;i <len ;++i){
            double n1 = v1.get(i)==null?0:v1.get(i);
            double n2 = v2.get(i)==null ? 0: v2.get(i);
            dis+=Math.pow(n1-n2,2d);
        }
        return Math.sqrt(dis);
    }
}
