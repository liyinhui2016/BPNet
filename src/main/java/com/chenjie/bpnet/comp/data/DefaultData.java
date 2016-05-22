package com.chenjie.bpnet.comp.data;

import java.util.List;

/**
 * Created by yinhui on 2016/5/22.
 */
public class DefaultData implements IData {

    private List<Double> props;

    private List<Double> lable;

    @Override
    public List<Double> props() {
        return props;
    }

    @Override
    public List<Double> lable() {
        return lable;
    }

    public void setProps(List<Double> props) {
        this.props = props;
    }

    public void setLable(List<Double> lable) {
        this.lable = lable;
    }
}
