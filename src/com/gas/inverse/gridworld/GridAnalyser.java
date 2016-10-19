package com.gas.inverse.gridworld;

import com.gas.framwork.State;

import java.util.*;

/**
 * Created by Mao on 17/10/2016.
 */
public class GridAnalyser {
    double discount;

    public GridAnalyser(double discount){
        this.discount = discount;
    }
    public RewardVector preprocessing(List<List<State>> history){

        Iterator<List<State>> iterator = history.iterator();
        RewardVector rv_sum = new RewardVector(new GridState(0,0));
        while(iterator.hasNext()){
            List<State> traj= iterator.next();
            Collections.reverse(traj);
            Iterator<State> it_traj = traj.iterator();
            RewardVector rv = new RewardVector(new GridState(0,0));
            while(it_traj.hasNext()){
                rv.times(discount);
                rv.add(  new RewardVector( it_traj.next() )   );
            }
            rv_sum.add(rv);
        }

        rv_sum.times(  1.0/history.size()  );
        return rv_sum;
    }
    private Map<String,Double> rewardVector(State s){
        double x = s.getFeature("x");
        double y = s.getFeature("y");
        Map<String,Double> map = new HashMap<String,Double>();
        map.put("x",x);
        map.put("x2",x*x);
        map.put("x3",x*x*x);
        map.put("y",y);
        map.put("y2",y*y);
        map.put("y3",y*y*y);
        map.put("xy",x*y);
        map.put("x2y",x*x*y);
        map.put("xy2",x*y*y);

        return map;
    }
}
