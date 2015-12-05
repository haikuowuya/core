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
import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class CropFragment extends BaseFragment
{
    public static CropFragment newInstance()
    {
        CropFragment fragment = new CropFragment();
        return fragment;
    }

    @Bind(R.id.iv_image)
    ImageView mIvImageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_crop, container, false);
    }

    @OnClick(R.id.tv_select_image)
    void onSelectImageClick()
    {
        PhotoUtils.showSelectDialog(mActivity);
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
        return null;
    }
}
