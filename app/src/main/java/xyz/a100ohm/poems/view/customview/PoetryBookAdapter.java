package xyz.a100ohm.poems.view.customview;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xyz.a100ohm.poems.R;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/5/31 20:10</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/5/31] [一百欧姆][诗集（若干本古书）列标的adapter]
 */
public class PoetryBookAdapter extends RecyclerView.Adapter<PoetryBookAdapter.ViewHolder>{

    private int size;

    public PoetryBookAdapter(int size) {
        this.size = size;
    }

    @NonNull
    @Override
    public PoetryBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.poetry_bookrack, viewGroup, false);
        if(Build.VERSION.SDK_INT > 21)
            ((ImageView)view.findViewById(R.id.bookrack_image)).setImageTintList(
                    ColorStateList.valueOf(ContextCompat.getColor
                            (viewGroup.getContext(),R.color.white_100)));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PoetryBookAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
