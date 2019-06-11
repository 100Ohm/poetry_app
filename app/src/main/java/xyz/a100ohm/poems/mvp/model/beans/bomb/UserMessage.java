package xyz.a100ohm.poems.mvp.model.beans.bomb;

import cn.bmob.v3.BmobObject;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 14:14</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][用户之间聊天的消息]
 */
public class UserMessage extends BmobObject {
    /** 这条消息所属的会话*/
    private Session session;
    /** 发送者*/
    private User from_user;
    /** 接收者*/
    private User to_user;
    /** 发送内容*/
    private String content;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getFrom_user() {
        return from_user;
    }

    public void setFrom_user(User from_user) {
        this.from_user = from_user;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
