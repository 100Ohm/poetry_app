package xyz.a100ohm.poems.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.adapterandview.MyPoetryListAdapter;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/7 13:55</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/7] [一百欧姆][我的诗集Fragment]
 */
public class MyPoetryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_poetry, container, false);
        //诗集recyclerView
        RecyclerView r = (RecyclerView) view.findViewById(R.id.recycler_view_my_poetry);
        StaggeredGridLayoutManager sgm = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        sgm.setOrientation(LinearLayoutManager.VERTICAL);
        r.setLayoutManager(sgm);
        MyPoetryListAdapter ad = new MyPoetryListAdapter(16);
        r.setAdapter(ad);
        return view;
    }
}
