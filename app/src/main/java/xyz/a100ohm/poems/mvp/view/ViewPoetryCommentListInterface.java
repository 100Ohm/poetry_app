package xyz.a100ohm.poems.mvp.view;

import android.graphics.Bitmap;

import java.util.List;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 16:03</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][诗文评列表的显示接口]
 */
public interface ViewPoetryCommentListInterface {
    class PoetryComments {
        //这条诗文评的id,方便之后在本地数据库或网络查询
        public String objectId;
        //这条诗文引用的类型
        public int quoteType;
        //这条诗文引用的id
        public String QuoteId;
        //发表动态的用户名称
        public String userName;
        //用户头像
        public Bitmap head;
        //发表的诗评内容
        public String content;
        //引用诗的内容
        public String quoteContent;
        //引用诗的标题
        public String quoteTitle;
        //引用诗的作者
        public String quoteAuthor;
        //左下角时间
        public String time;
        //左下角来源
        public String source;
        //点赞数,评论数,点赞数
        public int views, commentsNum, likes;
        //这条动态的评论,最多三条
        public String[] commets;
        //上面评论的那些用户名称
        public String[] commetAuthor;
    }

    /**
     * 增加诗文评.
     * @param page 页数
     * @param pageLimit 这页的条数
     * @param list 要显示的列表
     */
    void addPoetryComment(int page, int pageLimit, List<PoetryComments> list);

    /**
     * 替换正在显示的诗文评.
     * @param page 页数,如果是刷新的话应该是从第一页开始,但是还是确认一下
     * @param pageLimit 这页的条数
     * @param list 要显示的列表
     */
    void reflashPoetryComment(int page, int pageLimit, List<PoetryComments> list);

    /**
     * 刷新失败
     * @param error 错误原因
     */
    void refreshFail(String error);
}
