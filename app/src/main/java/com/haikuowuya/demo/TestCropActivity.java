package com.haikuowuya.demo;

import android.content.Intent;

import com.haikuowuya.core.base.BaseHKWYTitleActivity;
import com.haikuowuya.core.util.PhotoUtils;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.CropFragment;

public class TestCropActivity extends BaseHKWYTitleActivity
{
    private CropFragment mCropFragment;

    public BaseFragment fragmentAsView()
    {
        mCropFragment = CropFragment.newInstance();
        return mCropFragment;
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "测试Crop";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtils.onActivityResult(this, requestCode, resultCode,data);
        if (null != mCropFragment)
        {
            mCropFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
