package xyz.a100ohm.poems.mvp.model.beans.bomb;

import com.jinrishici.sdk.android.model.PoetySentence;

import java.util.List;

import cn.bmob.v3.BmobObject;
import io.realm.RealmList;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetrySentence;
import xyz.a100ohm.poems.mvp.model.beans.localdb.DBPoetryTranslate;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 10:51</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][每日一句的Bmob版,当用户需要写每日一句的诗评时,会存储倒Bomob中,便于用户写诗评后查找相应的资料]
 */
public class EveryDayPoetry extends BmobObject {
    /** 对应的今日诗词Id*/
    private String jrscId;
    /** 诗词内容*/
    private String content;
    private String title;
    private String author;
    private String dynasty;
    private String translate;

    //本地的数据转换成bmob的
    public EveryDayPoetry(DBPoetrySentence sentence) {
        jrscId = sentence.getJrscId();
        content = sentence.getContent();
        title = sentence.getTitle();
        author = sentence.getAuthor();
        dynasty = sentence.getDynasty();
        RealmList<DBPoetryTranslate> translates = sentence.getTranslates();
        if(translates == null || translates.size() == 0) {
            translate = "暂无翻译。";
        } else {
            StringBuilder sb = new StringBuilder();
            for(DBPoetryTranslate t : translates)
                sb.append(t.getContent() + "\n");
            translate = sb.subSequence(0,sb.length()-2).toString();
        }
    }

    //今日诗词的转换成bmob的
    public EveryDayPoetry(PoetySentence sentence) {
        jrscId = sentence.getData().getId();
        content = sentence.getData().getContent();
        title = sentence.getData().getOrigin().getTitle();
        author = sentence.getData().getOrigin().getAuthor();
        dynasty = sentence.getData().getOrigin().getDynasty();
        List<String> translates = sentence.getData().getOrigin().getTranslate();
        if(translates == null || translates.size() == 0) {
            translate = "暂无翻译。";
        } else {
            StringBuilder sb = new StringBuilder();
            for(String str:translates)
                sb.append(str + "\n");
            translate = sb.subSequence(0,sb.length()-2).toString();
        }
    }

    public String getJrscId() {
        return jrscId;
    }

    public void setJrscId(String jrscId) {
        this.jrscId = jrscId;
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

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}
