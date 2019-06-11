package xyz.a100ohm.poems.mvp.model.beans.bomb;

import cn.bmob.v3.BmobObject;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 11:14</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][对动态的评论]
 */
public class TrendComment extends BmobObject {
    /** 评论的用户*/
    private User user;
    /** 评论的动态的id*/
    private Trend trend;
    /** 评论的内容*/
    private String Content;
    /** 回复的评论*/
    private TrendComment fatherComment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public TrendComment getFatherComment() {
        return fatherComment;
    }

    public void setFatherComment(TrendComment fatherComment) {
        this.fatherComment = fatherComment;
    }
}
