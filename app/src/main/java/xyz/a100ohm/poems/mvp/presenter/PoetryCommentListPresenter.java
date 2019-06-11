package xyz.a100ohm.poems.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import xyz.a100ohm.poems.mvp.model.beans.bomb.PoetryComment;
import xyz.a100ohm.poems.mvp.presenter.presenterinterface.PresenterPoetryCommentListInterface;
import xyz.a100ohm.poems.mvp.view.ViewPoetryCommentListInterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 20:18</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][创建文件]
 */
public class PoetryCommentListPresenter implements PresenterPoetryCommentListInterface {

    ViewPoetryCommentListInterface poetryView;

    public PoetryCommentListPresenter(ViewPoetryCommentListInterface poetryView) {
        this.poetryView = poetryView;
    }

    @Override
    public void refresh() {
        ViewPoetryCommentListInterface.PoetryComments p
                = new ViewPoetryCommentListInterface.PoetryComments();
        p.userName = "欧姆";
        p.content = "这首诗写得不错啊";
        p.quoteContent = "假装这是一首诗";
        p.quoteTitle = "假诗";
        p.quoteAuthor = "假作者";
        p.time = "现在";
        p.source = "喵星";
        p.views = 2;
        p.commentsNum = 0;
        p.likes = 3;
        List<ViewPoetryCommentListInterface.PoetryComments> list
                = new ArrayList<ViewPoetryCommentListInterface.PoetryComments>();
        list.add(p);
        poetryView.addPoetryComment(0,0, list);
    }

    @Override
    public void newPage() {

    }
}
