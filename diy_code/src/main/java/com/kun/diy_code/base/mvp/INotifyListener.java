package com.kun.diy_code.base.mvp;

/**
 * Created by jiangkun on 2017/9/12.
 */

public interface INotifyListener {
    void onFailed(Exception e);

    void onSuccess();
}
