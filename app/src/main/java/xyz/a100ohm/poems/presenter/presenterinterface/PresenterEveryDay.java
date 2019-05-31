package xyz.a100ohm.poems.presenter.presenterinterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/5/30 11:32</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/30] [一百欧姆][创建文件]
 * [1][2019/5/30] [一百欧姆][每日一句的presenter接口]
 */
public interface PresenterEveryDay {
    /**
     * 申请刷新卡片。
     * @param isFirst 是否第一次请求。如果是true，回调ViewEveryDay接口的时候返回三句诗，如果是false，返回一句
     */
    void requestReflashCard(boolean isFirst);
}
