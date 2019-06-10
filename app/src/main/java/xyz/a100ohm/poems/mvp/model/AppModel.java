package xyz.a100ohm.poems.mvp.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.jinrishici.sdk.android.model.PoetySentence;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import xyz.a100ohm.poems.application.MyAppliction;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetrySentence;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetryTranslate;
import xyz.a100ohm.poems.mvp.model.modelinterface.ModelEveryDayInterface;
import xyz.a100ohm.poems.mvp.model.modelinterface.ModelShowEveryDayDetailInterface;
import xyz.a100ohm.poems.utils.L;
import xyz.a100ohm.poems.utils.SharedPreferencesUtils;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/9 21:21</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/9] [一百欧姆][整个应用程序的Model,处理关于本地数据存储的问题]
 */
public class AppModel implements ModelEveryDayInterface, ModelShowEveryDayDetailInterface {
    private static AppModel appModel;
    private Realm realm;

    public AppModel() {
        realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {//初始化数据库
                int num = SharedPreferencesUtils.getInt(MyAppliction.getContext(),
                        "sentence num", 0);
                if(num == 0) {
                    DBPoetrySentence[] sentences = new DBPoetrySentence[4];
                    sentences[0] = realm.createObject(DBPoetrySentence.class,
                            DBPoetrySentence.getIncrementaID());
                    sentences[0].setJrscId("5b8b9572e116fb3714e6fa3c");
                    sentences[0].setContent("玉户帘中卷不去，捣衣砧上拂还来。");
                    sentences[0].setTitle("春江花月夜");
                    sentences[0].setAuthor("张若虚");
                    sentences[1] = realm.createObject(DBPoetrySentence.class,
                            DBPoetrySentence.getIncrementaID());
                    sentences[1].setJrscId("5b8b9572e116fb3714e6fa3b");
                    sentences[1].setContent("可怜楼上月徘徊，应照离人妆镜台。");
                    sentences[1].setTitle("春江花月夜");
                    sentences[1].setAuthor("张若虚");
                    sentences[2] = realm.createObject(DBPoetrySentence.class,
                            DBPoetrySentence.getIncrementaID());
                    sentences[2].setJrscId("5b8b9572e116fb3714e6fa39");
                    sentences[2].setContent("白云一片去悠悠，青枫浦上不胜愁。");
                    sentences[2].setTitle("春江花月夜");
                    sentences[2].setAuthor("张若虚");
                    sentences[3] = realm.createObject(DBPoetrySentence.class,
                            DBPoetrySentence.getIncrementaID());
                    sentences[3].setJrscId("5b8b9572e116fb3714e6fa3d");
                    sentences[3].setContent("此时相望不相闻，愿逐月华流照君。");
                    sentences[3].setTitle("春江花月夜");
                    sentences[3].setAuthor("张若虚");
                    realm.copyToRealmOrUpdate(sentences[0]);
                    realm.copyToRealmOrUpdate(sentences[1]);
                    realm.copyToRealmOrUpdate(sentences[2]);
                    realm.copyToRealmOrUpdate(sentences[3]);
                    SharedPreferencesUtils.putInt(MyAppliction.getContext(),
                            "sentence num", 4);
                }
            }
        });
    }

    public static AppModel getInstance() {
        if(appModel == null)
            synchronized (AppModel.class) {
                if (appModel == null)
                    appModel = new AppModel();
            }
        return appModel;
    }

    /**
     * {@inheritDoc}
     * @param poetySentence 今日诗词的一首诗
     */
    @Override
    public void savePoetrySentence(final PoetySentence poetySentence) {
        if(poetySentence == null)
            return;
        //不重复存储
        RealmResults<DBPoetrySentence> list = realm.where(DBPoetrySentence.class)
                .equalTo("jrscId", poetySentence.getData().getId()).findAll();
        if(list.isLoaded() && list.size() != 0)
            return;
        //开始存储
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                L.d("存储一句诗");
                DBPoetrySentence sentence = realm.createObject(DBPoetrySentence.class,
                        DBPoetrySentence.getIncrementaID());
                sentence.setJrscId(poetySentence.getData().getId());
                sentence.setContent(poetySentence.getData().getContent());
                sentence.setCacheAt(poetySentence.getData().getCacheAt());
                sentence.setAuthor(poetySentence.getData().getOrigin().getAuthor());
                sentence.setDynasty(poetySentence.getData().getOrigin().getDynasty());
                sentence.setTitle(poetySentence.getData().getOrigin().getTitle());

                //存储翻译
                if(poetySentence.getData().getOrigin().getTranslate()!= null) {
                    RealmList<DBPoetryTranslate> translates;
                    translates = new RealmList<>();
                    for (String s : poetySentence.getData().getOrigin().getTranslate()) {
                        DBPoetryTranslate translate = realm.createObject(DBPoetryTranslate.class,
                                DBPoetryTranslate.getIncrementaID());
                        translate.setContent(s);
                        realm.copyToRealmOrUpdate(translate);
                        translates.add(translate);
                    }
                    sentence.setTranslates(translates);
                }
                realm.copyToRealmOrUpdate(sentence);
                int num = SharedPreferencesUtils.getInt(MyAppliction.getContext(),
                        "sentence num", 0);
                SharedPreferencesUtils.putInt(MyAppliction.getContext(),
                        "sentence num", ++num);
                //L.d("sentence num：" + num);
            }
        });
    }
    /**
     * {@inheritDoc}
     * @param num 诗句数量
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void requestPoetrySentence(final int num, final PoetrySentenceCallBack callBack) {
        L.d("申请本地获取今日诗词");
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Random rand = new Random();
                DBPoetrySentence[] sentences = new DBPoetrySentence[num];
                int n = SharedPreferencesUtils.getInt(MyAppliction.getContext(),
                        "sentence num", 0);
                L.d("sentence num: " + n);
                if(n < num){
                    returnDefaultPoetry(num, callBack);
                    return;
                }
                for(int i = 0; i < num; i++) {//生成一个随机数
                    int id = rand.nextInt(n);
                    RealmResults<DBPoetrySentence> list = realm.where(DBPoetrySentence.class)
                            .equalTo("id", id)
                            .findAll();
                    if(list.size() != 0)
                        sentences[i] = list.get(0);
                    else {//不知道为什么会查不出结果，那就先返回默认的吧
                        returnDefaultPoetry(num, callBack);
                        return;
                    }
                }
                callBack.onFoundData(sentences);
            }
        });
    }

    /**
     * 返回默认的诗句
     * @param num 返回句数
     * @param callBack 获取诗句的callback
     */
    private void returnDefaultPoetry(int num, PoetrySentenceCallBack callBack){
        if(num == 3) {
            DBPoetrySentence[] sentences = new DBPoetrySentence[num];
            sentences[0] = new DBPoetrySentence();
            sentences[0].setJrscId("5b8b9572e116fb3714e6fa3c");
            sentences[0].setContent("玉户帘中卷不去，捣衣砧上拂还来。");
            sentences[0].setAuthor("张若虚");
            sentences[1] = new DBPoetrySentence();
            sentences[1].setJrscId("5b8b9572e116fb3714e6fa3b");
            sentences[1].setContent("可怜楼上月徘徊，应照离人妆镜台。");
            sentences[1].setAuthor("张若虚");
            sentences[2] = new DBPoetrySentence();
            sentences[2].setJrscId("5b8b9572e116fb3714e6fa39");
            sentences[2].setContent("白云一片去悠悠，青枫浦上不胜愁。");
            sentences[2].setAuthor("张若虚");
            callBack.onFoundData(sentences);
        } else if(num == 1) {
            DBPoetrySentence sentence = new DBPoetrySentence();
            sentence.setJrscId("5b8b9572e116fb3714e6fa3d");
            sentence.setContent("此时相望不相闻，愿逐月华流照君。");
            sentence.setAuthor("张若虚");
            callBack.onFoundData(new DBPoetrySentence[]{sentence});
        }
    }

    @Override
    public void requestPoetryDetail(final String jrscId, final DetailCallback callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<DBPoetrySentence> list = realm.where(DBPoetrySentence.class)
                        .equalTo("jrscId", jrscId).findAll();
                callback.onFoundData(list);
            }
        });
    }
}
