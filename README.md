# xUtils
我的工具类整理


功能介绍

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




步骤1.将其添加到根build.gradle中：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
步骤2.添加依赖项

	dependencies {
	        implementation 'com.github.seul8660:xUtils:v1.0'
	}
  
步骤3. 在application中初始化 

xBase.init(this);
