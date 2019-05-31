package xyz.a100ohm.poems.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import xyz.a100ohm.poems.R;
/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 15:28</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][19.4.4] [一百欧姆][注册页面]
 */
public class SignUpActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SignUpActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
