package com.gas.framwork;

import java.util.List;
import java.util.Map;

/**
 * Created by Mao on 10/10/2016.
 */
public interface Model {
    public State translate(State s, int action_id);
}
