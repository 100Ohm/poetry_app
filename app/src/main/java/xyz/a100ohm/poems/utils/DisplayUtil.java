package xyz.a100ohm.poems.utils;

import android.content.Context;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/9 16:57</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/9] [一百欧姆][单位转换工具]
 */
public class DisplayUtil {
    /**
     * px转换为dip或dp
     */
    public static int px2dip(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }

    /**
     * 将dip或dp值转换为px
     */
    public static int dip2px(Context context,float dipValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }

    /**
     * 将px值转换为sp
     */
    public static int px2sp(Context context,float pxValue){
        final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }

    /**
     * 将sp值转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
