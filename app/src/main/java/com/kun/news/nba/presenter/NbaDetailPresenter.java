package com.kun.news.nba.presenter;

import android.text.TextUtils;

import com.kun.news.app.Constant;
import com.kun.news.common.presenter.BasePresenter;
import com.kun.news.http.api.tencent.TencentApi;
import com.kun.news.nba.model.JsonParser;
import com.kun.news.nba.model.NewsDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by jiangkun on 16/9/29.
 */

public class NbaDetailPresenter extends BasePresenter<NewsDetail, NbaDetailView> {
    private TencentApi mTencentApi;

    public NbaDetailPresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.TENCENT_SERVER)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mTencentApi = retrofit.create(TencentApi.class);
    }

    @Override
    public void refreshData(Object... params) {
        Call<String> call = mTencentApi.getNewsDetail("news", (String) params[0]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body())) {
                    String jsonStr = response.body();
                    NewsDetail detail = JsonParser.parseNewsDetail(jsonStr);
                    if (mView != null)
                        mView.onLoadSuccess(detail);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (mView != null)
                    mView.onLoadFailed();
            }
        });
    }
}
