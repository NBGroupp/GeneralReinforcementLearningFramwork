package com.gas.inverse.gridworld;

import com.gas.framwork.Action;
import com.gas.framwork.RewardFunc;
import com.gas.framwork.State;

/**
 * Created by Mao on 12/10/2016.
 */
public class GridRewardFunc implements RewardFunc{

    @Override
    public double getReward(State s, Action action, State s_prime) {
        if(s_prime.getFeature("x")==3 && s_prime.getFeature("y")==0){
            return 10;
        }
        return 0;
    }
}
