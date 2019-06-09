package xyz.a100ohm.poems.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import cn.bmob.v3.listener.SaveListener;
import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.model.beans.bomb.User;
import xyz.a100ohm.poems.utils.StringUtils;

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
public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    TextInputLayout email;
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout repeatPasswd;
    Button signUpButton;
    View progress;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SignUpActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViewAndEvent();
    }

    private void initViewAndEvent() {
        email = (TextInputLayout) findViewById(R.id.sign_up_edit_text_email);
        username = (TextInputLayout) findViewById(R.id.sign_up_edit_text_username);
        password = (TextInputLayout) findViewById(R.id.sign_up_edit_text_password);
        repeatPasswd = (TextInputLayout) findViewById(R.id.sign_up_edit_text_repeat_password);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        progress = (View) findViewById(R.id.sign_up_progress);

        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String emailStr = email.getEditText().getText().toString();
        String usernameStr = username.getEditText().getText().toString();
        String passwordStr = password.getEditText().getText().toString();
        String repeatPasswordStr = repeatPasswd.getEditText().getText().toString();

        email.setErrorEnabled(false);
        username.setErrorEnabled(false);
        password.setErrorEnabled(false);
        repeatPasswd.setErrorEnabled(false);

        if(validateAccount(usernameStr) && validateAccount(emailStr)
                && validatePassword(passwordStr)
                && validateRepeatPassword(passwordStr, repeatPasswordStr)) {//如果都通过初步检查，就注册用户
            progress.setVisibility(View.VISIBLE);
            User bu = new User();
            bu.setEmail(emailStr);
            bu.setUsername(usernameStr);
            bu.setPassword(passwordStr);
            //注意：不能用save方法进行注册
            bu.signUp(SignUpActivity.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    progress.setVisibility(View.GONE);
                    Snackbar.make(signUpButton, "注册成功", Snackbar.LENGTH_SHORT).show();
                    //通过BmobUser user = BmobUser.getCurrentUser(context)获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息
                    MainActivity.startActivity(SignUpActivity.this);
                    finish();
                }
                @Override
                public void onFailure(int code, String msg) {
                    progress.setVisibility(View.GONE);
                    if(msg.split(" '")[0].equals("email"))
                        Snackbar.make(signUpButton, "该邮箱已被注册", Snackbar.LENGTH_SHORT).show();
                    else if(msg.split(" '")[0].equals("username"))
                        Snackbar.make(signUpButton, "该用户名已被注册", Snackbar.LENGTH_SHORT).show();
                    else
                        Snackbar.make(signUpButton, "注册失败：" + msg, Snackbar.LENGTH_SHORT).show();
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

        if (password.length() < 6 || password.length() > 18) {
            showError(this.password,"密码长度为6-18位");
            return false;
        }
        return true;
    }

    /**
     * 验证重复密码
     * @param password
     * @return
     */
    private boolean validateRepeatPassword(String password, String repeatPassword) {
        if (!StringUtils.equals(password, repeatPassword)) {
            showError(this.repeatPasswd,"密码不一致");
            return false;
        }
        return true;
    }
}
