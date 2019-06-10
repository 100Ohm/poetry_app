package xyz.a100ohm.poems.mvp.viewinterface;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/10 19:30</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/10] [一百欧姆][点击诗词卡片之后显示Activity的View接口]
 */
public interface ViewShowEveryDayDetailInterface {
    /**
     * 显示一句诗的详情。
     * @param title 诗的标题
     * @param dynasty 朝代
     * @param author 作者
     * @param content 内容
     * @param translates 翻译
     */
    void showEveryDayPoetry(String title, String dynasty, String author,
                            String content, String[] translates);

    /**
     * 显示失败。
     * @param result 失败原因。
     */
    void showError(String result);
}
