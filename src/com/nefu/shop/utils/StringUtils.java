package com.nefu.shop.utils;

public class StringUtils {

    public  static boolean isEmpty(String s){
        if(s==null)
            return true;
        if(s.isEmpty())
            return true;

     return false;
    }
}
