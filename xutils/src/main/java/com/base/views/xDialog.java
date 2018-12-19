package com.base.views;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.base.utils.xHandler;
import com.base.utils.xUtils;
import com.base.utils.xViewHelper;
import com.example.base.R;
import com.orhanobut.logger.Logger;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.base.utils.xUtils.convertToInt;
import static com.base.utils.xUtils.getWindowHeight;
import static com.base.utils.xUtils.setMargins;
import static com.base.utils.xUtils.value2dp;
import static com.zhl.cbdialog.CBDialogBuilder.DIALOG_STYLE_NORMAL;

public class xDialog {

    private static xDialog xDialog = null;
    private Activity mContext;
    private CBDialogBuilder builder;

    public static xDialog getInstance(Activity activity){


       // if(xDialog!=null&&xDialog.dialog!=null) xDialog.dialog.dismiss();

        xDialog = new xDialog();
        xDialog.mContext = activity;

        return xDialog;
    }

    public static void dismiss(){

        if(curDialog!=null&&curDialog.dialog!=null&&!curDialog.mContext.isFinishing()) curDialog.dialog.dismiss();

    }


    private static xDialog curDialog;
    public xDialog create(){

        builder = new CBDialogBuilder(mContext,layoutStyle,isSystemAlert,width,alpha,dimEnable);

        if(clickListener==null) {
            clickListener = new CBDialogBuilder.onDialogbtnClickListener() {
                @Override
                public void onDialogbtnClick(Context context, Dialog dialog, int i) {

                }
            };
        }

        builder.setButtonClickListener(isDismiss,clickListener);


        if(title.equals("")){

            builder.setTitle(null);
        }
        else{

            TextView view =builder.getView(com.zhl.cbdialog.R.id.dialog_title);
            view.setTypeface(titleTypeface);
            builder.setTitle(title);
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

        if(!text.equals("")){
            TextView textView = builder.getView(R.id.dialog_message);
            textView.setText(text);
        }


        if(view!=null){

            ViewGroup viewGroup = builder.getView(R.id.dialog_msg_layout);
            viewGroup.removeAllViews();
            if(view.getParent()!=null){
                ViewGroup group = (ViewGroup) view.getParent();
                group.removeView(view);
            }

            builder.setView(view);
        }

        /*设置无动画*/
        builder.setDialogAnimation(R.style.dialog_anim_style);


        if(!outSideClickDismiss){

            builder.setCancelable(false);
            builder.setTouchOutSideCancelable(false);

        }


        dialog = builder.create();

        if(height!=0f){
            Window window = dialog.getWindow();

            if(window!=null){
                window.getAttributes().height = convertToInt(height*getWindowHeight());
                window.setAttributes(window.getAttributes());

            }

        }

        return xDialog;
    }

    public void show(){

        /*防止忘记create*/
        if(dialog==null){
            create();
        }

        if(curDialog!=null) curDialog.dialog.dismiss();

        curDialog = xDialog;

        try{
            dialog.show();
        }catch (Exception e){
            Logger.d(e.toString());
        }
    }

    private float alpha = 1.0f,width = 0.9f,height = 0f;
    private int layoutStyle = DIALOG_STYLE_NORMAL;
    private Boolean  isSystemAlert = false,dimEnable = true;
    private Dialog dialog = null;
    private Boolean isDismiss = true;
    private CBDialogBuilder.onDialogbtnClickListener clickListener;

   /*dialog 标题头*/
    private String title = "";
    public xDialog setTitle(String text){
        this.title =text;
        return xDialog;
    }

    /*dialog 取消按钮*/
    private String cancelBtn = "取消";
    public xDialog setCancelBtn(String text){
        this.cancelBtn =text;
        return xDialog;
    }

    /*dialog 确认按钮*/
    private String confirmBtn = "确定";
    public xDialog setConfirmBtn(String text){
        this.confirmBtn =text;
        return xDialog;
    }

    /*dialog 是否有图标*/
    private int icon = 0;
    public xDialog setIcon(int icon){
        this.icon =icon;
        return xDialog;
    }

    /*dialog 透明度 默认1.0f*/
    public xDialog setAlpha(float alpha){
        this.alpha =alpha;
        return xDialog;
    }

    /*dialog 宽度占屏百分比 默认0.9f*/
    public xDialog setWidth(float width){
        this.width =width;
        return xDialog;
    }

    /*dialog 高度占屏百分比 */
    public xDialog setHeight(float height){
        this.height = height;

        return xDialog;
    }

    /*dialog 按钮是否直接关闭dialog */
    public xDialog setBtnClickDismiss(Boolean b){
        this.isDismiss = b;

        return xDialog;
    }

    /*dialog 按钮监听 */
    public xDialog setOnBtnClick(CBDialogBuilder.onDialogbtnClickListener onBtnClick){

        this.clickListener = onBtnClick;

        return xDialog;

    }

    /*dialog msg */
    private String text="";
    public xDialog setText(String text){

        this.text = text;

        return xDialog;
    }

    /*dialog view */
    private View view ;
    public xDialog setView(View view){

        this.view =view;

        setBtnClickDismiss(false);


        return xDialog;
    }

    /*dialog 点击外部关闭 默认false */
    private boolean outSideClickDismiss = false;
    public xDialog setOutSideClickDismiss(boolean outSideClickDismiss) {
        this.outSideClickDismiss = outSideClickDismiss;

        return xDialog;
    }

    /*dialog 标题字体 默认加粗 */
    private Typeface titleTypeface = Typeface.DEFAULT_BOLD;
    public xDialog setTitleTextTypeface(Typeface typeface){

        titleTypeface = typeface;

        return xDialog;
    }

    /*默认提示dialog*/
    public xDialog setWarm(String msg){

        setIcon(0);
        setWidth(0.7f);
        setTitle("");
        setText(msg);
        setBtnClickDismiss(true);
        setOutSideClickDismiss(false);
        return xDialog;
    }

    /*默认自定义dialog*/
    public xDialog setCustomView(View view){
        setIcon(0);
        setCancelBtn("");
        setConfirmBtn("");
        setBtnClickDismiss(true);
        setOutSideClickDismiss(false);
        setText("");
        setTitle("");
        setView(view);
        return xDialog;
    }


    /*加载框*/
    private static List<Dialog> dialogs =new ArrayList<>();

    public void showLoading(String msg){

        disLoading();

        xViewHelper helper = new xViewHelper(mContext,R.layout.loading_view);
        TextView msgText = helper.getView(R.id.text);
        if(msg.equals("")) msgText.setVisibility(View.GONE);
        else msgText.setText(msg);

        if(builder==null){

            builder = new CBDialogBuilder(mContext,layoutStyle,isSystemAlert,1.0f,1.0f,true);

            ViewGroup viewGroup = builder.getView(R.id.dialog_msg_layout);
            viewGroup.removeAllViews();
            builder
                    .setTitle(null)
                    .showCancelButton(false)
                    .showConfirmButton(false)
                    .setCancelable(false)
                    .showIcon(false)
                    .setDialogAnimation(R.style.dialog_anim_style)
                    .setTouchOutSideCancelable(false);


            View rootView = builder.getView(com.zhl.cbdialog.R.id.cb_dialog_root_layout);
            rootView.setBackgroundColor(mContext.getResources().getColor(R.color.colourless));

            if(helper.getConvertView().getParent()!=null){
                    ViewGroup group = (ViewGroup) helper.getConvertView().getParent();
                    group.removeView(helper.getConvertView());
                }
            builder.setView(helper.getConvertView());

            final Dialog dialog =  builder.create();

            try {
                    //dialog.getWindow().setDimAmount(0.4f);
                    dialog.show();
                    dialogs.add(dialog);
                    xHandler.putTask(15000, new xHandler.CallBack() {
                        @Override
                        public void onBack() {
                            dialog.dismiss();
                        }
                    });
                }catch (Exception e){
                Logger.d(e.toString());
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
            }catch (Exception e){
                Logger.d(e.toString());
            }


            it.remove();

        }

    }


}
