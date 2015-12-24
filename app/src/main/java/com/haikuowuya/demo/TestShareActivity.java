package com.haikuowuya.demo;

import android.content.Intent;

import com.haikuowuya.core.base.BaseHKWYTitleActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.ShareFragment;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class TestShareActivity extends BaseHKWYTitleActivity
{

    public BaseFragment fragmentAsView()
    {
        return ShareFragment.newInstance();
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return getString(R.string.tv_activity_test_share);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_QZONE_SHARE)
        {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
    }

    public IUiListener mIUiListener = new IUiListener()
    {
        @Override
        public void onComplete(Object object)
        {
            System.out.println("QQ onComplete" + object);
        }

        @Override
        public void onError(UiError uiError)
        {
            System.out.println("QQ onError" + uiError.errorMessage);
        }

        @Override
        public void onCancel()
        {
            System.out.println("QQ onCancel");
        }
    };

}
