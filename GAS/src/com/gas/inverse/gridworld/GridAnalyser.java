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
    public StatePolyVector preprocessing(List<List<State>> history){

        Iterator<List<State>> iterator = history.iterator();
        StatePolyVector rv_sum = new StatePolyVector(new GridState(0,0));
        while(iterator.hasNext()){
            List<State> traj= iterator.next();
            Collections.reverse(traj);
            Iterator<State> it_traj = traj.iterator();
            StatePolyVector rv = new StatePolyVector(new GridState(0,0));
            while(it_traj.hasNext()){
                rv.times(discount);
                rv.add(  new StatePolyVector( it_traj.next() )   );
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

        map.put("x",x);         // ---1
        map.put("x2",x*x);      // ---2
        map.put("x3",x*x*x);    // ---3
        map.put("y",y);         // ---4
        map.put("y2",y*y);      // ---5
        map.put("y3",y*y*y);    // ---6
        map.put("xy",x*y);      // ---7
        map.put("x2y",x*x*y);   // ---8
        map.put("xy2",x*y*y);   // ---9

        return map;
    }
}
