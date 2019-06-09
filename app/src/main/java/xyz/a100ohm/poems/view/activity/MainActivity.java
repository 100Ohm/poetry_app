package xyz.a100ohm.poems.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.model.beans.bomb.User;
import xyz.a100ohm.poems.utils.L;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;
import xyz.a100ohm.poems.view.fragment.FeedbackFragment;
import xyz.a100ohm.poems.view.fragment.LibrayFragment;
import xyz.a100ohm.poems.view.fragment.MainFragment;
import xyz.a100ohm.poems.view.fragment.MyPoetryFragment;
import xyz.a100ohm.poems.view.fragment.SettingFragment;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: MainActivity.java </p>
 * <p>创建时间: 2019/4/4 0:36</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019.4.4] [一百欧姆][应用主要页面]
 * [2][2019.4.6] [一百欧姆][增加了夜间模式的设置]
 * [3][2019.4.7] [一百欧姆][增加了菜单选项卡的切换]
 * [4][2019.5.29] [一百欧姆][增加了点击返回键关闭菜单的方法]
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    //drawerLayout
    private DrawerLayout mDrawerLayout;

    private CircleImageView headImage;
    private TextView userNameTextView;
    private TextView emailTextView;

    private Realm realm;



    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化成员
        initView();

        //将ToolBar设置为ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置ActionBar的菜单键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //NavigationView菜单选项设置
        NavigationView navigationView = (NavigationView) findViewById(R.id.design_nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        //详细请看this类实现的onNavigationItemSelected()方法
        navigationView.setNavigationItemSelectedListener(this);

        //日间夜间模式初始设置
        boolean night = SharedPreferencesUtils.getBoolean(getApplicationContext(), "NightMode", false);
        if(night) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if(savedInstanceState == null) {//只在第一次的时候加入
            //初始fragment设置为MainFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_fragment_framelayout, new MainFragment());
            transaction.commit();
        }

        User user = BmobUser.getCurrentUser(this, User.class);
        if(user != null){
            //设置显示用户等
            displayUser(user);
        }
        //获取realm对象
        realm = Realm.getDefaultInstance();
        L.e("从MainActivity中获取的realm对象"+realm);

    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //NavigationView不能直接使用findViewById
        NavigationView navigationView = (NavigationView) findViewById(R.id.design_nav_view);
        View headerView = navigationView.getHeaderView(0);
        headImage = (CircleImageView) headerView.findViewById(R.id.icon_image);
        emailTextView = (TextView) headerView.findViewById(R.id.mail);
        userNameTextView = (TextView) headerView.findViewById(R.id.username);
    }

    /**
     * 显示用户信息在侧滑菜单
     * @param user 用户对象
     */
    public void displayUser(User user) {
        if(user.getImage() != null)
            Snackbar.make(headImage, "有图", Snackbar.LENGTH_SHORT).show();
        userNameTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
    }

    //Toolbar响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    //侧滑菜单的选项响应
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // TODO: 2019/4/7
        switch (menuItem.getItemId()){
            case R.id.nav_home://主页
                replaceFragment(new MainFragment());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_poetry_library://诗文集
                replaceFragment(new LibrayFragment());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_person://个人中心
                //replaceFragment(new PersonalCenterFragment());
                PersonalCenterActivity.startActivity(this);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_my_poetry_anthology:
                replaceFragment(new MyPoetryFragment());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_night_mode://夜间模式
                boolean night = SharedPreferencesUtils.getBoolean(getApplicationContext(), "NightMode", false);
                if(night) {//如果处于夜间，开启日间模式，并设置标记为false
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.putBoolean(getApplicationContext(), "NightMode", false);
                    recreate();
                } else {//如果处于日间，开启夜间模式，并设置标记为true
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.putBoolean(getApplicationContext(), "NightMode", true);
                    recreate();
                }
                break;
            case R.id.nav_setting:
                replaceFragment(new SettingFragment());
                // TODO: 2019/5/29
                LoginActivity.startActivity(this);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_help:
//                replaceFragment(new HelpFragment());
//                // TODO: 2019/5/29
//                SignUpActivity.startActivity(this);
                startActivity(new Intent(this,
                        GuideActivity.class));
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_feedback:
                replaceFragment(new FeedbackFragment());
                mDrawerLayout.closeDrawers();
                break;
            default:
                mDrawerLayout.closeDrawers();
        }
        return true;
    }

    //替换fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragment_framelayout, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());//加入返回栈
        transaction.commit();
    }

    //返回键关闭菜单
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(findViewById(R.id.design_nav_view)))
            mDrawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用完之后关闭
        //realm.close();
    }
}
