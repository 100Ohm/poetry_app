package xyz.a100ohm.poems.mvp.model.modelinterface;

import io.realm.RealmResults;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetrySentence;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/10 22:08</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/10] [一百欧姆][创建文件]
 */
public interface DBModelShowEveryDayDetailInterface {
    interface DetailCallback {
        void onFoundData(RealmResults<DBPoetrySentence> list);
    }
    void requestPoetryDetail(String jrscId, DetailCallback callback);
}
