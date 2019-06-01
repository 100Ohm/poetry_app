package xyz.a100ohm.poems.view.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;
import xyz.a100ohm.poems.view.customview.CommentAdapter;
import xyz.a100ohm.poems.view.customview.PoetryBookAdapter;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: MainActivity.java </p>
 * <p>创建时间: 2019/4/4 0:36</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019.4.4] [一百欧姆][个人中心和资料编辑的Activity]
 */
public class PersonalCenterActivity extends AppCompatActivity {

    //悬浮按钮打开的界面
    private View openView;
    private FloatingActionButton fbtn;
    private double radio;
    private boolean isOn = false;
    //头像等资料
    private View nav;
    //标题栏图片的viewgroup
    private View imageViewGroup;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PersonalCenterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        //将ToolBar设置为ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.person_toolbar);
        setSupportActionBar(toolbar);

        //诗集recyclerView
        RecyclerView r = (RecyclerView) findViewById(R.id.person_bookreck_recycler_view);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        r.setLayoutManager(lm);
        PoetryBookAdapter ad = new PoetryBookAdapter(16);
        r.setAdapter(ad);

        //个人动态recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.person_trends_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        CommentAdapter adapter = new CommentAdapter(8);
        recyclerView.setAdapter(adapter);

        //日间夜间模式初始设置
        boolean night = SharedPreferencesUtils.getBoolean(getApplicationContext(), "NightMode", false);
        if(night) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //遮盖的view
        openView = findViewById(R.id.person_open_view);
        nav = findViewById(R.id.person_nav);
        imageViewGroup = findViewById(R.id.person_nav_image);

        //悬浮按钮
        fbtn = findViewById(R.id.person_float_button);

        //悬浮按钮事件
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动画
                if(Build.VERSION.SDK_INT > 21) {
                    createAnimation(openView).start();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Animator createAnimation(View v) {
        //初始化动画的起始点
        Point animPoint = new Point((int) (fbtn.getX() + (fbtn.getMeasuredWidth()) / 2),
                (int) (fbtn.getY() + (fbtn.getMeasuredHeight()) / 2));

        Animator animator;
        if (radio == 0L) {
            radio = Math.sqrt(Math.pow(openView.getWidth(), 2) + Math.pow(openView.getHeight(), 2));
        }
        if (isOn) {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    animPoint.x,// 动画开始的中心点X
                    animPoint.y,// 动画开始的中心点Y
                    (float) radio,// 动画开始半径
                    0);// 动画结束半径
        } else {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    animPoint.x,// 动画开始的中心点X
                    animPoint.y,// 动画开始的中心点Y
                    0,// 动画开始半径
                    (float) radio);// 动画结束半径
        }
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!isOn) {
                    openView.setVisibility(View.VISIBLE);
                } else {
                    nav.setVisibility(View.VISIBLE);
                    imageViewGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isOn) {
                    openView.setVisibility(View.INVISIBLE);
                } else {
                    //隐藏，减少过度绘制
                    nav.setVisibility(View.INVISIBLE);
                    imageViewGroup.setVisibility(View.INVISIBLE);
                }
                isOn = !isOn;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new DecelerateInterpolator());
        if(isOn)
            animator.setDuration(225);
        else
            animator.setDuration(350);
        return animator;
    }
}
