package xyz.a100ohm.poems.view.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.a100ohm.poems.R;
import xyz.a100ohm.poems.presenter.presenterinterface.PresenterEveryDayInterface;
import xyz.a100ohm.poems.utils.L;
import xyz.a100ohm.poems.view.viewinterface.ViewEveryDay;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/4/8 14:56</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/4/8] [一百欧姆][自定义的诗词卡片视图，滑动可以刷新诗词]
 * [2][2019.4.6] [一百欧姆][尝试处理用户上划下滑手势]
 * [3][2019.5.29] [一百欧姆][完成滑动卡片的功能]
 */
public class PoetryCardView extends FrameLayout implements ViewEveryDay {

    /** 诗词卡片位置，用于动画*/
    float x0, y0, z0, x1, y1, z1, x2, y2, z2;

    /**卡片间隔位置*/
    private static final float CARD_VIEW_INTERVAL_PERCENT = 0.000046f;

    /** DragHelper*/
    //private ViewDragHelper mDragHelper;

    /** Animation*/
    //private ObjectAnimator animator;

    /** 观察者，用于检测是否收到点击*/
    private List<OnClickListener> onClickListeners;

    /** 获取诗句的Presenter*/
    private PresenterEveryDayInterface presenter;

    /** 子view*/
    private List<View> views = new ArrayList<>(4);

    public PoetryCardView(@NonNull Context context) {
        this(context, null);
    }

