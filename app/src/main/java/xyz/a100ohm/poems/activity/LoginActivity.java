package xyz.a100ohm.poems.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.bmob.v3.listener.SaveListener;
import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.mvp.model.beans.bomb.User;
import xyz.a100ohm.poems.utils.StringUtils;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 15:28</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][19.4.4] [一百欧姆][登陆页面，用户登陆使用]
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    TextInputLayout username;
    TextInputLayout password;
    AppCompatCheckBox remember;
    Button loginButton;
    TextView goToSignUp;
    View progress;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewAndEvent();
    }

    private void initViewAndEvent() {
        username = (TextInputLayout) findViewById(R.id.login_edit_text_username);
        password = (TextInputLayout) findViewById(R.id.login_edit_text_password);
        remember = (AppCompatCheckBox) findViewById(R.id.login_checkbox_remember_password);
        loginButton = (Button) findViewById(R.id.login_button);
        goToSignUp = (TextView) findViewById(R.id.login_text_goto_sign_up);
        progress = (View) findViewById(R.id.login_progress);

        goToSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SignUpActivity.startActivity(LoginActivity.this);
                finish();
            }
        });

        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String usernameStr = username.getEditText().getText().toString();
        String passwordStr = password.getEditText().getText().toString();

        if(validateAccount(usernameStr) && validatePassword(passwordStr)) {
            progress.setVisibility(View.VISIBLE);
            User bu2 = new User();
            bu2.setUsername(usernameStr);
            bu2.setPassword(passwordStr);
            bu2.login(LoginActivity.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    progress.setVisibility(View.GONE);
                    Snackbar.make(loginButton, "登陆成功", Snackbar.LENGTH_SHORT).show();
                    //通过BmobUser user = BmobUser.getCurrentUser(context)获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息
                    MainActivity.startActivity(LoginActivity.this);
                    finish();
                }

                @Override
                public void onFailure(int code, String msg) {
                    progress.setVisibility(View.GONE);
                    Snackbar.make(loginButton, "登陆失败：" + msg, Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout 显示错误提示的textInputLayout
     * @param error 错误提示
     */
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(StringUtils.isEmpty(account)){
            showError(username,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(this.password,"密码不能为空");
            return false;
        }

        if (password.length() > 18) {
            showError(this.password,"密码长度为6-18位");
            return false;
        }
        return true;
    }
}
