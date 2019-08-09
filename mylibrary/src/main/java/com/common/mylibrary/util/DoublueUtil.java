package com.common.mylibrary.util;

import android.util.Log;

public class DoublueUtil {


    public static double format(double value,int num){
        int n = (int)Math.pow(10,num);
        return (double)Math.round(value*n)/n;
    }
    
  /*  public static void main(String[] arg){
        System.out.print("main: "+format(2589 *  1.0 * 0.6/1000,2));
    }*/

}
