package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.data.DefaultData;
import com.chenjie.bpnet.comp.data.IData;
import com.chenjie.bpnet.function.Evaluate;
import com.chenjie.bpnet.function.GenerEq;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yinhui on 2016/5/22.
 */
public  class SampleTrainer<T extends ITrainnable> implements ITrainer<T,DefaultData> {

    private final  static Logger logger = Logger.getLogger(SampleTrainer.class);

    /**
     * 训练一个模型，使用简单训练法，后续实现 n-cross validate.
     * @param model ： 模型
     * @param dataSet ： 数据集
     */
    @Override
    public void train(T model, Collection<DefaultData> dataSet) {
        model.enterTrainMode();
        for (int i = 0 ;i<maxLoop() ; ++ i){
            int r = 0;
            int f = 0;
            for(DefaultData d: dataSet){
                IData data = model.predict(d);
                logger.info(data.lable()+"|||"+d.lable()+"|||"+d.props());
                if(resEq().eq(data.lable(),d.lable()))
                    ++r;
                else
                    ++f;
            }

//            ((DefaultBPNet)model).persist();
            logger.info(String.format("maxLoop :%s ,curLoop : %s ,minRate :%s ,curRate: %s ",maxLoop(),i,minRate(),eval().eval(r,f)));
            //正确率达标
            if(eval().eval(r,f)>minRate())
                break;

            for (DefaultData d:dataSet)
                model.train(d);
        }
        model.leaveTrainMode();
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
