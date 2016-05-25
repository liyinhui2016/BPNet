package com.chenjie.bpnet.comp.net;

import com.chenjie.bpnet.comp.Layer.DefaultCommLayer;
import com.chenjie.bpnet.comp.data.DefaultData;
import com.chenjie.bpnet.comp.data.IData;
import com.chenjie.bpnet.comp.elem.DefaultNode;
import com.chenjie.bpnet.comp.elem.DefaultWeight;
import com.chenjie.bpnet.function.Evaluate;
import com.chenjie.bpnet.function.LinearCombine;
import com.chenjie.bpnet.function.Reduce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by yinhui on 2016/5/21.
 */
public class DefaultBPNet extends AbsBPNet<DefaultData> {

    private boolean trainMode = false;

    private Evaluate eval;

    private int maxLoop;

    private double minRate;

    /**
     * 训练模式下节点输出状态
     */
    public Map<DefaultNode, Double> trainOutState = new HashMap<>();


    @Override
    public void persist() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson = new Gson();
        System.out.println(gson.toJson(this.inputLayer()));
    }

    @Override
    public Supplier<Double> thetaInit() {
        return () -> Math.random()*2-1;
    }

    @Override
    public Supplier<Double> weightInit() {
        return () -> Math.random()*2-1;
    }


    public List<Double> predict0(List<Double> input) {
        if (input == null || input.size() == 0)
            return null;
        List<Double> l1 = new ArrayList<>();
        List<Double> l2 = new ArrayList<>();
        List<Double> t;
        //输入
        for (int i = 0; i < inputLayer().size(); ++i) {
            double  outValue = inputLayer().get(i).outFun().process(input.get(i));
            l1.add(outValue);
            if (this.trainMode)
                this.trainOutState.put(inputLayer().get(i), outValue);
//            System.out.println("inputOut : "+l1);
        }
        for (int i = 0; i < hiddenLayers().size(); ++i) {
            for (int j = 0; j < hiddenLayers().get(i).size(); ++j) {
                DefaultNode node = hiddenLayers().get(i).get(j);
                List<Double> frontWeights = node.frontWeights().stream().map((w) -> w.weight()).collect(Collectors.toList());
                double outValue = node.outFun().process(LinearCombine.combineNum(l1, frontWeights) + node.theta());
                l2.add(outValue);

                if (this.trainMode)
                    this.trainOutState.put(node, outValue);
            }
//            System.out.println("hidden : "+i +" : "+l2);
            l1.clear();
            t = l2;
            l2 = l1;
            l1 = t;
        }
        for (int i = 0; i < outputLayer().size(); ++i) {
            DefaultNode node = outputLayer().get(i);
            List<Double> frontWeight = node.frontWeights().stream().map(w -> w.weight()).collect(Collectors.toList());
            double outValue = node.outFun().process(LinearCombine.combineNum(l1, frontWeight) + node.theta());
            l2.add(outValue);
            if (this.trainMode)
                this.trainOutState.put(node, outValue);
        }
        return l2;
    }

    @Override
    public Reduce<Double> errScale() {
        return (o, t) -> o * (1 - o) * (t - o);
    }

    @Override
    public double lambda() {
        return 0.5;
    }


    /**
     * 一次训练
     *
     * @param trainSet
     * @param lable
     */
    private void train1(List<Double> trainSet, List<Double> lable,double dataWeight) {
        List<Double> pRes = predict0(trainSet);
        List<Double> error = new ArrayList<>();
        for (int i = 0; i < pRes.size(); ++i) {
            error.add(errScale().reduce(pRes.get(i), lable.get(i)));
        }
        //计算隐藏层最后一层的错误
        List<Double> frontErr = frontErr(hiddenLayers().get(hiddenLayers().size()-1), error);

        //更新输出层权重和偏置值
        updateWeightAndTheta(outputLayer(), error,dataWeight);

        for (int i = hiddenLayers().size() - 1; i > 0; i--) {
            error = frontErr;
            frontErr = frontErr(hiddenLayers().get(i-1), error);
            //更新权重和和偏置值
            updateWeightAndTheta(hiddenLayers().get(i), error,dataWeight);
        }
    }


    /**
     * 计算网络上一层错误向量。
     *
     * @param front  ：当前层网络
     * @param curErr ：当前层错误
     * @return
     */
    private List<Double> frontErr(DefaultCommLayer front, List<Double> curErr) {
        List<Double> err = new ArrayList<>();
        List<DefaultNode> nodes = front.nodes();
        for (DefaultNode node : nodes) {
            List<Double> ws = node.nextWeights().stream().map(w -> w.weight()).collect(Collectors.toList());
            err.add(errScale().reduce(trainOutState.get(node), LinearCombine.combineNum(curErr, ws)));
        }
        return err;
    }

    /**
     * 更新weight 和 theta
     *
     * @param curLayer 当前层
     * @param curErr   当前层错误向量。
     */
    private void updateWeightAndTheta(DefaultCommLayer curLayer, List<Double> curErr,double dataWeight) {
        List<DefaultNode> nodes = curLayer.nodes();
        for (int i = 0; i < nodes.size(); ++i) {
            nodes.get(i).setTheta(nodes.get(i).theta() + lambda() * curErr.get(i));
            List<DefaultWeight> ws = nodes.get(i).frontWeights();
            for (int j = 0; j < ws.size(); ++j) {
                ws.get(j).setWeight(ws.get(j).weight() +
                        trainOutState.get( ws .get(j). frontNode()) * curErr.get(i) * lambda() * dataWeight);
            }
        }
    }

    @Override
    public void train(DefaultData train_data,double dataWeight) {
        train1(train_data.props(), train_data.lable(),dataWeight);
//        System.out.println(this.trainOutState);
    }

    @Override
    public DefaultData predict(DefaultData data) {
        List<Double> lable = predict0(data.props());
//        System.out.println(data.props()+"|||"+lable);
        DefaultData resData = new DefaultData();
        resData.setLable(lable);
        return resData;
    }


    @Override
    public void enterTrainMode() {
        this.trainMode = true;
    }

    @Override
    public void leaveTrainMode() {
        this.trainMode = false;
    }
}
