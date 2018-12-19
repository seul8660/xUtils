package com.base.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.base.utils.xUtils;

import static com.base.utils.xUtils.value2dp;


@SuppressLint("AppCompatCustomView")
public class xImageView extends ImageView {

    public static xImageView defaultImageView;
    private static Context mContext;
    private static int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int height = ViewGroup.LayoutParams.WRAP_CONTENT;
    public xImageView(Context context) {
        super(context);
    }

    public static xImageView init(Context activity, int resID){
        defaultImageView = new xImageView(activity);
        mContext =activity;
        defaultImageView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        defaultImageView.setPaddings(value2dp(10));
        defaultImageView.setImageResource(resID);
        return defaultImageView;
    }

    public xImageView setHeight(int height){
        xImageView.height = height;
        setParams();
        return defaultImageView;
    }

    public xImageView setWidth(int width){
        xImageView.width = width;
        setParams();
        return defaultImageView;
    }

    public xImageView setSize(int width, int height){
        xImageView.height = height;
        xImageView.width = width;
        setParams();
        return defaultImageView;
    }

    private void setParams(){
        defaultImageView.setLayoutParams(new ViewGroup.LayoutParams(width,height));

    }

    public xImageView setParams(ViewGroup.LayoutParams  params){
        defaultImageView.setLayoutParams(params);
        return  defaultImageView;
    }

    public xImageView setPaddings(int l, int t , int r, int b){
        defaultImageView.setPadding(l,t,r,b);
        return  defaultImageView;
    }
    public xImageView setPaddings(int l){
        defaultImageView.setPadding(l,l,l,l);
        return  defaultImageView;
    }
    public xImageView setPaddings(int l, int t){
        defaultImageView.setPadding(l,t,l,t);
        return  defaultImageView;
    }
    public xImageView setMargins(int l, int t , int r, int b){
        xUtils.setMargins(defaultImageView,l,t,r,b);
        return  defaultImageView;
    }

    public xImageView setBg(int res){
        defaultImageView.setBackgroundResource(res);
        return  defaultImageView;
    }


}
