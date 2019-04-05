package xyz.a100ohm.poems.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import xyz.a100ohm.poems.R;
/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/4 15:28</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][19.4.4] [一百欧姆][引导页，初次启动轮转页面]
 */
public class GuideActivity extends AppCompatActivity {

    //ViewPager
    private ViewPager mViewPager;

    //容器
    private List<View> views = new ArrayList<>();

    //View
    private View view1, view2, view3;

    private ImageView point1, point2, point3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    //初始化View
    private void initView() {
        mViewPager = findViewById(R.id.mViewPager);

        view1 = View.inflate(this, R.layout.pager_item_one, null);
        view2 = View.inflate(this, R.layout.pager_item_two, null);
        view3 = View.inflate(this, R.layout.pager_item_three, null);

        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);

        setPointImg(0);

        views.add(view1);
        views.add(view2);
        views.add(view3);

        view3.findViewById(R.id.enter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //设置适配器
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }
        });

        //动态使用svg图片
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        setPointImg(0);
                        break;
                    case 1:
                        setPointImg(1);
                        break;
                    case 2:
                        setPointImg(2);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {}
        });
    }
    private void setPointImg(int page){
        switch (page){
            case 0:
                point1.setBackgroundResource(R.drawable.ic_point_on);
                point2.setBackgroundResource(R.drawable.ic_point_off);
                point3.setBackgroundResource(R.drawable.ic_point_off);
                break;
            case 1:
                point1.setBackgroundResource(R.drawable.ic_point_off);
                point2.setBackgroundResource(R.drawable.ic_point_on);
                point3.setBackgroundResource(R.drawable.ic_point_off);
                break;
            case 2:
                point1.setBackgroundResource(R.drawable.ic_point_off);
                point2.setBackgroundResource(R.drawable.ic_point_off);
                point3.setBackgroundResource(R.drawable.ic_point_on);
                break;
        }
    }
}
