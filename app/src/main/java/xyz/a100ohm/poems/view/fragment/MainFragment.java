package xyz.a100ohm.poems.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.utils.L;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/7 3:29</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/7] [一百欧姆][创建文件]
 */
public class MainFragment extends Fragment {
    //TabLayout
    private TabLayout mTabLayout;

    //ViewPager
    private ViewPager mViewPager;

    //Title
    private List<String> mTitle;

    //Fragment
    private List<Fragment> mFragment;

    //SwipeRefreshLayout
    //private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.d("create"+this);
        //初始化数据
        initData();
        //初始化View对象等
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        L.d(""+this);
//
//        //初始化数据
//        initData();
//        //初始化View对象等
//        initView();
    }

    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.tab_every_day));
        mTitle.add(getString(R.string.tab_comment));
        mTitle.add(getString(R.string.tab_creation));

        mFragment = new ArrayList<>();
        mFragment.add(new MainTabEveryDayFragment());
        mFragment.add(new MainTabCommentFragment());
        mFragment.add(new MainTabCreationFragment());
    }

    private void initView() {
        mTabLayout = getView().findViewById(R.id.main_TabLayout);
        mViewPager = getView().findViewById(R.id.main_viewPager);
//        swipeRefreshLayout = getActivity().findViewById(R.id.swipe_refresh);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//
//        //下拉刷新
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshView();
//            }
//        });
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //view pager滑动监听
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {}
//
////            @SuppressLint("RestrictedApi")
//            @Override
//            public void onPageSelected(int i) {
////                if(i == 0){
////                    //为什么会有错误提示
////                    mFloatingActionButton.setVisibility(View.GONE);
////                } else {
////                    mFloatingActionButton.setVisibility(View.VISIBLE);
////                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {}
//        });


        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int i) {
                return mFragment.get(i);
            }
            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            //设置标题
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

//    private void refreshView() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                });
//            }
//        }).start();
//    }

}
