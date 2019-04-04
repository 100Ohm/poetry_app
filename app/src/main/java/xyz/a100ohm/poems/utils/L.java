package xyz.a100ohm.poems.utils;

import android.util.Log;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 15:49</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/4] [一百欧姆][日志类]
 */
public class L {
    //日志开关，打开则表示正在debug，输出日志，发行版则关闭开关
    public static final boolean DEBUG = true;

    public static final String TAG = "Smart Butler";

    //控制日志输出
    public static void d(String text) {
        if(DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if(DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if(DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void e(String text) {
        if(DEBUG) {
            Log.e(TAG, text);
        }
    }
}
