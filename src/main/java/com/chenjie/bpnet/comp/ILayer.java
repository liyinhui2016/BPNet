package com.chenjie.bpnet.comp;

import java.util.List;

/**
 * Created by yinhui on 2016/5/21.
 */
public interface ILayer {

    /**
     * 所有的神经
     * @return
     */
    List<? extends INode> nodes();
}
