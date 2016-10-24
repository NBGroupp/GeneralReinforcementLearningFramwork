package com.gas.inverse.gridworld;

import com.gas.framwork.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mao on 17/10/2016.
 */
public class GridRLAgent extends RLAgent {

    public GridRLAgent(Environment env, RewardFunc reward, Logger log, Map<String, Action> actions, Value value, double discount,double learn_rate,double epsilon) {
        super(env, reward, log, actions, value, discount,learn_rate , epsilon);
    }

    @Override
    public State initialize() {
        Random random = new Random();
        State init_state=null;
        //while(init_state==null || !isEndState(init_state)){
            int init_x = random.nextInt(4);
            int init_y = random.nextInt(4);
            init_state = new GridState(init_x, init_y);
        //}
        return init_state;
    }

    @Override
    protected Action policy() {
        State state = observe();
        return eMaxValueAction(state);
    }

    @Override
    protected boolean isEndState(State state) {
        if ((int) state.getFeature("x") == -1 && (int) state.getFeature("y") == -1) {
            return true;
        } else if ((int) state.getFeature("x") == 3 && (int) state.getFeature("y") == 0) {
            return true;
        } else {
            return false;
        }
    }


}
