package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.data.DefaultData;
import com.chenjie.bpnet.comp.data.IData;
import com.chenjie.bpnet.function.Evaluate;

import java.util.Iterator;

/**
 * Created by yinhui on 2016/5/22.
 */
public abstract class SampleTrainer<T extends ITrainnable> implements ITrainer<T,DefaultData> {


    @Override
    public void train(T model, Iterator<DefaultData> dataSet) {
        //todo train
    }

    @Override
    public Evaluate eval() {
        return (int r2r,int r2e,int e2r,int e2e)->(double)(r2r+e2e)/(r2e+r2r+e2e+e2r);
    }
}
