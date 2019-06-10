package xyz.a100ohm.poems.mvp.model.beans.localdb;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: DBPoetryTranslate.java.java </p>
 * <p>创建时间: 2019/6/9 20:48</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/9] [一百欧姆][一句诗的翻译]
 */
public class DBPoetryTranslate extends RealmObject {
    @PrimaryKey
    private int id;
    private String content;

    public DBPoetryTranslate() {
    }

    public static int getIncrementaID(){//id自增
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DBPoetryTranslate> list = realm.where(DBPoetryTranslate.class).findAll().sort("id");
        if(list.size() == 0)
            return 0;
        int lastid = list.last().getId();
        return ++lastid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
