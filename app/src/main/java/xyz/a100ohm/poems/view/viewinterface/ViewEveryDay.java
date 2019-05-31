package xyz.a100ohm.poems.view.viewinterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/5/30 11:27</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/30] [一百欧姆][创建文件]
 * [1][2019/5/30] [一百欧姆][这个是每日一句的view接口]
 */
public interface ViewEveryDay {
    /**
     * 让卡片显示诗句
     * @param poetry 回调返回的诗句，第一二次需要三句诗，第二次及以后需要一句诗
     */
    void displayCard(String[] poetry);
}
