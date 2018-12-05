package com.base.views;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.base.R;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.base.utils.xUtils.convertToInt;
import static com.base.utils.xUtils.getWindowHeight;
import static com.base.utils.xUtils.setMargins;
import static com.zhl.cbdialog.CBDialogBuilder.DIALOG_STYLE_NORMAL;

public class xDialog2 {

    private static xDialog2 dialog2 = null;

    private static Map<Activity,xDialog2> dialogs = new HashMap<>();

    public static xDialog2 getInstance(Activity activity){

        if(dialogs.containsKey(activity)){

            dialogs.get(activity);

        }

        dialog2 = new xDialog2();
        dialog2.mContext = activity;

        return dialog2;
    }


    private Activity mContext;
    private CBDialogBuilder builder;




    private float alpha = 1.0f,width = 0.8f,height = 0f;
    private int layoutStyle = DIALOG_STYLE_NORMAL;
    private Boolean  isSystemAlert = false,dimEnable = false;
    private Dialog dialog = null;
    private Boolean isDismiss = true;
    private CBDialogBuilder.onDialogbtnClickListener clickListener;
    public xDialog2 create(){

        builder = new CBDialogBuilder(mContext,layoutStyle,isSystemAlert,width,alpha,dimEnable);


        if(clickListener==null) clickListener = new CBDialogBuilder.onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int i) {

            }
        };

        builder.setButtonClickListener(isDismiss,clickListener);


        if(title.equals("")){

            builder.setTitle(null);
        }

        if(cancelBtn.equals("")){
            builder.showCancelButton(false);
        }
        else{
            builder.showCancelButton(true);
            builder.setCancelButtonText(cancelBtn);
        }

        if(confirmBtn.equals("")){
            builder.showConfirmButton(false);
        }
        else{

            builder.showConfirmButton(true);
            builder.setConfirmButtonText(confirmBtn);
        }

        if(icon==0){
            builder.showIcon(false);
        }
        else{
            builder.showIcon(true);

            builder.setCustomIcon(icon);

        }

        dialog = builder.create();

        if(height!=0f){
            Window window = dialog.getWindow();

            if(window!=null){
                window.getAttributes().height = convertToInt(this.height*getWindowHeight());
                window.setAttributes(window.getAttributes());

            }


        }

        return dialog2;
    }

    private String title = "";
    public xDialog2 setTitle(String text){
        this.title =text;
        return dialog2;
    }

    private String cancelBtn = "取消";
    public xDialog2 setCancelBtn(String text){
        this.cancelBtn =text;
        return dialog2;
    }

    private String confirmBtn = "确认";
    public xDialog2 setConfirmBtn(String text){
        this.confirmBtn =text;
        return dialog2;
    }

    private int icon = 0;
    public xDialog2 setIcon(int icon){
        this.icon =icon;
        return dialog2;
    }

    public xDialog2 setAlpha(float alpha){
        this.alpha =alpha;
        return dialog2;
    }

    public xDialog2 setWidth(float width){
        this.width =width;
        return dialog2;
    }

    public xDialog2 setHeight(float height){
        this.height =height;
        return dialog2;
    }

    public xDialog2 setClickDismiss(Boolean b){
        this.isDismiss = b;

        return dialog2;
    }

    public xDialog2 setOnBtnClick(CBDialogBuilder.onDialogbtnClickListener onBtnClick){

        this.clickListener = onBtnClick;

        return dialog2;

    }



    public void showView(View view){


            setTitle("");
            setCancelBtn("");
            setConfirmBtn("");

            create();

            ViewGroup viewGroup = dialog2.builder.getView(R.id.dialog_msg_layout);

            viewGroup.removeAllViews();

            if(view.getParent()!=null){
                ViewGroup group = (ViewGroup) view.getParent();
                group.removeView(view);
            }
            dialog2.builder.setView(view);


            show();


    }


    public void show(){

        try{
            dialog.show();
        }catch (Exception e){

        }
    }

}
