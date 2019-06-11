package xyz.a100ohm.poems.adapterandview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.mvp.view.ViewPoetryCommentListInterface;
import xyz.a100ohm.poems.utils.L;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/5/31 11:49</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/31] [一百欧姆][创建文件]
 * [1][2019/5/31] [一百欧姆][这是诗文评的adapter,创作中心应该也能使用这个]
 */
public class PoetryCommentAdapter extends RecyclerView.Adapter<PoetryCommentAdapter.ViewHolder> {

    //诗文评对象
    private List<ViewPoetryCommentListInterface.PoetryComments> poetryComments;

    public PoetryCommentAdapter(List<ViewPoetryCommentListInterface.PoetryComments> poetryComments) {
        this.poetryComments = poetryComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_comment, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewPoetryCommentListInterface.PoetryComments poetryComment = poetryComments.get(i);
        if(poetryComment.head == null)
            viewHolder.head.setImageResource(R.drawable.head_portrait);
        else
            viewHolder.head.setImageBitmap(poetryComment.head);
        viewHolder.userName.setText(poetryComment.userName);
        viewHolder.content.setText(poetryComment.content);
        viewHolder.quoteContent.setText(poetryComment.quoteContent);
        viewHolder.quoteTitleAndAuthor.setText("——《" + poetryComment.quoteTitle + "》" + poetryComment.quoteAuthor);
        if(poetryComment.source != null)
            viewHolder.timeAndSource.setText(poetryComment.time + "    " + poetryComment.source);
        else
            viewHolder.timeAndSource.setText(poetryComment.time);
        viewHolder.views.setText("" + poetryComment.views);
        viewHolder.commentsNum.setText("" + poetryComment.commentsNum);
        viewHolder.likes.setText("" + poetryComment.likes);
        if(poetryComment.commentsNum == 0)
            viewHolder.commentsMainLayout.setVisibility(View.GONE);
        else {
            viewHolder.commentsMainLayout.setVisibility(View.VISIBLE);
            for(int n = 0; n < 3; n++) {
                if(poetryComment.commets[n] != null && poetryComment.commetAuthor[n] != null){
                    viewHolder.commentAuthor[n].setText(poetryComment.commetAuthor[n]);
                    viewHolder.comments[n].setText(poetryComment.commets[n]);
                } else {
                    viewHolder.commentsLayout[n].setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return poetryComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView head;
        //发表动态的用户名称
        TextView userName, content, quoteContent,
                quoteTitleAndAuthor, timeAndSource,
                views, commentsNum, likes;
        View commentsMainLayout;
        View[] commentsLayout = new View[3];
        TextView[] comments = new TextView[3];
        TextView[] commentAuthor = new TextView[3];

        public ViewHolder(@NonNull View view) {
            super(view);
            head = view.findViewById(R.id.comment_head);
            userName = view.findViewById(R.id.comment_username);
            content = view.findViewById(R.id.comment_text);
            quoteContent = view.findViewById(R.id.comment_quote);
            quoteTitleAndAuthor = view.findViewById(R.id.comment_title_and_author);
            timeAndSource = view.findViewById(R.id.comment_time_and_source);
            views = view.findViewById(R.id.comment_views);
            commentsNum = view.findViewById(R.id.comment_comment_num);
            likes = view.findViewById(R.id.comment_likes);
            commentsMainLayout = view.findViewById(R.id.comment_comment_main_layout);
            commentsLayout[0] = view.findViewById(R.id.comment_comment_layout1);
            commentsLayout[1] = view.findViewById(R.id.comment_comment_layout2);
            commentsLayout[2] = view.findViewById(R.id.comment_comment_layout3);
            comments[0] = view.findViewById(R.id.comment_comment_text1);
            comments[1] = view.findViewById(R.id.comment_comment_text2);
            comments[2] = view.findViewById(R.id.comment_comment_text3);
            commentAuthor[0] = view.findViewById(R.id.comment_comment_username1);
            commentAuthor[1] = view.findViewById(R.id.comment_comment_username2);
            commentAuthor[2] = view.findViewById(R.id.comment_comment_username3);
        }
    }

    /**
     * 插入新数据
     */
    public void insertData(List<ViewPoetryCommentListInterface.PoetryComments> insertedData) {
        if (insertedData == null) {
            L.e("insertData(list) list is null");
            return;
        }
        int index = 0;
        for (ViewPoetryCommentListInterface.PoetryComments data : insertedData) {
            if (data != null) {
                poetryComments.add(data);
                index++;
            }
        }
        notifyItemRangeInserted(poetryComments.size() - index, index);
    }

    /**
     * 替换全部数据
     * @param poiItemList 替换数据
     */
    public void replaceData(List<ViewPoetryCommentListInterface.PoetryComments> poiItemList) {
        if (poiItemList != null) {
            int previousSize = poetryComments.size();
            poetryComments.clear();
            notifyItemRangeRemoved(0, previousSize);
            poetryComments.addAll(poiItemList);
            notifyItemRangeInserted(0, poiItemList.size());
            notifyItemRangeChanged(0,poetryComments.size());//通知数据与界面重新绑定
        }
    }
}
