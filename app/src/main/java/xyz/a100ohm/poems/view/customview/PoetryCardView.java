package xyz.a100ohm.poems.view.customview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.utils.DisplayUtil;
import xyz.a100ohm.poems.utils.L;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/8 14:56</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/8] [一百欧姆][自定义的诗词卡片视图]
 */
public class PoetryCardView extends FrameLayout {

    //诗词卡片的位置
    float x0, y0, z0, x1, y1, z1, x2, y2, z2;

    public PoetryCardView(@NonNull Context context) {
        this(context, null);
    }

    public PoetryCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //添加三个view到界面中
        View view1 = View.inflate(context, R.layout.cardview_poetry, null);
        View view2 = View.inflate(context, R.layout.cardview_poetry, null);
        View view3 = View.inflate(context, R.layout.cardview_poetry, null);
        addView(view1);
        addView(view2);
        addView(view3);

        //安卓版本号大于21的话，三个view是CardView，需要设置Z高度
        //安卓版本低于21的话，三个view是普通view，只是设置了阴影，所以不用设置Z高度
        if(android.os.Build.VERSION.SDK_INT >= 21) {
            z1 = 10f;
            z2 = 20f;
            view2.setZ(z1);
            view3.setZ(z2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PoetryCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PoetryCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //添加三个view到界面中
        View view1 = View.inflate(context, R.layout.cardview_poetry, null);
        View view2 = View.inflate(context, R.layout.cardview_poetry, null);
        View view3 = View.inflate(context, R.layout.cardview_poetry, null);
        addView(view1);
        addView(view2);
        addView(view3);

        view2.setZ(10f);
        view3.setZ(20f);
    }

    //在onLayout中设置卡片位置
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        float midX = getMeasuredWidth()/2f,
                midY = getMeasuredHeight()/2f;
        float childWidthHalf = getChildAt(0).getWidth()/2f,
                childHeightHalf = getChildAt(0).getHeight()/2f;
        float midCardX = midX - childWidthHalf, midCardY = midY - childHeightHalf;

        // TODO: 2019/4/9 20dp最好转换成资源文件的数值
        //设置卡片位置
        int dp = 8;
        x0 = midCardX + DisplayUtil.dip2px(getContext(), dp);
        y0 = midCardY + DisplayUtil.dip2px(getContext(), dp);
        x1 = midCardX;
        y1 = midCardY;
        x2 = midCardX - DisplayUtil.dip2px(getContext(), dp);
        y2 = midCardY - DisplayUtil.dip2px(getContext(), dp);

        //底层卡片
        getChildAt(0).setX(x0);
        getChildAt(0).setY(y0);
        //中层卡片
        getChildAt(1).setX(x1);
        getChildAt(1).setY(y1);
        //顶层卡片
        getChildAt(2).setX(x2);
        getChildAt(2).setY(y2);

        L.d("PoetryCardView.onLayout()");
    }

    //在onMeasure中设置卡片大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setAllCardSize();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //设置所有卡片的大小
    private void setAllCardSize() {
        if(getChildCount() > 0){
            for(int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                ViewGroup.LayoutParams para = view.getLayoutParams();
                para.width = (int)Math.round(getMeasuredWidth() * 0.85);//修改宽度
                para.height = (int)Math.round(getMeasuredHeight() * 0.85);//修改高度
                view.setLayoutParams(para); //设置修改后的布局。
            }
        }
    }

    //卡片点击回调
    private static class onClickCardView implements OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO: 2019/4/9 待设置，是点击view的响应。如果解决不了滑动冲突的问题，就使用onClick来刷新
            // TODO: 2019/4/9 待转移，这是设置动画的代码
//            Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.anim_poetry_card);
//            getChildAt(0).startAnimation(animation);
//            getChildAt(1).startAnimation(animation);
        }
    }

}
