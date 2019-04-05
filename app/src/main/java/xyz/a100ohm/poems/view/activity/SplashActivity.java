package xyz.a100ohm.poems.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;

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
public class SplashActivity extends AppCompatActivity {

    //Handler
    private SoftReference<Handler> handler
            = new SoftReference<Handler>(new MyHandler());

    //Handler内部类来的，注意内存泄漏
    public class MyHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case StaticMessage
                        .HANDLE_SPLASH_MSG:
                    //判断程序是否是第一次运行
                    if(isFirst()) {
                        //TODO
                        Toast.makeText(SplashActivity.this, "第一次运行",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this,
                                GuideActivity.class));
                    } else { //改了
                        //TODO
                        Toast.makeText(SplashActivity.this, "不是第一次运行",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this,
                                GuideActivity.class));
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

        //初始化View
        initView();
    }

    //初始化view
    private void initView() {
        //1000ms
        Message message = new Message();
        message.what = StaticMessage.HANDLE_SPLASH_MSG;
        handler.get().sendMessageDelayed(message, 1000);
        //handler.get().sendEmptyMessageDelayed(StaticClass.HANDLE_SPLASH_MSG, 1000);
    }

    //禁止返回键返回
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
