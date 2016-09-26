package com.kun.news.zhihu.event;

/**
 * Created by jiangkun on 16/9/26.
 */

public class ScrollEvent {
    public static final int SCROLL_UP = 0;
    public static final int SCROLL_DOWN = 1;
    int Type;

    public int getType() {
        return Type;
    }

    public ScrollEvent(int type) {
        Type = type;
    }
}
