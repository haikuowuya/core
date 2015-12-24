package com.haikuowuya.demo;

import android.content.Intent;

import com.haikuowuya.core.base.BaseHKWYFragment;
import com.haikuowuya.core.base.BaseHKWYSlidingMenuActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.CropMenuFragment;
import com.haikuowuya.demo.fragment.ShareFragment;

public class TestSlidingMenuActivity extends BaseHKWYSlidingMenuActivity
{
    private CropMenuFragment mCropMenuFragment;

    public BaseFragment fragmentAsView()
    {
        return ShareFragment.newInstance();
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return getString(R.string.tv_activity_test_sliding_menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mCropMenuFragment)
        {
            mCropMenuFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public BaseHKWYFragment fragmentAsMenu()
    {
        mCropMenuFragment = CropMenuFragment.newInstance();
        return mCropMenuFragment;
    }
}
