package com.xujie.lequ.view.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wj on 2016/10/10.
 * ViewPager 是否禁止左右滑动
 */
public class IsScrollViewPager extends ViewPager {


    private boolean isScroll = true;

    public IsScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IsScrollViewPager(Context context){
        super(context);
    }

    public void setIsScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) return  false;
        else return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll) return  false;
        else return super.onTouchEvent(ev);
    }
}
