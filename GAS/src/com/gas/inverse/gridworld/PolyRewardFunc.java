package com.gas.inverse.gridworld;

import com.gas.framwork.Action;
import com.gas.framwork.RewardFunc;
import com.gas.framwork.State;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by Mao on 24/10/2016.
 */
public class PolyRewardFunc implements RewardFunc {

    private Map<String,Double> polynomialParaMap;
    public PolyRewardFunc(Map<String,Double> polynomialParaMap){
        this.polynomialParaMap = polynomialParaMap;
    }
    @Override
    public double getReward(State s, Action action, State s_prime) {
        StatePolyVector statePolyVector = new StatePolyVector(s_prime);
        return statePolyVector.dotProduct(polynomialParaMap);
    }
}
