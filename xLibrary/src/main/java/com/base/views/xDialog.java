package com.base.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.utils.xHandler;
import com.base.utils.xUtils;
import com.base.utils.xViewHelper;
import com.example.base.R;
import com.zhl.cbdialog.CBDialogBuilder;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.base.utils.xUtils.convertToStr;
import static com.base.utils.xUtils.setMargins;
import static com.base.utils.xUtils.value2dp;

public class xDialog extends CBDialogBuilder{

    private static xDialog cbDialogBuilder;
    public static int ICON_SUCCESS = R.drawable.icon_dialog_success;
    public static int ICON_ERROR = R.drawable.icon_dialog_error;
    public static int ICON_INFO = R.drawable.icon_dialog_msg;
    public Dialog dialog;
    private float alpha = 1.0f;
    private onDialogbtnClickListener listener;
    private Boolean isDismiss = true;
    private static Context mContext;

    private xDialog(Context context) {
        super(context);
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }

    private xDialog(Context context, int layoutStyle, float widthcoefficient, boolean dimEnable) {

        super(context, layoutStyle, false, widthcoefficient, 1.0F, dimEnable);
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }


    public static xDialog getInstance(Activity activity,onDialogbtnClickListener listener){

            mContext = activity;
            cbDialogBuilder = new xDialog(activity,DIALOG_STYLE_NORMAL,0.8f,true);
            cbDialogBuilder.listener = listener;
            cbDialogBuilder
                    .setDialogAnimation(R.style.dialog_anim_style)
                    .showIcon(false)
                    .setTitle(null)
                    .setConfirmButtonText("确定")
                    .showCancelButton(true)
                    .setCancelButtonText("取消")
                    .setButtonClickListener(cbDialogBuilder.isDismiss,listener);

        TextView view =cbDialogBuilder.getView(com.zhl.cbdialog.R.id.dialog_title);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        setMargins(view, xUtils.value2dp(10),xUtils.value2dp(10),xUtils.value2dp(10),xUtils.value2dp(10));
        return cbDialogBuilder;
    }


