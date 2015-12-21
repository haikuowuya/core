package com.haikuowuya.demo.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.haikuowuya.core.util.PhotoUtils;
import com.haikuowuya.core.util.SharePopupWindowUtils;
import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class ShareFragment extends BaseFragment
{
    public static ShareFragment newInstance()
    {
        ShareFragment fragment = new ShareFragment();
        return fragment;
    }

    @Bind(R.id.iv_image)
    ImageView mIvImageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @OnClick(R.id.tv_share)
    void onShareClick()
    {
        SharePopupWindowUtils.showShare(mActivity,"");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        String croppedImagePath = PhotoUtils.getFinalCroppedImagePath();
        if (!TextUtils.isEmpty(croppedImagePath))
        {
            mIvImageView.setImageBitmap(BitmapFactory.decodeFile(croppedImagePath));
        }
    }

    @Override
    public String getFragmentTitle()
    {
        return getString(R.string.tv_activity_test_share);

    }
}
