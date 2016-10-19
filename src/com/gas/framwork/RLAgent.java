package com.gas.framwork;

import groovy.ui.SystemOutputInterceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by Mao on 11/10/2016.
 */
abstract public class RLAgent extends Agent {
    private Value value;
    private double discount;
    private double learn_rate;
    private double epsilon;
    public RLAgent(Environment evn, RewardFunc reward, Logger log, Map<String, Action> actions, Value value, double discount, double learn_rate,double epsilon) {
        super(evn, reward, log, actions);
        this.discount = discount;
        this.value = value;
        this.learn_rate = learn_rate;
        this.epsilon = epsilon;
    }

    private List<Action> maxValueActions(State state) {
        Iterator<Action> iterator = actions.values().iterator();
        double maxValue = -100000;
        while (iterator.hasNext()) {
            Action action = iterator.next();
            double qvalue = value.getValue(state, action);
            if (qvalue > maxValue) {
                maxValue = qvalue;
            }
        }
        List<Action> maxValues = new ArrayList<>();
        iterator = actions.values().iterator();
        while (iterator.hasNext()) {
            Action action = iterator.next();
            if (maxValue ==  value.getValue(state,action)) {
                maxValues.add(action);
            }
        }
        return maxValues;
    }

    private Action randMaxValueAction(State state){
        Random random = new Random();
        List<Action> action_list = maxValueActions(state);
        int numOfActions = action_list.size();
        int rand_action_id = random.nextInt(numOfActions);
        return action_list.get(rand_action_id);
    }

    protected Action eMaxValueAction(State state){
        Random random = new Random();
        double rand_01 = random.nextDouble();

        if(rand_01 > epsilon){ // select random from whole action set
            int numOfActions = actions.size();
            int rand_action_id = random.nextInt(numOfActions);
            for(Action action : actions.values()  ){
                if(action.getId() == rand_action_id){
                    return action;
                }
            }
        }else{
            List<Action> action_list = maxValueActions(state);
            int numOfActions = action_list.size();
            int rand_action_id = random.nextInt(numOfActions);
            return action_list.get(rand_action_id);
        }
        return null;
    }
    @Override
    protected void update(State state_before_act,Action action) {
        State s_prime = observe();
        double old_qvalue = value.getValue(state_before_act, action);
        double rw = reward.getReward(state_before_act, action, observe());
        // qlearning update
        double new_qvalue = old_qvalue +
                learn_rate * (rw + discount * value.getValue(s_prime, randMaxValueAction(s_prime)) - old_qvalue);
        value.updateValue(state_before_act,action,new_qvalue);
    }
}
