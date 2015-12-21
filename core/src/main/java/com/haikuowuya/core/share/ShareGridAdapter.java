package com.haikuowuya.core.share;

import android.app.Activity;
import android.view.View;

import com.haikuowuya.core.R;
import com.haikuowuya.core.base.BaseHKWYAdapter;

import java.util.List;

/**
 * Created by raiyi-suzhou on 2015/7/10 0010.
 */
public class ShareGridAdapter extends BaseHKWYAdapter<ShareGridAdapter.ShareItem>
{
    public ShareGridAdapter(Activity activity, List<ShareItem> data)
    {
        super(activity, data);
    }

    @Override
    public void bindDataToView(View convertView, ShareItem shareItem)
    {
        setImageViewResId(convertView, R.id.iv_image, shareItem.resId);
        setTextViewText(convertView, R.id.tv_text, shareItem.text);
    }

    @Override
    public int layoutResId()
    {
        return R.layout.grid_share_item;
    }

    public static class ShareItem
    {
        public int resId;
        public String text;
        public int type;
    }
}
