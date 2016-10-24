package com.gas;

import com.gas.framwork.*;
import com.gas.inverse.gridworld.*;
import com.gas.inverse.gridworld.actions.GoDownAction;
import com.gas.inverse.gridworld.actions.GoLeftAction;
import com.gas.inverse.gridworld.actions.GoRightAction;
import com.gas.inverse.gridworld.actions.GoUpAction;

import java.util.*;

/**
 * Created by Mao on 12/10/2016.
 */
public class TestMain {

    public static void main(String[] args){
        RewardFunc reward = new GridRewardFunc();
        Logger log = new GridLogger("GridLogger");
        Environment env = new GridEnvironment();
        Map<String,Action> actions = new HashMap<String,Action>();
        actions.put("left" ,  new GoLeftAction  ("left",0));
        actions.put("right" , new GoRightAction("right",1));
        actions.put("up" ,    new GoUpAction      ("up",2));
        actions.put("down" ,  new GoDownAction  ("down",3));
        ExpertAgent expertAgent = new ExpertAgent(env,reward,log,actions);

        /*
        expertAgent.initialize();
        List<List<State>> runHistory = expertAgent.runTrajectory(300);
        GridAnalyser analyser = new GridAnalyser(0.8);
        RewardVector rv = analyser.preprocessing(runHistory);
        rv.print();
        */

        GridValue value = new GridValue();
        double discount = 0.8;
        double learn_rate = 0.1;
        double epsilon = 0.8;
        GridRLAgent agent_rl = new GridRLAgent(env, reward, log, actions, value, discount,learn_rate,epsilon);
        agent_rl.initialize();
        agent_rl.runTrajectory(1000000);

        value.printQV();


    }
}
