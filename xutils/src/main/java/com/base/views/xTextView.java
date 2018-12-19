package com.base.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.utils.xUtils;



@SuppressLint("AppCompatCustomView")
public class xTextView{

    private static xTextView xTextView;
    private TextView textView;

    private  Context context;
    private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;

    private static Typeface BOLD = Typeface.DEFAULT_BOLD;
    private static Typeface DEFALUT = Typeface.DEFAULT;



    public static xTextView init(Context context,String text){

        xTextView = new xTextView();
        xTextView.context = context;
        xTextView.textView = new TextView(context);
        xTextView.textView.setText(text);
        xTextView.textView.setLayoutParams(new ViewGroup.LayoutParams(xTextView.width, xTextView.height));
        return xTextView;
    }

    public static xTextView init(Context context,Spanned text){

        xTextView = new xTextView();
        xTextView.textView = new TextView(context);
        xTextView.textView.setText(text);
        xTextView.textView.setLayoutParams(new ViewGroup.LayoutParams(xTextView.width, xTextView.height));
        return xTextView;
    }

    public TextView create(){
        return xTextView.textView;
    }


    public xTextView setColor(int Color){


        textView.setTextColor(Color);
        return  xTextView;
    }

    public xTextView setType(Typeface Type){
        textView.setTypeface(Type);
        return  xTextView;
    }

    public xTextView setUnderline(){
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        return xTextView;
    }



    public xTextView setTags(Object tag) {
        textView.setTag(tag);
        return xTextView;
    }

    public Object getTags() {
        return textView.getTag();
    }

    public xTextView setGravitys(int gravitys){
        textView.setGravity(gravitys);
        return xTextView;

    }
    public xTextView setHeights(int height){
        this.height = height;
        setParams();
        return xTextView;
    }

    public xTextView setWidths(int width){
        this.width = width;
        setParams();
        return xTextView;
    }

    public xTextView setSizes(int width,int height){
        this.height = height;
        this.width = width;
        setParams();
        return xTextView;
    }

    private void setParams(){
        textView.setLayoutParams(new ViewGroup.LayoutParams(width,height));

    }

    public xTextView setOnClick(View.OnClickListener listener){
        textView.setOnClickListener(listener);

        return  xTextView;
    }


    public xTextView setParams(ViewGroup.LayoutParams  params){
        textView.setLayoutParams(params);
        return  xTextView;
    }

    public xTextView setTextSizes(float size){
        textView.setTextSize(size);
        return  xTextView;
    }


    public xTextView setPaddings(int l,int t ,int r,int b){
        textView.setPadding(l,t,r,b);
        return  xTextView;
    }
    public xTextView setPaddings(int l){
        textView.setPadding(l,l,l,l);
        return  xTextView;
    }
    public xTextView setPaddings(int l,int t){
        textView.setPadding(l,t,l,t);
        return  xTextView;
    }
    public xTextView setMargins(int l,int t ,int r,int b){
        xUtils.setMargins(textView,l,t,r,b);
        return  xTextView;
    }

    public xTextView setMargins(int l){
        xUtils.setMargins(textView,l,l,l,l);
        return  xTextView;
    }

    public xTextView setMargins(int l,int t){
        xUtils.setMargins(textView,l,t,l,t);
        return  xTextView;
    }

    public xTextView setBg(int res){
        textView.setBackground(context.getResources().getDrawable(res));
        return  xTextView;
    }

    public xTextView setBgColor(int res){
        textView.setBackgroundColor(context.getResources().getColor(res));
        return  xTextView;

    }


}
