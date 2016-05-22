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
    Map<DefaultNode, Double> trainOutState = new HashMap<>();


    @Override
    public void persist() {
        Gson gson = new Gson();
        System.out.println(gson.toJson(this));
    }

    @Override
    public Supplier<Double> thetaInit() {
        return () -> Math.random();
    }

    @Override
    public Supplier<Double> weightInit() {
        return () -> Math.random();
    }


    public List<Double> predict0(List<Double> input) {
        if (input == null || input.size() == 0)
            return null;
        List<Double> l1 = new ArrayList<>();
        List<Double> l2 = new ArrayList<>();
        List<Double> t;
        //输入
        for (int i = 0; i < inputLayer().size(); ++i) {
            l1.add(inputLayer().get(i).outFun().process(input.get(i)));
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
        return 0.3;
    }

    /**
     * 一次训练
     *
     * @param trainSet
     * @param lable
     */
    private void train1(List<Double> trainSet, List<Double> lable) {
        List<Double> pRes = predict0(trainSet);
        List<Double> error = new ArrayList<>();
        for (int i = 0; i < pRes.size(); ++i) {
            error.add(errScale().reduce(pRes.get(i), lable.get(i)));
        }
        //计算隐藏层最后一层的错误
        List<Double> frontErr = frontErr(outputLayer(), error);

        //更新输出层权重和偏置值
        updateWeightAndTheta(outputLayer(), error);

        for (int i = hiddenLayers().size() - 1; i >= 0; i--) {
            error = frontErr;
            frontErr = frontErr(hiddenLayers().get(i), error);
            //更新权重和和偏置值
            updateWeightAndTheta(hiddenLayers().get(i), error);
        }
    }


    /**
     * 计算网络上一层错误向量。
     *
     * @param front  ：上一层网络
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
    private void updateWeightAndTheta(DefaultCommLayer curLayer, List<Double> curErr) {
        List<DefaultNode> nodes = curLayer.nodes();
        for (int i = 0; i < nodes.size(); ++i) {
            nodes.get(i).setTheta(nodes.get(i).theta() + lambda() * curErr.get(i));
            List<DefaultWeight> ws = nodes.get(i).frontWeights();
            for (int j = 0; j < ws.size(); ++j) {
                ws.get(j).setWeight(ws.get(j).weight() + trainOutState.get(ws.get(j).frontNode()) * curErr.get(i) * lambda());
            }
        }
    }

    @Override
    public void train(Iterator<DefaultData> train_data) {
        trainMode = true;
        for (int i = 0; i < maxLoop; ++i) {
            while (train_data.hasNext()) {
                DefaultData trainData = train_data.next();
                train1(trainData.props(), trainData.lable());
            }
        }
        trainMode = false;
    }

    @Override
    public void predict(DefaultData data) {
        List<Double> lable = predict0(data.props());
        data.setLable(lable);
    }
}
