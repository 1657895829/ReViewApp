package com.example.pullrecyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * 创建自定义view继承ListView
 * 需求：
 * 下拉ListView，图片跟随手指移动而放大，手指离开屏幕，图片则反弹回原始大小
 */
public class HeaderRecyclerView extends RecyclerView {
    // 放大的 ImageView
    private ImageView headerIV;
    private int height;

    public void setHeaderIV(ImageView headerIV) {
        this.headerIV = headerIV;
    }

    public HeaderRecyclerView(Context context) {
        super(context);
    }

    public HeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * 当 view 依附到 activity 上面的时候回调
     * @param hasWindowFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if (hasWindowFocus){
            height = this.headerIV.getHeight();
        }
    }

    /**
     * 复写OverScrollBy方法，根据用户下拉的距离，动态修改headerView的高度（主要是deltaY和isTouchEvent这两个方法）
     *  当listview 滚动到顶部的时候，还要下拉，还要网上滚动，那么这时就会调用该方法
     * @param deltaX
     * @param deltaY
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX
     * @param maxOverScrollY
     * @param isTouchEvent
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        // 滑动过头的时候回调该方法
        // 控制 imageview 的高度逐渐增加------从而达到滚动图片放大的效果
        boolean isCollpse = resizeOverScrollBy(deltaY);

        return isCollpse == false ? isCollpse: super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    private boolean resizeOverScrollBy(int deltaY) {

        if (deltaY < 0){
            if (headerIV != null){
                // 当滑动到顶部的时候，还要网上滑动，就改变 imageview 的高度
                headerIV.getLayoutParams().height = headerIV.getHeight() - deltaY;
                headerIV.requestLayout();
            }
        } else {
            if (headerIV != null){
                headerIV.getLayoutParams().height = headerIV.getHeight() - deltaY;
                headerIV.requestLayout();
            }
        }
        return false;
    }

    /**
     * 暴露一个方法，得到外界ImageView,并测量ImageView的高度
     * 当listview 没有滑动到底部或顶部时调用
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // 还原图片，保持imageview 的初始化高度
        // 获取imageview 的父容器（RelativeLayout）
        ViewParent parent = this.headerIV.getParent();
        if (parent != null){
            View rootView = (View)parent;
            if (rootView.getTop() < 0 && headerIV .getHeight() > height){

                headerIV.getLayoutParams().height = headerIV.getHeight() + rootView.getTop();

                // 重新摆放子控件
                rootView.layout(rootView.getLeft(), 0, rootView.getRight(), rootView.getBottom());

                // 重新绘制
                headerIV.requestLayout();
            }
        }
    }

    //复写OnToucheEvent方法
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 监听手势抬起
        if (ev.getAction() == MotionEvent.ACTION_UP){
            MyAnimation animation = new MyAnimation(headerIV, height);
            animation.setDuration(300);
            this.headerIV.startAnimation(animation);
        }
        return super.onTouchEvent(ev);
    }

    public class MyAnimation extends Animation {

        private ImageView imageView;
        // imageview 的原始高度
        private int targetHeight;
        // 当前 imageview 的高度
        private int currentHeight;
        // 高度差 当前的减去原始的
        private int extraHeight;

        public MyAnimation(ImageView imageView, int targetHeight){
            this.imageView = imageView;
            this.targetHeight = targetHeight;
            this.currentHeight = imageView.getHeight();
            this.extraHeight = this.currentHeight - this.targetHeight;
        }

        /**
         *  当动画在不断的执行的时候回调该方法（就是监听动画执行的过程）
         * @param interpolatedTime 值得范围 0.0 到 1.0，时间变化因子
         * @param t
         */
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            this.imageView.getLayoutParams().height = (int)(this.currentHeight
                    - extraHeight * interpolatedTime);

            this.imageView.requestLayout();
        }
    }
}

