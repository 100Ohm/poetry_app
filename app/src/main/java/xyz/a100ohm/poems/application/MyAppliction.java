package xyz.a100ohm.poems.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.jinrishici.sdk.android.factory.JinrishiciFactory;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

import cn.bmob.v3.Bmob;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import xyz.a100ohm.poems.utils.L;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;
import xyz.a100ohm.poems.utils.StaticMessage;
import xyz.a100ohm.poems.view.activity.MainActivity;

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

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //MTA数据分析接入
        // 打开Logcat输出，上线时，一定要关闭
        StatConfig.setDebugEnable(true);
        // 注册activity生命周期，统计时长
        StatService.registerActivityLifecycleCallbacks(this);
        //bomb初始化
        Bmob.initialize(this, StaticMessage.BMOB_KEY);
        //今日诗词初始化
        JinrishiciFactory.init(this);
        //初始化Realm，使用默认配置
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        L.e("Application中初始化的Realm对象："+Realm.getDefaultInstance());
    }

    public static Context getContext() {
        return context;
    }
}
