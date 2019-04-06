package xyz.a100ohm.poems.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: MainActivity.java </p>
 * <p>创建时间: 2019/4/4 0:36</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019.4.4] [一百欧姆][应用主要页面]
 */

public class MainActivity extends BaseActivity {
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // TODO: 2019/4/7  
                switch (menuItem.getItemId()){
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
                    default:
                            mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });

    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
