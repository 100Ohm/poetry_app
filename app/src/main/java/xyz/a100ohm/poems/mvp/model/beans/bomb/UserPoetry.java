package xyz.a100ohm.poems.mvp.model.beans.bomb;

import cn.bmob.v3.BmobObject;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 1:47</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][用户写的诗]
 */
public class UserPoetry extends BmobObject {
    /** 所属的动态*/
    private Trend trend;
    /** 诗句所属诗集*/
    private UserPoetryList list;
    /** 诗句标题*/
    private String title;
    /** 诗句内容*/
    private String content;

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public UserPoetryList getList() {
        return list;
    }

    public void setList(UserPoetryList list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
