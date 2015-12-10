package com.haikuowuya.demo;

import android.content.Intent;

import com.haikuowuya.core.util.PhotoUtils;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.base.BaseTitleActivity;
import com.haikuowuya.demo.fragment.SendCodeFragment;

public class TestSendSmsActivity extends BaseTitleActivity
{
    private SendCodeFragment mSendCodeFragment;

    public BaseFragment fragmentAsView()
    {
        mSendCodeFragment = SendCodeFragment.newInstance();
        return mSendCodeFragment;
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
        if (null != mSendCodeFragment)
        {
            mSendCodeFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
