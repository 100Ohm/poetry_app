package xyz.a100ohm.poems.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.activity.ShowEveryDayDetailActivity;
import xyz.a100ohm.poems.adapterandview.PoetryCardView;
import xyz.a100ohm.poems.mvp.presenter.EveryDayPresenter;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/7 3:42</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/7] [一百欧姆][主页的每日一句模块]
 */
public class MainTabEveryDayFragment extends Fragment {
    //根布局
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            return mView;
        }
        View rootView = null;
        rootView = inflater.inflate(R.layout.fragment_main_tab_every_day, container, false);
        mView = rootView;
        //设置view的Presenter
        PoetryCardView card = (PoetryCardView) mView.findViewById(R.id.poetry_card_view);
        card.setPresenter(new EveryDayPresenter(card));
        card.addCardOnClickListener(new PoetryCardView.OnClickListener() {
            @Override
            public void onClick(String poetryId) {
                ShowEveryDayDetailActivity.startActivity(getContext(), poetryId);
            }
        });
        return rootView;
    }
}
