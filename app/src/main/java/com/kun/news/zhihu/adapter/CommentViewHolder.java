package com.kun.news.zhihu.adapter;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kun.news.R;
import com.kun.news.common.adapter.BaseViewHolder;
import com.kun.news.http.bean.zhihu.ZhihuComments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiangkun on 16/9/26.
 */

public class CommentViewHolder extends BaseViewHolder<ZhihuComments.CommentsBean> {

    TextView mName;
    SimpleDraweeView mAvatar;
    TextView mComment;
    TextView mDate;
    TextView mLikes;

    public CommentViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.name);
        mAvatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar);
        mComment = (TextView) itemView.findViewById(R.id.comment_text);
        mDate = (TextView) itemView.findViewById(R.id.date);
        mLikes = (TextView) itemView.findViewById(R.id.likes);
    }

    @Override
    public void bind(ZhihuComments.CommentsBean data) {
        if (data == null) return;
        mName.setText(data.getAuthor());
        mAvatar.setImageURI(data.getAvatar());
        mComment.setText(data.getContent());
        mLikes.setText(data.getLikes() + "赞");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            Date date = format.parse());
//            String dateStr = date.getYear() + "-" + date.getMonth() + "-" + date.getDate();
            mDate.setText(format.format(new Long(data.getTime())));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Date date = new Date(data.getTime() * 10000);

    }
}