    public PoetryCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PoetryCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PoetryCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        removeAllViews();
        //添加三个view到界面中
        View view1 = View.inflate(context, R.layout.cardview_poetry, null);
        View view2 = View.inflate(context, R.layout.cardview_poetry, null);
        View view3 = View.inflate(context, R.layout.cardview_poetry, null);
        addView(view1);
        addView(view2);
        addView(view3);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        //安卓版本号大于21的话，三个view是CardView，需要设置Z高度
        //安卓版本低于21的话，三个view是普通view，只是设置了阴影，所以不用设置Z高度
        if(android.os.Build.VERSION.SDK_INT >= 21) {
            z0 = 5f;
            z1 = 10f;
            z2 = 12f;
            view1.setZ(z0);
            view2.setZ(z1);
            view3.setZ(z2);
        }
    }

    /**
     * 在onLayout中设置卡片位置
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        float midX = getMeasuredWidth()/2f,
                midY = getMeasuredHeight()/2f;
        float childWidthHalf = getChildAt(0).getWidth()/2f,
                childHeightHalf = getChildAt(0).getHeight()/2f;
        float midCardX = midX - childWidthHalf, midCardY = midY - childHeightHalf;

        //设置卡片位置
        //float dp = getResources().getDimension(R.dimen.card_view_interval);
        //x0 = midCardX + DisplayUtil.dip2px(getContext(), dp);
        final float in = getChildAt(0).getWidth()
                * getChildAt(0).getHeight()
                * CARD_VIEW_INTERVAL_PERCENT;
        x0 = midCardX + in;
        y0 = midCardY + in;
        x1 = midCardX;
        y1 = midCardY;
        x2 = midCardX - in;
        y2 = midCardY - in;

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

    /**
     * 在onMeasure中设置卡片大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setAllCardSize();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置所有卡片大小
     */
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

    /**
     * 是否拦截事件，我认为可以拦截。
     * @param ev 触摸事件
     * @return 是否拦截
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private float downY;//记录按下的事件位置
    private long downTime;//按下事件的时间
    private float startEventY;//按下并有移动后，开始移动的事件位置
    private float startCardY;//按下并有移动后，开始移动时的卡片位置
    private boolean isStart;//记录是否开始了滑动卡片
    private boolean isLockCardMove;//在播放动画时锁定触摸事件
    private float cardNowX, cardNowY;//用于判断卡片当前位置。因为用getY获取Y不及时，所以自己记录一下

    private static final float CARD_MOVE_START = 20f;//如果从down到move，用户移动的y到达这个值的大小，则会开始移动卡片
    private static final long CLICK_TIME = 200L;//如果从按下到弹起时间不超过这个值，则表示用户想要点击卡片
    private static float CARD_MOVE_DISTANCE = 250f;//如果移动卡片超过这个值，则会刷新卡片，否则卡片弹回

    /**
     * 触摸事件处理(似乎处理有个规律，必须消费了down事件，move事件和up事件才会有响应)。
     * 当按下时，记录事件y位置。
     * 移动时，不断更新和计算y移动距离。
     *     移动y距离到达一定距离后，视为滑动卡片，开始移动CardView。
     *     若移动y距离没有达到一定距离，视为取消，不移动CardView。
     * 释放时，计算卡片位移的距离：
     *     如果大于某一值，则卡片抛出，刷新卡片，后面的卡片位置移动；
     *     否则如果时间短，视为点击，打开诗句分享页面。（或直接做刷新）
     * @param ev 触摸事件
     * @return 返回true消费掉事件，否则继续传递事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev){
        if(isLockCardMove)
            return false;
        boolean isConsume = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(cardViewTopInclude(ev.getX(), ev.getY())) {
                    downY = ev.getY();
                    downTime = System.currentTimeMillis();
                    isConsume = true;//消费事件，以后才会有后续事件的调用
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isStart) {
                    //开始移动CardView
                    L.d("移动卡片");
                    View child = getChildAt(2);
                    float moveY = startEventY - ev.getY();
                    float newY = startCardY - moveY;
                    cardNowY = newY;
                    child.setY(newY);
                } else {
                    //计算距离是否达标，若达标，则开始移动卡片
                    float nowY = ev.getY();
                    if(Math.abs(nowY - downY) > CARD_MOVE_START){
                        isStart = true;
                        startEventY = nowY;
                        startCardY = cardNowY;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isStart) {
                    L.d("用户释放卡片");
                    releaseCard();
                    cancelTouchEvent();
                } else if(System.currentTimeMillis() - downTime < CLICK_TIME) {//视为点击
                    //处理点击事件
                    L.d("用户点击");
                    cancelTouchEvent();
                    // TODO: 2019/5/30  
                    notifyClickListeners("通知");
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                //收到这个消息代表tabLayout要消费了这一序列的事件（因为用户横向滑动了）
                L.d("手势取消");
                releaseCard();
                cancelTouchEvent();
                break;
            default:
                releaseCard();
                cancelTouchEvent();
        }
        return isConsume;
    }
    //取消卡片移动
    private void cancelTouchEvent(){
        downY = 0f;
        downTime = 0L;
        startEventY = 0f;
        isStart = false;
        //重置
        startCardY = 0f;
        cardNowX = 0;
        cardNowY = 0;
    }

    //释放卡片
    //若达到一定距离，则释放并刷新卡片；
    //否则卡片弹回原位。
    private void releaseCard(){
        isLockCardMove = true;

        if(Math.abs(cardNowY - startCardY) < CARD_MOVE_DISTANCE){//没达到距离，弹回原位
            float jumpY = (cardNowY - startCardY)/6;
            float jumpY2 = jumpY/2;
            ObjectAnimator an = ObjectAnimator.ofFloat(getChildAt(2), "Y", cardNowY,
                y2 - jumpY, y2 + jumpY2, y2)
                .setDuration(400);
            an.start();
            an.addListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animation) {}
                public void onAnimationEnd(Animator animation) {
                    isLockCardMove = false;
                }
                public void onAnimationCancel(Animator animation) {}
                public void onAnimationRepeat(Animator animation) {}
            });
        } else {//达到距离，弹走
            boolean movwDown = cardNowY - startCardY > 0;//向下移动
            ObjectAnimator topCardAnim;//最上面的卡片的运动
            //最上面的卡片弹走
            if(!movwDown) {//向上的动画
                topCardAnim = ObjectAnimator.ofFloat(getChildAt(2),
                        "Y", cardNowY, -getChildAt(2).getHeight())
                        .setDuration(250);
                topCardAnim.start();

            } else {//向下的动画
                topCardAnim = ObjectAnimator.ofFloat(getChildAt(2),
                        "Y", cardNowY, getMeasuredHeight())
                        .setDuration(250);
                topCardAnim.start();
            }
            ObjectAnimator CardAnim1x = ObjectAnimator.ofFloat(getChildAt(1), "X", x1, x2);
            ObjectAnimator CardAnim1y = ObjectAnimator.ofFloat(getChildAt(1), "Y", y1, y2);
            ObjectAnimator CardAnim1z = ObjectAnimator.ofFloat(getChildAt(1), "Z", z1, z2);

            ObjectAnimator CardAnim0x = ObjectAnimator.ofFloat(getChildAt(0), "X", x0, x1);
            ObjectAnimator CardAnim0y = ObjectAnimator.ofFloat(getChildAt(0), "Y", y0, y1);
            ObjectAnimator CardAnim0z = ObjectAnimator.ofFloat(getChildAt(0), "Z", z0, z1);

            AnimatorSet anSet = new AnimatorSet();//组合动画
            //动画结束，最上面的卡片回归原位，改变文字
            anSet.addListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animation) {}
                public void onAnimationEnd(Animator animation) {//新建卡片，并刷新数据
                    //移除顶层的卡片
                    views.remove(getChildAt(2));
                    PoetryCardView.this.removeViewAt(2);

                    View child = View.inflate(PoetryCardView.this.getContext(), R.layout.cardview_poetry, null);
                    child.setTag("5b8b9572e116fb3714e6fa3c");

                    if(presenter != null)//如果Presenter不为null，则申请刷新数据
                        presenter.requestReflashCard(child);

                    child.setX(x0);
                    child.setY(y0);
                    if(android.os.Build.VERSION.SDK_INT >= 21) {
                        child.setZ(z0);
                    }
                    //新卡片加入到布局中
                    PoetryCardView.this.addView(child, 0);
                    views.add(child);//加入到views中

                    ObjectAnimator an = ObjectAnimator.ofFloat(child, "Alpha", 0, 1);
                    an.setDuration(400);
                    an.start();
                    isLockCardMove = false;
                }
                public void onAnimationCancel(Animator animation) {}
                public void onAnimationRepeat(Animator animation) {}
            });
            anSet.play(CardAnim1x).with(CardAnim1y).with(CardAnim1z)
                    .with(CardAnim0x).with(CardAnim0y).with(CardAnim0z)
                    .with(topCardAnim);
            anSet.setDuration(250);
            anSet.start();
        }
    }

    //判断某个值是否在顶上的卡片内
    private boolean cardViewTopInclude(float x, float y) {
        View child = getChildAt(2);
        if(cardNowX == 0 && cardNowY == 0) {
            cardNowX = child.getX();
            cardNowY = child.getY();
        }
        return x > cardNowX
                && y > cardNowY
                && x < cardNowX + child.getWidth()
                && y < cardNowY + child.getHeight();
    }

    //实现view接口
    @Override
    public void displayCard(final View view, final String[] poetry, final String[] author, final String[] id) {
        if (!views.contains(view) || poetry.length != author.length)
            return;
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (poetry.length == 1) {//只有一句诗的时候刷新最下面那张
                    ((TextView) view.findViewById(R.id.card_tv_poem)).setText(poetry[0]);
                    ((TextView) view.findViewById(R.id.card_tv_name)).setText("——" + author[0]);
                    view.setTag(id[0]);
                } else {//显示三句诗
                    for (int i = poetry.length - 1; i >= 0; i--){
                        ((TextView) getChildAt(i).findViewById(R.id.card_tv_poem)).setText(poetry[i]);
                        ((TextView) getChildAt(i).findViewById(R.id.card_tv_name)).setText("——" + author[i]);
                        view.setTag(id[i]);
                    }
                }
            }
        });
    }

    //实现view接口
    @Override
    public void setPresenter(PresenterEveryDayInterface presenter) {
        this.presenter = presenter;
        //首次设置的时候申请一下
        presenter.requestReflashCard(getChildAt(0));
    }

    /** 点击相关*/
    //点击时的调用接口，点击的操作初定是分享诗句，但是评论诗句（诗文评）、弹出诗句详情页都是可以的
    public interface OnClickListener{
        /**
         * 点击时候的的处理
         * @param poetry 当前点击的诗句
         */
        void onClick(String poetry);
    }

    //增加点击倾听者
    public void addCardOnClickListener(OnClickListener l) {
        if(onClickListeners == null)
            synchronized (this) {
                if(onClickListeners == null)
                    onClickListeners = new ArrayList<>(1);
            }
        onClickListeners.add(l);
    }

    //通知所有点击倾听者，表示用户点击这句诗
    private void notifyClickListeners(String poetry) {
        if(onClickListeners == null)
            return;
        for(OnClickListener l : onClickListeners) {
            l.onClick(poetry);
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        init(getContext());
    }
}
