package xyz.a100ohm.poems.model.beans.localdb;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/9 20:44</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/9] [一百欧姆][创建文件]
 */
@RealmClass
public class DBPoetrySentence implements RealmModel {
    @PrimaryKey
    private int id;
    @Index
    private String jrscId;
    private String cacheAt;
    private String content;
    private String title;
    private String author;
    private String dynasty;
    private RealmList<DBPoetryTranslate> translates;

    public DBPoetrySentence() {
    }

    public static int getIncrementaID(){//id自增
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DBPoetrySentence> list = realm.where(DBPoetrySentence.class).findAll().sort("id");
        if(list.size() == 0)
            return 1;
        int lastid = list.last().getId();
        return ++lastid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCacheAt() {
        return cacheAt;
    }

    public void setCacheAt(String cacheAt) {
        this.cacheAt = cacheAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public RealmList<DBPoetryTranslate> getTranslates() {
        return translates;
    }

    public void setTranslates(RealmList<DBPoetryTranslate> translates) {
        this.translates = translates;
    }

    public String getJrscId() {
        return jrscId;
    }

    public void setJrscId(String jrscId) {
        this.jrscId = jrscId;
    }
}
