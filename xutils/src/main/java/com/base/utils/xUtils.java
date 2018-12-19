package com.base.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.afollestad.ason.Ason;
import com.afollestad.ason.AsonArray;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class xUtils {

    private static Context context;

    public static void init(Context context) {
        if (xUtils.context != null) {
            throw new IllegalStateException("application already holded 'application'.");
        }
        xUtils.context = context;
    }

    /**
     * 读取Raw文件夹下文本类型文件
     *
     * @param resourceId 资源id
     * @return 返回的读取完成的数据 string格式
     */
    public static String readFileFromRaw(int resourceId) {
        if (null == context || resourceId < 0) {
            return null;
        }

        String result = null;
        try {
            InputStream input = context.getResources().openRawResource(resourceId);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            result = new String(buffer, "GB2312");
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    /*****根据资源名字获取ID*****/
    public static int getReSourcesIdByName(String name) {

        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

        return id;
    }

    /*****根据资源名字获取ID*****/
    public static int getRawIdByName(String name) {

        int id = context.getResources().getIdentifier(name, "raw", context.getPackageName());

        return id;
    }

    public static int getWidgetIdByName(String name) {

        int id = context.getResources().getIdentifier(name, "id", context.getPackageName());

        return id;
    }


    /**转换dip为px**/
    public static int convertDipOrPx(int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /*转换px为dip*/
    public static int convertPxOrDip(int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }


    /*获取dp*/
    public static int value2dp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


    /*把String转化为float*/
    public static float convertToFloat(String number) {
        float defaultValue = -1;
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    public static double convertToDouble(Object number) {
        double defaultValue = -0.00;

        if (TextUtils.isEmpty((String) number)) {
            return defaultValue;
        }
        try {
            return Double.valueOf((String) number);
        } catch (Exception e) {
            return defaultValue;
        }

    }


    /*把String转化为float*/
    public static float convertToFloat(Object number) {
        float defaultValue = -1;
        if (TextUtils.isEmpty((String) number)) {
            return defaultValue;
        }
        try {
            return Float.parseFloat((String) number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /*把String转化为int*/
    public static int convertToInt(String number) {
        int defaultValue = -1;
        if (TextUtils.isEmpty(number)) {
            return 0;
        }
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /*把Object转化为int*/
    public static int convertToInt(Object number) {
        int defaultValue = -1;
        if (TextUtils.isEmpty(number.toString())) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(number.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public static String convertToStr(int number) {
        String defaultValue = "";

        try {
            return number + "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertToStr(Object number) {
        String defaultValue = "";
        try {
            return number + "";
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /*保留两位小数*/
    public static String savePoint(Object number) {

        float distanceValue = Math.round((convertToDouble(number) * 100f)) / 100f;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String distanceString = decimalFormat.format(distanceValue);//format 返回的是字符串
        return distanceString;
    }

    /*获取屏幕宽度*/
    public static int getWindowWidth() {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /*获取屏幕高度*/
    public static int getWindowHeight() {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }


    /*数字补0*/
    public static <T> String getZero(T value) {
        String v_value = value + "";
        if (v_value.length() == 1) {
            v_value = "0" + v_value;
        }

        return v_value;
    }

    /*数字去0*/
    public static <T> String removeZero(T value) {
        String v_value = value + "";
        if (v_value.length() == 2 && v_value.substring(0, 1).equals("0")) {
            v_value = v_value.substring(1);
        }
        return v_value;
    }


    /*setMargins**/
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    /*序列化*/
    public static <T> List<T> deserialize(@Nullable Ason json, @NotNull Class<T> cls, String tab) {
        List<T> result = new ArrayList<>();
        try {
            if (json != null) {
                Ason col = json.get(tab + "col");
                AsonArray datas = json.get(tab);
                Field[] fields = cls.getDeclaredFields();

                if (datas != null) {
                    for (int k = 0; k < datas.size(); k++) {
                        AsonArray data = (AsonArray) datas.get(k);
                        T model = cls.newInstance();
                        for (Field field : fields) {
                            String type = field.getGenericType().toString();    //获取属性的类型
                            String name = field.getName();
                            if (col != null && col.get(name) != null && data != null) {
                                convertToInt(col.get(name));
                                String dataitem = (String) data.get(convertToInt(col.get(name)));
                                name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
                                if (type.equals("class java.lang.String")) {   //如果type是类类型，则前面包含"class "，后面跟类名
                                    Method m = cls.getMethod("set" + name, String.class);
                                    m.invoke(model, dataitem);    //调用getter方法获取属性值
                                }
                                if (type.equals("class java.lang.Integer")) {
                                    Method m = cls.getMethod("set" + name, Integer.class);
                                    m.invoke(model, convertToInt(dataitem));
                                }
                            }
                        }
                        result.add(model);
                    }
                }
            }
        } catch (Exception e) {
            result = null;

        }

        return result;
    }


    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


    public static String getUniqueID() {
        //获得独一无二的Psuedo ID
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }




    /*
     * 获取当前程序的版本名
     */
    public static String getVersionName() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionName;
    }

    /*
 * 获取当前程序的版本号
 */
    public static int getVersionCode() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager =context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionCode;
    }

    public static String getBigVersion(String version){

        return version.split("\\.")[0]+"."+version.split("\\.")[1];
    }

    /**
     * 安装apk
     */
    public static void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    public static int getStatusBarHeight() {

        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {

            result = context.getResources().getDimensionPixelSize(resourceId);

        }

        return result;

    }

}
