package xyz.a100ohm.poems.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.a100ohm.poems.R;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/7 3:42</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/7] [一百欧姆][主页菜单的作诗模块]
 */
public class MainTabCreationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tab_creation, container, false);
        return view;
    }
}
