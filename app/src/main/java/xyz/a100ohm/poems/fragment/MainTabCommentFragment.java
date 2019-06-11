package xyz.a100ohm.poems.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.adapterandview.PoetryCommentAdapter;
import xyz.a100ohm.poems.mvp.presenter.PoetryCommentListPresenter;
import xyz.a100ohm.poems.mvp.presenter.presenterinterface.PresenterPoetryCommentListInterface;
import xyz.a100ohm.poems.mvp.view.ViewPoetryCommentListInterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/7 3:42</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/7] [一百欧姆][主页的诗文评模块]
 */
public class MainTabCommentFragment extends Fragment implements ViewPoetryCommentListInterface, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private PoetryCommentAdapter adapter;
    private PresenterPoetryCommentListInterface presenter;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tab_comment, container, false);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.comment_swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_comment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PoetryCommentAdapter(new ArrayList<PoetryComments>());
        recyclerView.setAdapter(adapter);

        presenter = new PoetryCommentListPresenter(this);
        presenter.refresh();

        return view;
    }

    @Override
    public void addPoetryComment(int page, int pageLimit, final List<PoetryComments> list) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.insertData(list);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void reflashPoetryComment(int page, int pageLimit, final List<PoetryComments> list) {
        if(getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.replaceData(list);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshFail(final String error) {
        if(getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(recyclerView, "刷新失败,原因:" + error, Snackbar.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        presenter.refresh();
    }
}
