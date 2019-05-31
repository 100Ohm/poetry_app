package xyz.a100ohm.poems.presenter;

import xyz.a100ohm.poems.presenter.presenterinterface.PresenterEveryDay;
import xyz.a100ohm.poems.view.viewinterface.ViewEveryDay;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/5/30 13:36</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/30] [一百欧姆][创建文件]
 */
public class EveryDayPresenter implements PresenterEveryDay {

    //一个view对象，要小心内存泄漏
    private ViewEveryDay view;

    public EveryDayPresenter(ViewEveryDay view) {
        this.view = view;
    }

    @Override
    public void requestReflashCard(boolean isFirst) {

    }
}
