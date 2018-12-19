package com.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.zhy.adapter.recyclerview.base.*;

import java.util.List;



public abstract class RvAdapter<T> extends com.zhy.adapter.recyclerview.CommonAdapter<T>{



    public RvAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context,layoutId,datas);
    }

    //添加数据
    public void addItem(int position, T data) {
        mDatas.add(position, data);
        notifyItemInserted(position);//通知演示插入动画
        notifyItemRangeChanged(position,mDatas.size()-position);//通知数据与界面重新绑定
    }


    //  删除数据
    public void removeData(int position) {
        mDatas.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


}
