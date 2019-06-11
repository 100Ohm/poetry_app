package xyz.a100ohm.poems.mvp.model.beans.bomb;

import cn.bmob.v3.BmobObject;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 10:53</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][用户诗评,引用一些诗句,可以是每日一句的诗,也可以是其他]
 */
public class PoetryComment extends BmobObject {
    /** 所属的动态*/
    private Trend trend;
    /** 引用诗句类型,1表示是念诗用户的诗句,2表示是用户自己输入的诗句(无法查询资料),3表示是每日一句的诗句 */
    private int type;
    /** 引用诗句的id,如果是每日一句,则是每日一句的jrscId */
    private String quoteId;
    /** 评论内容*/
    private String content;

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
