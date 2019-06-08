package xyz.a100ohm.poems.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;

import cn.bmob.v3.BmobUser;
import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;
import xyz.a100ohm.poems.utils.StaticMessage;
import xyz.a100ohm.poems.utils.UtilTools;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 15:28</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][19.4.4] [一百欧姆][
 *            闪屏页面，启动App时显示
 *            1.延时1000ms
 *            2.判断程序是否第一次运行
 *            3.自定义字体
 *            4.Acitivity全屏]
 */
public class SplashActivity extends BaseActivity {

    //Handler
    private Handler handler = new MyHandler();

    //Handler内部类来的，注意内存泄漏
    public class MyHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case StaticMessage
                        .HANDLE_SPLASH_MSG:
                    //判断程序是否是第一次运行
                    if(isFirst()) {
                        startActivity(new Intent(SplashActivity.this,
                                GuideActivity.class));
                    } else {
                        BmobUser bmobUser = BmobUser.getCurrentUser(SplashActivity.this);
                        if(bmobUser != null){
                            // 允许用户使用应用
                            startActivity(new Intent(SplashActivity.this,
                                    MainActivity.class));
                        }else{
                            //缓存用户对象为空时， 可打开用户注册界面…
                            LoginActivity.startActivity(SplashActivity.this);
                        }
                    }
                    finish();
                    break;
            }
        }
    }

    private boolean isFirst() {
        boolean isFirst = SharedPreferencesUtils.getBoolean(this,
                StaticMessage.SHARE_IS_FIRST, true);
        if(isFirst){
            SharedPreferencesUtils.putBoolean(this, StaticMessage.SHARE_IS_FIRST, false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Message message = new Message();
        message.what = StaticMessage.HANDLE_SPLASH_MSG;
        handler.sendMessageDelayed(message, 200);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Message message = new Message();
        message.what = StaticMessage.HANDLE_SPLASH_MSG;
        handler.sendMessageDelayed(message, 200);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(StaticMessage.HANDLE_SPLASH_MSG);
    }

    //禁止返回键返回
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
