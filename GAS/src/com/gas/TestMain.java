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
    private Map<String,Double> paraStr2map(String paraStr){
        Map<String,Double> paraMap = new HashMap<String,Double>();
        paraStr = paraStr.replaceAll("><",",");
        paraStr = paraStr.replaceAll(">","");
        paraStr = paraStr.replaceAll("<","");
        StringTokenizer st=new StringTokenizer(paraStr, ",");
        while(st.hasMoreTokens()){

            String paraPair = st.nextToken();
            String key = paraPair.substring(0,paraPair.indexOf(":"));
            Double value = Double.parseDouble( paraPair.substring(paraPair.indexOf(":") +1,paraPair.length()) );
            paraMap.put(key,value);
        }
        return paraMap;
    }

    private String reinforcementLearning(String rewardVector){
        Map<String,Double> para = paraStr2map(rewardVector);
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
        List<List<State>> runHistory = agent_rl.runTrajectory(100000);
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
        List<List<State>> runHistory = expertAgent.runTrajectory(100000);
        GridAnalyser analyser = new GridAnalyser(0.8);
        StatePolyVector rv = analyser.preprocessing(runHistory);
        return rv.toString();
    }
    public static void main(String[] args){
        TestMain mainFunc = new TestMain();
        if(args[0].equals("expert")){
            String rs = mainFunc.expert();
            System.out.println(rs);
        }else if(args[0].equals("RL")){
            String rs = mainFunc.reinforcementLearning(args[1]);
            System.out.println(rs);
        }else {
            System.out.println(" Error! input expert or RL");
        }
    }
}
