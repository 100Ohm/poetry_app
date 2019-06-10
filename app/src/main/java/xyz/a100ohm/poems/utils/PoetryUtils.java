package xyz.a100ohm.poems.utils;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/10 22:35</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/10] [一百欧姆][创建文件]
 */
public class PoetryUtils {
    /**
     * 将诗句转换为适合显示的格式
     * @param content 待转换的诗句
     * @return 转换后的诗句
     */
    public static String formatPoetrySentence(String content) {
        if(content == null)
            return null;
        return content.replace("，", "\n")
                .replace("。", "")
                .replace("、", "\n");
    }
}
