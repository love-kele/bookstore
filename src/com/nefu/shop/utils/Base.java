package com.nefu.shop.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Base {


    /**
     * 将user对象转换成obj[]    2018/7/5  user专用 因为少一个字段id
     */
    public static Object[] setUserParams(Object obj) {

        final Class<?> type = obj.getClass();

        Field[] declaredFields = type.getDeclaredFields();

        Method[] declaredMethods = type.getDeclaredMethods();

        Object[] obj1 = new Object[declaredFields.length - 1];
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < declaredFields.length; i++) {
            String fieldname = declaredFields[i].getName();
            map.put(fieldname.toLowerCase(), i);
        }

        for (int i = 0; i < declaredMethods.length; i++) {
            String mname = declaredMethods[i].getName();
            Integer index = 0;

            if (mname.startsWith("get")) {
                index = map.get(mname.substring(3).toLowerCase());
                Object invoke = null;

                try {
                    invoke = declaredMethods[i].invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (index != null && index - 1 >= 0)
                    obj1[index - 1] = invoke;
            }

        }

        return obj1;
    }

    /**
     * 将order对象转换成obj[]
     * @param obj
     * @return
     */
    public static Object[] setParams(Object obj) {

        final Class<?> type = obj.getClass();

        Field[] declaredFields = type.getDeclaredFields();

        Method[] declaredMethods = type.getDeclaredMethods();

        Object[] obj1 = new Object[declaredFields.length];
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < declaredFields.length; i++) {
            String fieldname = declaredFields[i].getName();
            map.put(fieldname.toLowerCase(), i);
        }

        for (int i = 0; i < declaredMethods.length; i++) {
            String mname = declaredMethods[i].getName();
            Integer index = 0;

            if (mname.startsWith("get")) {
                index = map.get(mname.substring(3).toLowerCase());
                Object invoke = null;

                try {
                    invoke = declaredMethods[i].invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
               if (index != null )
                    obj1[index] = invoke;
            }

        }

        return obj1;
    }


}
