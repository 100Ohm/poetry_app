package xyz.a100ohm.poems.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.jinrishici.sdk.android.JinrishiciClient;
import com.jinrishici.sdk.android.listener.JinrishiciCallback;
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException;
import com.jinrishici.sdk.android.model.PoetySentence;

import xyz.a100ohm.poems.model.AppModel;
import xyz.a100ohm.poems.model.beans.localdb.DBPoetrySentence;
import xyz.a100ohm.poems.presenter.presenterinterface.PresenterEveryDayInterface;
import xyz.a100ohm.poems.utils.L;
import xyz.a100ohm.poems.view.viewinterface.ViewEveryDay;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: EveryDayPresenter.java </p>
 * <p>创建时间: 2019/5/30 13:36</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/30] [一百欧姆][每日诗句的Presenter，我觉得应该不用写model了吧，毕竟是直接用SDK的]
 */
public class EveryDayPresenter implements PresenterEveryDayInterface {

    //持有一个view对象，要小心内存泄漏
    private ViewEveryDay view;

    /** 是否第一次*/
    private boolean isFirst = true;

    public EveryDayPresenter(ViewEveryDay view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void requestReflashCard(final View cardview) {
        if(isFirst) {
            isFirst = false;
            AppModel.getInstance().requestPoetrySentence(3, new AppModel.RequestPoetrySentenceCallBack() {
                @Override
                public void onfoundPoetrySentence(DBPoetrySentence[] sentences) {
                    L.d("本地查询成功，第一句："+sentences[0].getContent());
                    if(sentences.length != 3)
                        return;
                    String[] content = new String[]{
                            formatPoetrySentence(sentences[0].getContent()),
                            formatPoetrySentence(sentences[1].getContent()),
                            formatPoetrySentence(sentences[2].getContent())};
                    String[] author = new String[]{
                            sentences[0].getAuthor(),
                            sentences[1].getAuthor(),
                            sentences[2].getAuthor()};
                    String[] id = new String[]{
                            sentences[0].getJrscId(),
                            sentences[1].getJrscId(),
                            sentences[2].getJrscId()};
                    view.displayCard(cardview, content, author,id);
                }
            });
        } else {
            JinrishiciClient client = JinrishiciClient.getInstance();
            client.getOneSentenceBackground(new JinrishiciCallback() {
                @Override
                public void done(PoetySentence poetySentence) {
                    //输出一下
                    L.e(poetySentence.getData().getId());
                    L.e(poetySentence.getData().getCacheAt());
                    L.e(poetySentence.getData().getContent());
                    L.e(poetySentence.getData().getOrigin().getTitle());
                    L.e(poetySentence.getData().getOrigin().getAuthor());
                    if(poetySentence.getData().getOrigin().getTranslate()!= null)
                        for(String s:poetySentence.getData().getOrigin().getTranslate())
                            L.e(s);
                    L.e(poetySentence.getData().getOrigin().getDynasty());
                    String content = formatPoetrySentence(poetySentence.getData().getContent());
                    //显示诗句
                    view.displayCard(cardview, new String[]{content},
                            new String[]{poetySentence.getData().getOrigin().getAuthor()},
                            new String[]{poetySentence.getData().getId()});
                    //缓存到本地数据库
                    AppModel.getInstance().savePoetrySentence(poetySentence);
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void error(JinrishiciRuntimeException e) {
                    L.w("error: code = " + e.getCode() + " message = " + e.getMessage());
                    AppModel.getInstance().requestPoetrySentence(1, new AppModel.RequestPoetrySentenceCallBack() {
                        @Override
                        public void onfoundPoetrySentence(DBPoetrySentence[] sentences) {
                            L.d("本地查询成功，"+sentences[0].getContent());
                            if(sentences.length == 0)
                                return;
                            String[] content = new String[]{formatPoetrySentence(sentences[0].getContent())};
                            String[] author = new String[]{sentences[0].getAuthor()};
                            String[] id = new String[]{sentences[0].getJrscId()};
                            view.displayCard(cardview, content, author, id);
                        }
                    });
                }
            });
        }
    }

    /**
     * 将诗句转换为适合显示的格式
     * @param content 待转换的诗句
     * @return 转换后的诗句
     */
    private String formatPoetrySentence(String content) {
        if(content == null)
            return null;
        return content.replace("，", "\n")
                .replace("。", "")
                .replace("、", "\n");
    }
}
