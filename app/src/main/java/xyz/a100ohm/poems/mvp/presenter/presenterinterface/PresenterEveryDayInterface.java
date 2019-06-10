package xyz.a100ohm.poems.mvp.presenter.presenterinterface;

import android.view.View;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: PresenterEveryDayInterface.java </p>
 * <p>创建时间: 2019/5/30 11:32</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/30] [一百欧姆][创建文件]
 * [1][2019/5/30] [一百欧姆][每日一句的presenter接口]
 */
public interface PresenterEveryDayInterface {

    //====================让View调用的方法========================
    /**
     * 申请刷新卡片。会判断是否第一次请求。
     * 如果是true，回调ViewEveryDay接口的时候返回三句诗，如果是false，返回一句。
     * @param view 请求显示的诗句的view，用于辨识是哪个view请求显示诗句
     */
    void requestReflashCard(View view);

    //====================让Model回调的方法========================
}
