package xyz.a100ohm.poems.mvp.presenter.presenterinterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 17:15</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][显示诗文评列表的presenter]
 */
public interface PresenterPoetryCommentListInterface {
    /**
     * 界面下拉刷新.
     */
    void refresh();

    /**
     * 划到底部了,要获取新的数据.
     */
    void newPage();
}
