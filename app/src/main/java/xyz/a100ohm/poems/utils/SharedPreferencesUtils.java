package xyz.a100ohm.poems.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 16:03</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/4] [一百欧姆][与SharePreferences相关操作的封装]
 */
public class SharedPreferencesUtils {
    public static final String NAME = "config";

    //增
    public static void putString(Context context, String key, String values) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, values).commit();

    }

    //查
    public static String getString(Context context, String key, String defValues) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValues);
    }

    //增
    public static void putInt(Context context, String key, int values) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, values).commit();

    }

    //查
    public static int getInt(Context context, String key, int defValues) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValues);
    }

    //增
    public static void putBoolean(Context context, String key, boolean values) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).commit();

    }

    //查
    public static boolean getBoolean(Context context, String key, boolean defValues) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValues);
    }

    //删除
    public static void remove(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除
    public static void removeAll(Context context){
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
