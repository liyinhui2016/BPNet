package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.data.DefaultData;
import com.chenjie.bpnet.function.Distance;
import com.chenjie.bpnet.function.Evaluate;
import com.chenjie.bpnet.function.GenerEq;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yinhui on 2016/5/22.
 */
public  class SampleTrainer<T extends ITrainnable<DefaultData>> implements ITrainer<T,DefaultData> {

    private final static Logger mesLogger = Logger.getLogger("mseFile");

    private  double mse;

    private T model;

    public SampleTrainer(){    }


    /**
     * @param model ： 需要训练的模型
     */
    public SampleTrainer(T model){
        this.model = model;
    }

    /**
     * 训练一个模型，使用简单训练法，后续实现 n-cross validate.
     * @param dataSet ： 数据集
     */
    @Override
    public void train(Collection<DefaultData> dataSet) {
        //模型进入训练模式
        this.model().enterTrainMode();
        for (int i = 0 ;i<maxLoop() ; ++ i){
            for (DefaultData d:dataSet)
                model().train(d,(double)1/dataSet.size());
            //计算mse
            double err = 0d;
            for (DefaultData data : dataSet){
                DefaultData d = this.model().predict(data);
                err += Math.pow(Distance.eucDis(data.lable(),d.lable()),2) ;
            }
            this.mse = Math.sqrt(err/dataSet.size());
            mesLogger.debug(this.mse());
        }

        //模型离开训练模式
        model().leaveTrainMode();
    }

    @Override
    public T model() {
        return this.model;
    }

    /**
     * 准确率作为标准
     * @return
     */
    @Override
    public Evaluate eval() {
        return (int r,int f)->(double)r/(r+f);
    }

    @Override
    public int maxLoop() {
        return 2000;
    }
    @Override
    public double minRate() {
        return 1.1;
    }

    @Override
    public GenerEq<List<Double>> resEq() {
        return (List<Double> l1, List<Double> l2)-> maxIndex(l1)==maxIndex(l2);
    }

    @Override
    public double mse() {
        return this.mse;
    }

    /**
     * 求得最大值得下标
     * @param list
     * @return
     */
    private int maxIndex(List<Double> list){
        int idx = 0;
        double max = list.get(0);
        Iterator<Double> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()){
            double t = iterator.next();
            if(t > max) {
                max = t;
                idx = i;
            }
            ++ i;
        }
        return  idx;
    }
}
