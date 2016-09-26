package com.kun.news.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.kun.news.common.utils.UIUtils;

/**
 * Created by jiangkun on 16/9/24.
 */

public class SwipeOverlayFrameLayout extends FrameLayout {

    public interface OnSwipeListener {
        /** swipe toward left */
        public boolean onSwipeLeft();
        /** swipe toward right */
        public boolean onSwipeRight();
    }

    private GestureDetector mDetector;
    private OnSwipeListener mListener;
    private float mThresholdY;
    private float mMinFlingDistance;
    private boolean mSwipeEnabled = true;
    private boolean mDisallowInterceptEnabled = false;
    private boolean mDisallowIntercept = false;

    public SwipeOverlayFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public SwipeOverlayFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeOverlayFrameLayout(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                return onSwipeViewFling(e1, e2, velocityX, velocityY);
            }
        };
        mThresholdY = UIUtils.dip2Px(context, 45);
        mMinFlingDistance = UIUtils.dip2Px(context, 65);
        mDetector = new GestureDetector(context.getApplicationContext(), listener);
        mDetector.setOnDoubleTapListener(null);
        mDetector.setIsLongpressEnabled(false);
    }

    boolean onSwipeViewFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (mListener == null)
            return false;
        if (Math.abs(e2.getY() - e1.getY()) > mThresholdY)
            return false;
        float absX = Math.abs(velocityX);
        float absY = Math.abs(velocityY);
        float absDx = Math.abs(e2.getX() - e1.getX());
        float absDy = Math.abs(e2.getY() - e1.getY());
        if (absY >= absX || absDy >= absDx)
            return false;
        boolean handled = false;
        if (absDx > mMinFlingDistance) {
            if (velocityX > 0.0f) {
                handled = mListener.onSwipeRight();
            } else if (velocityX < 0.0f) {
                handled = mListener.onSwipeLeft();
            }
        }
        return handled;
    }

    public void setOnSwipeListener(OnSwipeListener listener) {
        mListener = listener;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        mDisallowIntercept = disallowIntercept;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN)
                mDisallowIntercept = false;
            boolean disallowIntercept = mDisallowIntercept && mDisallowInterceptEnabled;
            if (mSwipeEnabled && mDetector != null && !disallowIntercept) {
                boolean handled = mDetector.onTouchEvent(ev);
                if (handled) {
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    super.dispatchTouchEvent(ev);
                    return true;
                }
            }
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            return true;
        }
    }

    public void setSwipeEnabled(boolean enabled) {
        mSwipeEnabled = enabled;
    }

    public void setDisllowInterceptEnabled(boolean enabled) {
        mDisallowInterceptEnabled = enabled;
    }
}
