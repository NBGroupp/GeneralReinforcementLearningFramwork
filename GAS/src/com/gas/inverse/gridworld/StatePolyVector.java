package com.gas.inverse.gridworld;

import com.gas.framwork.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mao on 17/10/2016.
 */
public class StatePolyVector {
    Map<String,Double> map ;

    public void print(){
        System.out.println(this.toString());
    }
    public  String toString(){
        StringBuffer sb = new StringBuffer();
        for(String key : map.keySet()){
            sb.append("<"+key+":"+this.getFeature(key)+">");
        }
        return sb.toString();
    }
    public StatePolyVector(State s) {
        double x = s.getFeature("x");
        double y = s.getFeature("y");
        map = new HashMap<String, Double>();
        map.put("x", x);
        map.put("x2", x * x);
        map.put("x3", x * x * x);
        map.put("y", y);
        map.put("y2", y * y);
        map.put("y3", y * y * y);
        map.put("xy", x * y);
        map.put("x2y", x * x * y);
        map.put("xy2", x * y * y);
    }
    private double getFeature(String str){
        return map.get(str);
    }
    public void times(double r){
        for(Map.Entry<String,Double> entry : map.entrySet()){
            double result = entry.getValue()*r;
            map.put(entry.getKey(),result);
        }
    }
    public double dotProduct(Map<String,Double> polyPara){
        double result = 0;
        for(String key : map.keySet()){
            result = result + polyPara.get(key) * this.getFeature(key);
        }
        return result;
    }
    public void add(StatePolyVector vector){
        for(String key : map.keySet()){
            double result = vector.getFeature(key) + this.getFeature(key);
            map.put(key,result);
        }
    }
}
