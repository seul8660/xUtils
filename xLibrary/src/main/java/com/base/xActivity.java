package com.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.base.utils.xAct;
import com.base.utils.xBindUtils;
import java.util.HashMap;
import java.util.Map;

import me.yokeyword.fragmentation.SupportActivity;

public class xActivity extends SupportActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /*新建activity时加入act管理器*/
        xAct.addAct(this);

    }

    /*自动绑定view*/
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        xBindUtils.bindViews(this, getWindow().getDecorView());
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        xBindUtils.bindViews(this, getWindow().getDecorView());
    }


    /*跳转至新的activity*/
    public void turnTo(Class cls){
        xAct.turnTo(this,cls);
    }

    /*移除当前activity*/
    public void backTo(){

        xAct.removeLastAct(this);
    }


    /*获取当前view*/
    public View getContentView(){
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        ViewGroup content = (ViewGroup) view.getChildAt(0);
        return content.getChildAt(0);
    }


    /*fragment 管理*/

    /*设置 fragment 容器(必须)*/
    private int containerId;
    @CallSuper
    public void setContainerView(int containerId){

        this.containerId = containerId;

        fragments = new HashMap<>();
    }

    private Map<Class,xFragment> fragments;
    public xFragment curFragment;

    public void toggleTo(Class key){

        if(!fragments.containsKey(key)) {
            try {

                xFragment fragment = (xFragment) key.newInstance();

                fragments.put(key,fragment);

                curFragment = fragment;

                if(getTopFragment()==null) loadRootFragment(containerId,curFragment);
                else start(curFragment);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            curFragment = fragments.get(key);
            showHideFragment(curFragment);

        }

    }



}
