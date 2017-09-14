package com.kun.diy_code.base.mvp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jiangkun on 2017/9/12.
 */

public abstract class BaseModel<T> {
    protected T mData;
    private Object mApi;
    protected List<INotifyListener> mNotifyListeners;

    public abstract void requestData();

    protected Object getApiService(String url, Class clz) {
        if (mApi == null) {
            synchronized (BaseModel.class) {
                if (mApi == null) {
                    mApi = getRetrofit(url).create(clz);
                }
            }
        }
        return mApi;
    }

    protected Retrofit getRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void removeNotifyListener(INotifyListener listener) {
        if (mNotifyListeners != null) {
            mNotifyListeners.remove(listener);
        }
    }

    public void addNotifyListener(INotifyListener notifyListeners) {
        if (mNotifyListeners == null) {
            mNotifyListeners = new ArrayList<>();
        }
        mNotifyListeners.add(notifyListeners);
    }

    public void removeAllNotifyListener(INotifyListener listener) {
        if (mNotifyListeners != null) {
            mNotifyListeners.clear();
        }
    }

    public void notifySuccess() {
        if (mNotifyListeners == null) return;
        for (INotifyListener listener : mNotifyListeners) {
            listener.onSuccess();
        }
    }

    public void notifyFailed(Exception e) {
        if (mNotifyListeners == null) return;
        for (INotifyListener listener : mNotifyListeners) {
            listener.onFailed(e);
        }
    }
}
