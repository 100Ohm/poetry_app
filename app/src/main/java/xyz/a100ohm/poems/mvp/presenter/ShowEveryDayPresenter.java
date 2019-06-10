package xyz.a100ohm.poems.mvp.presenter;

import io.realm.RealmList;
import io.realm.RealmResults;
import xyz.a100ohm.poems.mvp.model.AppModel;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetrySentence;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetryTranslate;
import xyz.a100ohm.poems.mvp.model.modelinterface.ModelShowEveryDayDetailInterface;
import xyz.a100ohm.poems.mvp.presenter.presenterinterface.PresenterShowEveryDayDetailInterface;
import xyz.a100ohm.poems.mvp.viewinterface.ViewShowEveryDayDetailInterface;
import xyz.a100ohm.poems.utils.PoetryUtils;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/10 19:41</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/10] [一百欧姆][显示一句诗的Presenter]
 */
public class ShowEveryDayPresenter implements PresenterShowEveryDayDetailInterface {

    private ViewShowEveryDayDetailInterface view;

    public ShowEveryDayPresenter(ViewShowEveryDayDetailInterface view) {
        this.view = view;
    }

    @Override
    public void requestEveryDayDetail(final String jrscId) {
        AppModel.getInstance().requestPoetryDetail(jrscId, new ModelShowEveryDayDetailInterface.DetailCallback() {
            @Override
            public void onFoundData(RealmResults<DBPoetrySentence> list) {
                if(list.size() == 0) {
                    view.showError("找不到这句诗, id为" + jrscId);
                } else {
                    DBPoetrySentence poetrySentence = list.first();
                    RealmList<DBPoetryTranslate> translates = poetrySentence.getTranslates();
                    String[] transArr = new String[translates.size()];
                    for(int i = 0; i < transArr.length; i ++) {
                        if (translates.get(i) != null) {
                            transArr[i] = translates.get(i).getContent();
                        }
                    }
                    view.showEveryDayPoetry(
                            poetrySentence.getTitle(),
                            poetrySentence.getDynasty(),
                            poetrySentence.getAuthor(),
                            PoetryUtils.formatPoetrySentence(poetrySentence.getContent()),
                            transArr
                    );
                }
            }
        });
    }
}
