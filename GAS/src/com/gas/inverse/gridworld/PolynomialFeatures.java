package com.gas.inverse.gridworld;

import sun.awt.image.ImageWatched;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.lang.Math;

/**
 * Created by Mao on 01/11/2016.
 */
public class PolynomialFeatures {
    private  Map<String,Double> features;
    private  Map<String,Double> polynomial;  // use to return
    private  int degree;
    private  List<String> list;
    public PolynomialFeatures(Map<String,Double> features, Map<String,Double> polynomial){
        this.features = features;
        this.polynomial = polynomial;
        list = new LinkedList<String>();
        list.addAll(features.keySet());
    }

    public String strFix(String str){
        char[] chList = str.toCharArray();
        if(str.length()==1){
            return str;
        }
        int i=0;
        while (i<str.length() && chList[i]>='0'&& chList[i]<='9'){
            i++;
        }
        if(str.substring(i).length()==0){
            return "1";
        }
        return str.substring(i);
    }
    public void fit(int degree){
        this.degree = degree;
        for(int i = 0; i < degree + 1; i++) {
            fit_transform(degree, "1", 1, i, 0);
         //   System.out.println("===============================");   //~~~~~~~~~~~~~~~
        }
    }
    public void fit_transform(int left_degree,String polyItem,double value,int power,int index){
        // ---------------  End condition -----------------------
        if( left_degree < 0 || index == list.size() ){
            return;
        }
        //------------------  visit()  --------------------------
        String nodeName = list.get(index);
        double nodeValue = features.get(nodeName);
        //System.out.println("nodeName = " + nodeName + "    |    index = " + index);  //~~~~~~~~~~~~~~~
        //System.out.println("--------------");
        String nodePolyItem;

        //nodePolyItem = polyItem + nodeName + power;

        if(power == 0){
            nodePolyItem = "1"+polyItem;
        }else{
            nodePolyItem = polyItem + nodeName + power;
        }

        double pathValue = value * Math.pow(nodeValue,power);

        if(index == list.size()-1){
            nodePolyItem = strFix(nodePolyItem);
            polynomial.put(nodePolyItem, pathValue);
        }
        //System.out.println("nodeName="+nodeName+"   nodeValue"+nodeValue
         //       +"   nodePolyItem="+nodePolyItem+"    pathValue="+pathValue +"  pow:"+power +" LD:"+left_degree);
        //------------------  recursion call itself --------------------------
        for(int i = 0; i < degree + 1; i++){
         //   System.out.println("   CALL:"+(left_degree-i) +" | nodePolyItem:"+ nodePolyItem+" | pathValue:"+pathValue+" | pow:"+i+" | index:"+(index+1));
            fit_transform(left_degree - i, nodePolyItem,pathValue,i,index+1);
        }
    }
}
