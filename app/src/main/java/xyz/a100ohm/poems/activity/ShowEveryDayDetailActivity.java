package xyz.a100ohm.poems.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.mvp.presenter.ShowEveryDayPresenter;
import xyz.a100ohm.poems.mvp.presenter.presenterinterface.PresenterShowEveryDayDetailInterface;
import xyz.a100ohm.poems.mvp.viewinterface.ViewShowEveryDayDetailInterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: MainActivity.java </p>
 * <p>创建时间: 2019/4/4 0:36</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019.4.4] [一百欧姆][每日一句显示诗词详情的Activity]
 */
public class ShowEveryDayDetailActivity extends AppCompatActivity implements ViewShowEveryDayDetailInterface {

    private String showPoetryJrscId;

    private PresenterShowEveryDayDetailInterface presenter;

    private TextView mTitle;
    private TextView mAuthor;
    private TextView mDynasty;
    private TextView mContent;
    private TextView mTranslates;
    private View mProgess;

    public static void startActivity(Context context, String showPoetryJrscId) {
        Intent intent = new Intent(context, ShowEveryDayDetailActivity.class);
        intent.putExtra("id", showPoetryJrscId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_every_day_detail);
        showPoetryJrscId = getIntent().getStringExtra("id");
        presenter = new ShowEveryDayPresenter(this);
        initViews();
        presenter.requestEveryDayDetail(showPoetryJrscId);
    }

    private void initViews() {
        mTitle = findViewById(R.id.show_one_poetry_title);
        mAuthor = findViewById(R.id.show_one_poetry_author);
        mDynasty = findViewById(R.id.show_one_poetry_dynasty);
        mContent = findViewById(R.id.show_one_poetry_poem);
        mTranslates = findViewById(R.id.show_one_poetry_translate);
        mProgess = findViewById(R.id.show_one_poetry_progress);
    }

    @Override
    public void showEveryDayPoetry(final String title, final String dynasty, final String author, final String content, final String[] translates) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTitle.setText(title);
                mDynasty.setText("[朝代] "+dynasty);
                mAuthor.setText("[作者] "+author);
                mContent.setText(content);
                if(translates == null || translates.length == 0) {
                    mTranslates.setText("暂无翻译。");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for(String str:translates)
                        sb.append(str + "\n");
                    mTranslates.setText(sb.subSequence(0,sb.length()-2).toString());
                }
                if(dynasty==null)
                    mDynasty.setVisibility(View.GONE);
                else
                    mDynasty.setVisibility(View.VISIBLE);
                if(mAuthor==null)
                    mAuthor.setVisibility(View.GONE);
                else
                    mAuthor.setVisibility(View.VISIBLE);
                mProgess.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showError(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mContent, "加载失败, " + result, Snackbar.LENGTH_SHORT).show();
                mProgess.setVisibility(View.GONE);
            }
        });
    }
}
