package xyz.a100ohm.poems.mvp.model.beans.localdb;


import android.content.Context;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import xyz.a100ohm.poems.application.MyAppliction;
import xyz.a100ohm.poems.mvp.model.beans.bomb.User;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/9 19:12</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/9] [一百欧姆][DBUser，Realm的Beans用来缓存一些用户资料及头像，免得总是进行网络请求浪费流量]
 */

@RealmClass
public class DBUser implements RealmModel {
    @PrimaryKey
    private String objectId;
    private String username;
    private String email;
    private String mobilePhoneNumber;
    private String description;
    private String imageUrl;//头像的url路径
    private String imageFileUrl;//头像的File路径

    public DBUser() {}

    public DBUser(User user) {
        objectId = user.getObjectId();
        username = user.getUsername();
        email = user.getEmail();
        mobilePhoneNumber = user.getMobilePhoneNumber();
        description = user.getDescription();
        imageUrl = user.getImage().getUrl();
        imageFileUrl = user.getImage().getFileUrl(MyAppliction.getContext());
    }

    public static DBUser changeToDB(User user) {
        return new DBUser(user);
    }


}