    public static xDialog getInstance(Activity activity,float width){

        mContext = activity;

        cbDialogBuilder = new xDialog(activity,DIALOG_STYLE_NORMAL,width,true);

        cbDialogBuilder.listener = new onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int i) {

            }
        };
        cbDialogBuilder
                .setDialogAnimation(R.style.dialog_anim_style)
                .showIcon(false)
                .setTitle(null)
                .setConfirmButtonText("确定")
                .showCancelButton(true)
                .setCancelButtonText("取消")
                .setButtonClickListener(cbDialogBuilder.isDismiss,cbDialogBuilder.listener);

        TextView view =cbDialogBuilder.getView(com.zhl.cbdialog.R.id.dialog_title);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        setMargins(view,xUtils.value2dp(10),xUtils.value2dp(10),xUtils.value2dp(10),xUtils.value2dp(10));
        return cbDialogBuilder;


    }

    public static xDialog getInstance(Activity activity){

        mContext = activity;

        cbDialogBuilder = new xDialog(activity,DIALOG_STYLE_NORMAL,0.8f,true);
        cbDialogBuilder.listener = new onDialogbtnClickListener() {
            @Override
            public void onDialogbtnClick(Context context, Dialog dialog, int i) {

            }
        };
        cbDialogBuilder
                .setDialogAnimation(R.style.dialog_anim_style)
                .showIcon(false)
                .setTitle(null)
                .setConfirmButtonText("确定")
                .showCancelButton(true)
                .setCancelButtonText("取消")
                .setButtonClickListener(true,cbDialogBuilder.listener);

        TextView view =cbDialogBuilder.getView(com.zhl.cbdialog.R.id.dialog_title);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        setMargins(view,xUtils.value2dp(10),xUtils.value2dp(10),xUtils.value2dp(10),xUtils.value2dp(10));
        return cbDialogBuilder;
    }

    public xDialog setDismiss(Boolean dismiss){
        cbDialogBuilder.isDismiss = dismiss;
        cbDialogBuilder.setButtonClickListener(dismiss,listener);
        return cbDialogBuilder;
    }

    public void setAmin(int Res){
        cbDialogBuilder.setDialogAnimation(Res);

    }

    public void showWarn(String msg){
        cbDialogBuilder.showIcon(false);
        TextView textView = cbDialogBuilder.getView(R.id.dialog_message);
        textView.setPadding(value2dp(10),value2dp(20),value2dp(10),value2dp(10));
        cbDialogBuilder.showCancelButton(false);
        cbDialogBuilder.setMessage(msg);
        cbDialogBuilder.setDismiss(true);
        try{
            getDialog().show();
        }catch (Exception e){

        }

    }

    public void show(String msg,int icon){
        cbDialogBuilder.setIcon(icon);
        cbDialogBuilder.setMessage(msg);
        cbDialogBuilder.setDismiss(true);
        try{
            getDialog().show();
        }catch (Exception e){

        }
    }

    public void show(String msg){
        cbDialogBuilder.showIcon(false);
        cbDialogBuilder.setMessage(msg);

        TextView textView = cbDialogBuilder.getView(R.id.dialog_message);
        textView.setPadding(value2dp(10),value2dp(20),value2dp(10),value2dp(10));
        cbDialogBuilder.setDismiss(true);
        try{
            getDialog().show();
        }catch (Exception e){

        }
    }



    public xDialog setBackground(){
        cbDialogBuilder.setDialogBackground(R.color.colourless);
       return cbDialogBuilder;
    }

    public xDialog setAlpha(float alpha){

        cbDialogBuilder.alpha = alpha;

        return cbDialogBuilder;

    }

    private static List<Dialog> dialogList = new ArrayList<>();
    public Dialog getDialog(){
        if(dialog==null) dialog = cbDialogBuilder.create();

        dialogList.add(dialog);

        return dialog;


    }

    public static void dismiss(){

        Iterator<Dialog> it = dialogList.iterator();

        while (it.hasNext()){

            Dialog dialog = it.next();

            try{
                dialog.dismiss();
                dialog.cancel();
            }
            catch (Exception e){

            }

            dialog = null;
            it.remove();
        }

    }

    public void showView(View view){

        if(cbDialogBuilder!=null){
            ViewGroup viewGroup = cbDialogBuilder.getView(R.id.dialog_msg_layout);
            viewGroup.removeAllViews();
            cbDialogBuilder.setDismiss(false);
            if(view.getParent()!=null){
               ViewGroup group = (ViewGroup) view.getParent();
                group.removeView(view);
            }
            cbDialogBuilder.setView(view);
            try{
                getDialog().show();
            }catch (Exception e){

            }

        }

    }











    public xDialog setOnDialogClick(onDialogbtnClickListener listener){

        cbDialogBuilder.setButtonClickListener(cbDialogBuilder.isDismiss,listener);

        return cbDialogBuilder;
    }


    public xDialog setCancelButton(boolean isshow){
        cbDialogBuilder.showCancelButton(isshow);
        return cbDialogBuilder;
    }

    public xDialog setConfirmButton(boolean isshow){
        cbDialogBuilder.showConfirmButton(isshow);
        return cbDialogBuilder;
    }
    public xDialog setTitle(Object object){
        TextView view =getView(com.zhl.cbdialog.R.id.dialog_title);
        if(object!=null){
            view.setVisibility(View.VISIBLE);
            view.setText(convertToStr(object));
        }
        else{
            view.setVisibility(View.GONE);
        }

        return cbDialogBuilder;
    }


    public xDialog setTiTleType(Typeface typeface){
        TextView view =cbDialogBuilder.getView(com.zhl.cbdialog.R.id.dialog_title);
        view.setTypeface(typeface);
        return cbDialogBuilder;
    }

    public xDialog setIcon(int id){
        cbDialogBuilder.setCustomIcon(id);
        cbDialogBuilder.showIcon(true);
        ImageView view = cbDialogBuilder.getView(com.zhl.cbdialog.R.id.custom_icon);
        setMargins(view,20,20,20,20);
        view.setImageResource(id);
        return cbDialogBuilder;

    }

    public xDialog setOutSideClickDismiss(Boolean dismiss){
        cbDialogBuilder.setCancelable(false);
        cbDialogBuilder.setTouchOutSideCancelable(dismiss);

        return cbDialogBuilder;
    }


    public xDialog setBackground(int color){
        View rootView = cbDialogBuilder.getView(com.zhl.cbdialog.R.id.cb_dialog_root_layout);
        rootView.setBackgroundColor(color);
        return cbDialogBuilder;
    }



    public xDialog setConfirmText(String text){

        cbDialogBuilder.setConfirmButtonText(text);

        return cbDialogBuilder;
    }


    public xDialog setCancelText(String text){

        cbDialogBuilder.setCancelButtonText(text);

        return cbDialogBuilder;
    }



    private static List<Dialog> dialogs =new ArrayList<>();
    public void showLoading(String msg){

        disLoading();

        xViewHelper helper = new xViewHelper(mContext,R.layout.loading_view);
        TextView msgText = helper.getView(R.id.text);
        if(msg.equals("")) msgText.setVisibility(View.GONE);
        else msgText.setText(msg);
        if(cbDialogBuilder!=null){
            ViewGroup viewGroup = cbDialogBuilder.getView(R.id.dialog_msg_layout);
            viewGroup.removeAllViews();
            cbDialogBuilder.setConfirmButton(false).setOutSideClickDismiss(false).setCancelButton(false).setBackground(mContext.getResources().getColor(R.color.colourless)).setDismiss(false);
            if(helper.getConvertView().getParent()!=null){
                ViewGroup group = (ViewGroup) helper.getConvertView().getParent();
                group.removeView(helper.getConvertView());
            }
            cbDialogBuilder.setView(helper.getConvertView());

            final Dialog dialog =  cbDialogBuilder.create();

            try {
                dialog.getWindow().setDimAmount(0f);
                dialog.show();
                dialogs.add(dialog);
                xHandler.putTask(15000, new xHandler.CallBack() {
                    @Override
                    public void onBack() {
                        dialog.dismiss();
                    }
                });
            }catch (Exception e){



            }






        }

    }

    public static void disLoading(){

        Iterator<Dialog> it = dialogs.iterator();

        while (it.hasNext()){

            Dialog dialog =  it.next();

            try{
                dialog.dismiss();
                dialog.cancel();
            }catch (Exception e){}


            dialog = null;

            it.remove();

        }

    }



}
