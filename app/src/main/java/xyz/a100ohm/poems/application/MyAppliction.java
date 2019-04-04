package xyz.a100ohm.poems.application;

import android.app.Application;

import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 15:24</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][19.4.4] [一百欧姆][自定义Application类]
 */
public class MyAppliction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //MTA数据分析接入
        // 打开Logcat输出，上线时，一定要关闭
        StatConfig.setDebugEnable(true);
        // 注册activity生命周期，统计时长
        StatService.registerActivityLifecycleCallbacks(this);
    }
}
