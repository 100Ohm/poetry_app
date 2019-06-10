package xyz.a100ohm.poems.mvp.model.beans.localdb;


import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/9 19:12</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/9] [一百欧姆][DBUser，Realm的Beans用来缓存一些用户资料，免得总是进行网络请求浪费流量]
 */

@RealmClass
public class DBUser implements RealmModel {
    @PrimaryKey
    private String id;

    public DBUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
