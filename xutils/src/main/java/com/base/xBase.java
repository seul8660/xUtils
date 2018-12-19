package com.base;


import android.content.Context;
import android.support.annotation.NonNull;

import com.base.utils.xAct;
import com.base.utils.xColorUtils;
import com.base.utils.xUtils;
import com.example.base.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

public class xBase {

    private static Context context;


    /*
    * 集成SmartRefreshLayout下拉刷新，已初始化
    * 集成logger，已初始化
    * 集成Fragmentation，已初始化，使用时直接继承xActivity,xFragment
    * 集成statusbarutil
    *
    * 集成并修改AutoFlowLayout 解决无子项时crash问题
    * 集成并修改CountdownView 实现超过60限制分钟数
    * 集成并修改FlycoTabLayout_Lib 实现UnCrollTabLayout,修改CommonTabLayout 为滚动tab
    * 集成并修改RweigetHelper
    * 集成并修改EasyPopup
    * 集成并修改pickerview 修改弹框样式与非弹框一致，修复非弹框被dialog遮挡问题
    *
    * xDialog 集成并修改封装CBDialogBuilder 实现ios样式弹框与加载框
    * xAct Activity管理
    * xViewHelper 封装 LayoutInflater 获取view 流程
    * xToast 封装整理自定义吐司，集成成功，失败，提醒等样式，加入toast点击间隔判断
    * xHandler 封装定时功能。
    * xAsyncTask 封装异步任务
    * xConfig 封装SharedPreferences
    * xBindUtils 自动绑定view 用法参考 xActivity 和 xFragment
    * OnMultiClickListener 防止多次点击
    * CommonAdapter 集成鸿洋大神的万能适配器
    * RvAdapter 集成整理鸿洋大神的 recyclerview 适配器
    * xColorUtils 集成网上的颜色工具类
    * xDateUtils 集成整理网上的日期工具类
    * xRegexUtils 集成整理正则验证工具类
    * xImageView 封装iamgeview
    * xTextView 封装 textview
    *
    *
    *
    * */


    public static void init(Context context) {

        xBase.context = context;
        xUtils.init(context);
        xColorUtils.init(context);
        xAct.init(context);


        /*下拉刷新组件初始化*/
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableAutoLoadMore(false);
                layout.setEnableLoadMore(true);
                layout.setEnableRefresh(true);

            }
        });


        /*初始化logger*/
        Logger.addLogAdapter(new AndroidLogAdapter());


        /*初始化fragment 管理器*/
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();

    }


    public static Context getContext(){

        return context;
    }


}
