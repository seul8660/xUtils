package com.base.utils;

import android.content.Context;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import static com.base.utils.xUtils.convertToStr;


public class xViewHelper implements Serializable{

    private static final long serialVersionUID = 6L;
    private SparseArray<View> mViews;
    private View mConvertView;

    public xViewHelper(Context context, int viewid, ViewGroup group){
        this.mConvertView  = LayoutInflater.from(context).inflate(viewid,group);
        this.mViews = new SparseArray<View>();
    }

    public xViewHelper(Context context, int viewid){
        this.mConvertView  = LayoutInflater.from(context).inflate(viewid,null);
        this.mViews = new SparseArray<View>();
    }

    public void setText(int resID,String text){
        TextView textView =getView(resID);
        textView.setText(text);
    }

    public void setText(int resID,Spanned text){
        TextView textView =getView(resID);
        textView.setText(text);
    }

    public String getText(int resID){
        if(getView(resID) instanceof TextView){
           return convertToStr(((TextView) getView(resID)).getText().toString());
        }
        else return "";
    }
    public void setTmage(int resID,int imgID){
        ImageView imageView =getView(resID);
        imageView.setImageResource(imgID);
    }

    public void setBackground(int resID,int imgID){
        View imageView =getView(resID);
        imageView.setBackgroundResource(imgID);
    }

    public void setTag(int resID,Object tag){
        getView(resID).setTag(tag);

    }

    public Object getTag(int resID){

      return getView(resID).getTag();
    }


   public void addView(int resID,View view){
       ViewGroup group = getView(resID);
        group.addView(view);
   }
   public void addView(View view){
        ViewGroup group = (ViewGroup) getConvertView();
        group.addView(view);
    }

    public void removeView(int resID,View view){

        ViewGroup group = getView(resID);
        group.removeView(view);
    }
    public void removeAllViews(int resID){
        ViewGroup group = getView(resID);
        group.removeAllViews();
    }

    public void removeAllViews(){
        ViewGroup group = (ViewGroup) getConvertView();
        group.removeAllViews();

    }

    public void setOnClickListener(int resID, View.OnClickListener listener){
        getView(resID).setOnClickListener(listener);
    }

    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public <T extends View> T getChildAt(int index){
        ViewGroup group = (ViewGroup) getConvertView();
        View view = group.getChildAt(index);
        return (T) view;
    }

    public int getChildCount(){
        ViewGroup group = (ViewGroup) getConvertView();
        return group.getChildCount();
    }

    public void setVisibility(int resID,int Visibility){
        getView(resID).setVisibility(Visibility);

    }

    public View getConvertView(){

        return mConvertView;
    }
}
