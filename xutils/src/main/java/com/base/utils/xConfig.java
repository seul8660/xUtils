package com.base.utils;

import android.content.SharedPreferences;
import com.base.xBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class xConfig {

    private xConfig() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "x_config";

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     */
    public static void put(String key, Object t) {

        SharedPreferences sp = xBase.getContext().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Integer) {
            editor.putInt(key, (Integer) t);
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, (Boolean) t);
        } else if (t instanceof Long) {
            editor.putLong(key, (Long) t);
        } else if (t instanceof Float) {
            editor.putFloat(key, (Float) t);
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param key
     * @return
     */
    public static <T> T get(String key, T t) {
        SharedPreferences sp = xBase.getContext().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        if (t instanceof String) {
            String str = sp.getString(key, (String) t);
            t = (T) str;
        } else if (t instanceof Integer) {
            Integer in = sp.getInt(key, (Integer) t);
            t = (T) in;
        } else if (t instanceof Long) {
            Long lon = sp.getLong(key, (Long) t);
            t = (T) lon;
        } else if (t instanceof Float) {
            Float fl = sp.getFloat(key, (Float) t);
            t = (T) fl;
        } else if (t instanceof Boolean) {
            Boolean bl = sp.getBoolean(key, (Boolean) t);
            t = (T) bl;
        }
        return t;

    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = xBase.getContext().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     */
    public static void clearAll() {
        SharedPreferences sp = xBase.getContext().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = xBase.getContext().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = xBase.getContext().getSharedPreferences(FILE_NAME,
                MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }


}
