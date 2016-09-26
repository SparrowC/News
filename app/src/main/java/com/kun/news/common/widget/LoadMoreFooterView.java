package com.kun.news.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.kun.news.R;

/**
 * Created by jiangkun on 16/9/25.
 */

public class LoadMoreFooterView extends LinearLayout implements SwipeTrigger, SwipeLoadMoreTrigger {
    private TextView mPrompt;
    private Context mContext;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View.inflate(mContext, R.layout.view_refresh_header, this);
        mPrompt = (TextView) findViewById(R.id.text_prompt);
    }

    @Override
    public void onPrepare() {
        mPrompt.setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getMeasuredHeight()) {
                mPrompt.setText(mContext.getText(R.string.prompt_release_to_refresh));
            } else {
                mPrompt.setText(mContext.getText(R.string.prompt_push_to_refresh));
            }
        } else {
            mPrompt.setText(mContext.getText(R.string.prompt_refresh_finish));
        }
    }

    @Override
    public void onRelease() {
        mPrompt.setText(mContext.getText(R.string.prompt_refreshing));
    }

    @Override
    public void onComplete() {
        mPrompt.setText(mContext.getText(R.string.prompt_refresh_finish));
    }

    @Override
    public void onReset() {
        mPrompt.setText("");
    }

    @Override
    public void onLoadMore() {

    }
}
