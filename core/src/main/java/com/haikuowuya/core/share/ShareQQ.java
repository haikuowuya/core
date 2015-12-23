package com.haikuowuya.core.share;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by Steven on 2015/12/23 0023.
 */
public class ShareQQ
{

    private Tencent mTencent;

    private void doTencentRegister(Activity activity)
    {
        mTencent = Tencent.createInstance(ShareUtils.QQ_APP_ID, activity);
    }

    /**
     * QQ空间分享
     *
     * @param params
     */

    public void doShareToQzone(final Activity activity, final Bundle params)
    {
        doTencentRegister(activity);
        activity.runOnUiThread(new Runnable()
        {
            public void run()
            {
                System.out.println("分享到QQ空间");
                mTencent.shareToQzone(activity, params, mIUiListener);
            }
        });

    }

    /**
     * QQ分享
     *
     * @param params
     */
    public void doShareToQQ(final Activity activity, final Bundle params)
    {
        doTencentRegister(activity);
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mTencent.shareToQQ(activity, params, mIUiListener);
            }
        });
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
