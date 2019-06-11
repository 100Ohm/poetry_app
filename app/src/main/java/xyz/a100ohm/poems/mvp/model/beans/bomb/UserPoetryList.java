package xyz.a100ohm.poems.mvp.model.beans.bomb;

import cn.bmob.v3.BmobObject;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 11:00</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][用户的诗集]
 */
public class UserPoetryList extends BmobObject {
    /** 诗集名字*/
    private String ListName;
    /** 所属用户*/
    private User user;

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
