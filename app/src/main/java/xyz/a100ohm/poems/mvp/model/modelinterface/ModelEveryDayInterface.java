package xyz.a100ohm.poems.mvp.model.modelinterface;

import com.jinrishici.sdk.android.model.PoetySentence;

import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetrySentence;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/9 21:18</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/9] [一百欧姆][创建文件]
 */
public interface ModelEveryDayInterface {
    /**
     * 后台开一个线程存储今日诗词的一首诗
     * @param poetySentence 今日诗词的一首诗
     */
    void savePoetrySentence(PoetySentence poetySentence);

    /**
     * 请求若干句随机诗句
     * @param num 诗句数量
     */
    void requestPoetrySentence(int num, PoetrySentenceCallBack callBack);

    /**
     * 请求获取诗句的回调接口
     */
    interface PoetrySentenceCallBack {
        void onFoundData(DBPoetrySentence[] sentences);
    }
}
