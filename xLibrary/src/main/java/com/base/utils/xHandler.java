package com.base.utils;


import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class xHandler {

    private static xHandler xHandler = new xHandler();
    private static Map<Object,List<Runnable>> allTasks = new HashMap<>();
    private Handler handler = new Handler();



    public static void putTask(Context context, int delay,final CallBack callBack){


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callBack.onBack();
            }
        };


        if(!allTasks.containsKey(context)) {

            allTasks.put(context,new ArrayList<Runnable>());

        }

        allTasks.get(context).add(runnable);
        xHandler.handler.postDelayed(runnable,delay);

    }

    public static void putTask(int delay,final int loop,String key,final CallBack callBack){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                xHandler.handler.postDelayed(this,loop);
                callBack.onBack();

            }
        };

        if(!allTasks.containsKey(key)) allTasks.put(key,new ArrayList<Runnable>());

        allTasks.get(key).add(runnable);
        xHandler.handler.postDelayed(runnable,delay);

    }


    public static void putTask(int delay,final int loop,final CallBack callBack){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                xHandler.handler.postDelayed(this,loop);
                callBack.onBack();
            }
        };

        xHandler.handler.postDelayed(runnable,delay);

    }


    public static void clearTask(Context context){

        if(allTasks.get(context)!=null){

            for(Runnable runnable:allTasks.get(context)){

                xHandler.handler.removeCallbacks(runnable);

            }

            allTasks.remove(context);

        }




    }

    public static void clearTask(String key){

        if(allTasks.get(key)!=null) {

            for (Runnable runnable : allTasks.get(key)) {

                xHandler.handler.removeCallbacks(runnable);

            }

            allTasks.remove(key);

        }

    }


    public static void clearTask(){

        Iterator iterator = allTasks.keySet().iterator();

        while (iterator.hasNext()) {
            Object key = iterator.next();

            if(key instanceof Context){

                for(Runnable runnable:allTasks.get(key)){

                    xHandler.handler.removeCallbacks(runnable);

                }

                iterator.remove();
                allTasks.remove(key);
            }

        }

    }

    public static void clearAllTask(){

        Iterator iterator = allTasks.keySet().iterator();

        while (iterator.hasNext()) {
            Object key = iterator.next();

             for(Runnable runnable:allTasks.get(key)){

             xHandler.handler.removeCallbacks(runnable);

             iterator.remove();

             allTasks.remove(key);
            }

        }

    }

    public static void putTask(int delay,final CallBack callBack) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                callBack.onBack();

            }
        };

        xHandler.handler.postDelayed(runnable,delay);

    }


    public interface CallBack{

        void onBack();


    }


}
