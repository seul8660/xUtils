package com.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.utils.xBindUtils;
import com.base.utils.xViewHelper;

import me.yokeyword.fragmentation.SupportFragment;

public class xFragment extends SupportFragment {


    protected xViewHelper baseHelper;
    protected xActivity xAct;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        xAct = (xActivity) _mActivity;

    }


    @CallSuper
    protected void setContentView(int layout){

        baseHelper = new xViewHelper(xAct,layout);
        /*自动绑定view*/
        xBindUtils.bindViews(this, baseHelper.getConvertView());


    }

    protected View findViewById(int id){

        return baseHelper.getView(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return baseHelper.getConvertView();
    }

    public void toggleTo(Class cls){

        xAct.toggleTo(cls);

    }



}
