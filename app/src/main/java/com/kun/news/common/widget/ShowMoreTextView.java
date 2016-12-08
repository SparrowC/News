package com.kun.news.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kun.news.R;
import com.kun.news.common.utils.UIUtils;

/**
 * Created by jiangkun on 2016/10/31.
 */

public class ShowMoreTextView extends LinearLayout implements View.OnClickListener {
    private TextView mText;
    private TextView mShowMore;
    private boolean mIsShowMore;
    private int textSize;
    private int textColor;
    private String text;
    private int maxLine;

    public ShowMoreTextView(Context context) {
        this(context, null);
    }

    public ShowMoreTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShowMoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShowMoreTextView);
        textSize = array.getDimensionPixelSize(R.styleable.ShowMoreTextView_textSize
                , (int) UIUtils.sp2px(context, 10));
        textColor = array.getColor(R.styleable.ShowMoreTextView_textColor
                , context.getResources().getColor(R.color.primary_text));
        text = array.getString(R.styleable.ShowMoreTextView_text);
        maxLine = array.getInteger(R.styleable.ShowMoreTextView_maxLine, 2);
        array.recycle();
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.view_show_more_textview, this, true);
        mText = (TextView) root.findViewById(R.id.text);
        mText.setTextColor(textColor);
        mText.setTextSize(textSize);
        mText.setText(text);

        mShowMore = (TextView) root.findViewById(R.id.show_more);
        mShowMore.setText(R.string.text_show);
        mShowMore.setVisibility(GONE);
        mIsShowMore = false;
        mText.post(new Runnable() {
            @Override
            public void run() {
                updateText();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.show_more && mShowMore.isCursorVisible()) {
            if (mIsShowMore) {
                mText.setMaxLines(maxLine);
                mShowMore.setText(R.string.text_show);
                mIsShowMore = false;
            } else {
                mText.setMaxLines(Integer.MAX_VALUE);
                mShowMore.setText(R.string.text_hide);
                mIsShowMore = true;
            }
        }
    }

    public void setText(CharSequence topText) {
        mText.setText(topText);
        updateText();
    }

    public void setText(CharSequence text, TextView.BufferType type) {
        mText.setText(text, type);
        updateText();
    }

    public void setText(char[] text, int start, int len) {
        mText.setText(text, start, len);
        updateText();
    }

    public void setText(int resid) {
        mText.setText(resid);
        updateText();
    }

    public void setText(int resid, TextView.BufferType type) {
        mText.setText(resid, type);
        updateText();
    }

    private void updateText() {
        int lines = mText.getLineCount();
        if (lines > maxLine) {
            mText.setMaxLines(maxLine);
            mShowMore.setVisibility(VISIBLE);
            mShowMore.setOnClickListener(ShowMoreTextView.this);
        }
    }
}
