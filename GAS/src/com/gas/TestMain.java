package com.gas;

import com.gas.framwork.*;
import com.gas.inverse.gridworld.*;
import com.gas.inverse.gridworld.StatePolyVector;
import com.gas.inverse.gridworld.actions.GoDownAction;
import com.gas.inverse.gridworld.actions.GoLeftAction;
import com.gas.inverse.gridworld.actions.GoRightAction;
import com.gas.inverse.gridworld.actions.GoUpAction;

import java.util.*;

/**
 * Created by Mao on 12/10/2016.
 */
public class TestMain {

    private String reinforcementLearning(String rewardVector){
        Map<String,Double> para = new HashMap<String,Double>();
        //需要一个  字符串——>map  个解析器 java Python两边实现，通过字符串传参数。
        para.put("x",  0.1);
        para.put("x2", 0.1);
        para.put("x3", 0.1);
        para.put("y",  0.1);
        para.put("y2", 0.1);
        para.put("y3", 0.1);
        para.put("xy", 0.5);
        para.put("x2y",0.5);
        para.put("xy2",0.5);
        RewardFunc reward = new PolyRewardFunc(para);

        Environment env = new GridEnvironment();
        Logger log = new GridLogger("GridLogger");
        Map<String,Action> actions = new HashMap<String,Action>();
        actions.put("left" ,  new GoLeftAction  ("left",0));
        actions.put("right" , new GoRightAction("right",1));
        actions.put("up" ,    new GoUpAction      ("up",2));
        actions.put("down" ,  new GoDownAction  ("down",3));

        GridValue value = new GridValue();
        double discount = 0.8;
        double learn_rate = 0.1;
        double epsilon = 0.8;
        GridRLAgent agent_rl = new GridRLAgent(env, reward, log, actions, value, discount,learn_rate,epsilon);
        agent_rl.initialize();
        List<List<State>> runHistory = agent_rl.runTrajectory(10000);
        GridAnalyser analyser = new GridAnalyser(discount);
        StatePolyVector rv = analyser.preprocessing(runHistory);

        //value.printQV();
        return rv.toString();
    }

    public String expert (){
        RewardFunc reward = new GridRewardFunc();
        Logger log = new GridLogger("GridLogger");
        Environment env = new GridEnvironment();
        Map<String,Action> actions = new HashMap<String,Action>();
        actions.put("left" ,  new GoLeftAction  ("left",0));
        actions.put("right" , new GoRightAction("right",1));
        actions.put("up" ,    new GoUpAction      ("up",2));
        actions.put("down" ,  new GoDownAction  ("down",3));
        ExpertAgent expertAgent = new ExpertAgent(env,reward,log,actions);


        expertAgent.initialize();
        List<List<State>> runHistory = expertAgent.runTrajectory(300);
        GridAnalyser analyser = new GridAnalyser(0.8);
        StatePolyVector rv = analyser.preprocessing(runHistory);
        //rv.print();
        return rv.toString();
    }
    public static void main(String[] args){
        TestMain test = new TestMain();
        test.reinforcementLearning("");
    }
}
