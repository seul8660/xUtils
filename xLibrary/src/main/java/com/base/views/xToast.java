package com.base.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.utils.xUtils;
import com.base.xBase;
import com.example.base.R;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import static com.base.utils.xUtils.value2dp;


/**
 * 自定义Toast
 */
public class xToast {


    protected static Toast toast   = null;

    private static Context context= xBase.getContext();

    private static long lastTime=0;

    private static String oldMsg = "";

    private static long showTime = 1500;



    public static void normal(@NonNull String text){

        long curTime = System.currentTimeMillis();

        if(text.equals(oldMsg)){
            if(curTime-lastTime>2000){

                lastTime=curTime;

                showMyToast();

            }

        }else{

            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setBackgroundResource(R.drawable.xtoast_bg);
            toastView.setPadding(value2dp(5),value2dp(5),value2dp(5),value2dp(5));
            TextView message = ((TextView) toastView.findViewById(android.R.id.message));

            message.setTextColor(Color.parseColor("#ffffff"));


            oldMsg = text;

            lastTime=curTime;

            showMyToast();
        }


    }

    public static void success(@NonNull String text){


        long curTime = System.currentTimeMillis();

        if(text.equals(oldMsg)){
            if(curTime-lastTime>2000){

                lastTime=curTime;

                showMyToast();

            }

        }else{

            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setBackgroundResource(R.drawable.xtoast_bg);
            toastView.setPadding(value2dp(5),value2dp(5),value2dp(5),value2dp(5));
            TextView message = ((TextView) toastView.findViewById(android.R.id.message));
            message.setTextColor(Color.parseColor("#ffffff"));

            ImageView imageCodeProject = new ImageView(context);
            imageCodeProject.setImageResource(R.drawable.ic_check_circle_white_24dp);
            imageCodeProject.setPadding(0, value2dp(15),0, value2dp(5));
            toastView.addView(imageCodeProject, 0);

            oldMsg = text;

            lastTime=curTime;

            showMyToast();
        }





    }

    public static void error(@NonNull String text){


        long curTime = System.currentTimeMillis();

        if(text.equals(oldMsg)){
            if(curTime-lastTime>2000){

                lastTime=curTime;

                showMyToast();

            }

        }else{

            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setBackgroundResource(R.drawable.xtoast_bg);
            toastView.setPadding(value2dp(5),value2dp(5),value2dp(5),value2dp(5));
            TextView message = ((TextView) toastView.findViewById(android.R.id.message));
            message.setTextColor(Color.parseColor("#ffffff"));

            ImageView imageCodeProject = new ImageView(context);
            imageCodeProject.setImageResource(R.drawable.ic_clear_white_24dp);
            imageCodeProject.setPadding(0, value2dp(15),0, value2dp(5));
            toastView.addView(imageCodeProject, 0);

            oldMsg = text;

            lastTime=curTime;

            showMyToast();
        }



    }

    public static void worm(@NonNull String text){


        long curTime = System.currentTimeMillis();

        if(text.equals(oldMsg)){
            if(curTime-lastTime>2000){

                lastTime=curTime;

                showMyToast();

            }

        }else{

            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setBackgroundResource(R.drawable.xtoast_bg);
            toastView.setPadding(value2dp(5),value2dp(5),value2dp(5),value2dp(5));
            TextView message = ((TextView) toastView.findViewById(android.R.id.message));
            message.setTextColor(Color.parseColor("#ffffff"));

            ImageView imageCodeProject = new ImageView(context);
            imageCodeProject.setImageResource(R.drawable.ic_error_outline_white_24dp);
            imageCodeProject.setPadding(0, value2dp(15),0, value2dp(5));
            toastView.addView(imageCodeProject, 0);

            oldMsg = text;

            lastTime=curTime;

            showMyToast();
        }


    }



    private static void showMyToast() {
        final Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(toast!=null){

                    //hook(toast);/*反射处理crash问题*/
                    toast.show();
                }

            }
        },0,5000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if(toast!=null){
                    toast.cancel();
                    timer.cancel();
                   // toast = null;
                }

            }
        }, showTime );
    }



//
//    /*处理7.1.0-7.1.2 toast crash 问题*/
//    private static Field sField_TN;
//    private static Field sField_TN_Handler;
//    static {
//        try {
//            sField_TN = Toast.class.getDeclaredField("mTN");
//            sField_TN.setAccessible(true);
//            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
//            sField_TN_Handler.setAccessible(true);
//        } catch (Exception e) {
//        }
//    }
//
//    private static void hook(Toast toast) {
//        try {
//            Object tn = sField_TN.get(toast);
//            Handler preHandler = (Handler) sField_TN_Handler.get(tn);
//            sField_TN_Handler.set(tn, new SafelyHandlerWarpper(preHandler));
//        } catch (Exception e) {
//        }
//    }
//
//
//    public static class SafelyHandlerWarpper extends Handler {
//        private Handler impl;
//
//        public SafelyHandlerWarpper(Handler impl) {
//            this.impl = impl;
//        }
//
//        @Override
//        public void dispatchMessage(Message msg) {
//            try {
//                super.dispatchMessage(msg);
//            } catch (Exception e) {
//            }
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            impl.handleMessage(msg);//需要委托给原Handler执行
//        }
//    }

}
