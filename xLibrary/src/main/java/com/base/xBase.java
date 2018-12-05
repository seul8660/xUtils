package com.base;


import android.content.Context;

import com.base.utils.xAct;
import com.base.utils.xColorUtils;
import com.base.utils.xUtils;
import com.base.views.xTextView;
import com.example.base.R;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

public class xBase {

    private static Context context;


    public static void init(Context context) {

        xBase.context = context;
        xUtils.init(context);
        xColorUtils.init(context);
        xAct.init(context);

        Hawk.init(context).build();

    }


    public static Context getContext(){

        return context;
    }


}
