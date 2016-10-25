package com.gas.framwork;

/**
 * Created by Mao on 10/10/2016.
 */
public interface RewardFunc {
    double getReward(State s , Action action , State s_prime);
}