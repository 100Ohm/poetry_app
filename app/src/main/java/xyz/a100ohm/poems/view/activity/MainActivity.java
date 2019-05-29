package xyz.a100ohm.poems.view.activity;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.utils.L;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;
import xyz.a100ohm.poems.view.fragment.FeedbackFragment;
import xyz.a100ohm.poems.view.fragment.HelpFragment;
import xyz.a100ohm.poems.view.fragment.LibrayFragment;
import xyz.a100ohm.poems.view.fragment.MainFragment;
import xyz.a100ohm.poems.view.fragment.MyPoetryFragment;
import xyz.a100ohm.poems.view.fragment.PersonalCenterFragment;
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
        boolean night = SharedPreferencesUtils.getBoolean(MainActivity.this, "NightMode", false);
        if(night) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //初始fragment设置为MainFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragment_framelayout, new MainFragment());
        transaction.commit();

        //test
        //String filePath = Environment.getExternalStorageDirectory().getPath();

    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                replaceFragment(new PersonalCenterFragment());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_my_poetry_anthology:
                replaceFragment(new MyPoetryFragment());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_night_mode://夜间模式
                boolean night = SharedPreferencesUtils.getBoolean(MainActivity.this, "NightMode", false);
                if(night) {//如果处于夜间，开启日间模式，并设置标记为false
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.putBoolean(MainActivity.this, "NightMode", false);
                    recreate();
                } else {//如果处于日间，开启夜间模式，并设置标记为true
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.putBoolean(MainActivity.this, "NightMode", true);
                    recreate();
                }
                break;
            case R.id.nav_setting:
                replaceFragment(new SettingFragment());
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_help:
                replaceFragment(new HelpFragment());
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
        //transaction.addToBackStack(null);//注释掉不加入返回栈
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
}
