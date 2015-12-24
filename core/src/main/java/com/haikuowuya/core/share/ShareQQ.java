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

    private static Tencent doTencentRegister(Activity activity)
    {
        Tencent tencent = Tencent.createInstance(ShareConstant.QQ_APP_ID, activity);
        return tencent;
    }

    /**
     * QQ空间分享
     *
     * @param params
     */

    public static void doShareToQzone(final Activity activity, final Bundle params)
    {
        final Tencent tencent = doTencentRegister(activity);
        activity.runOnUiThread(new Runnable()
        {
            public void run()
            {
                System.out.println("分享到QQ空间");
                tencent.shareToQzone(activity, params, sIUiListener);
            }
        });

    }

    /**
     * QQ分享
     *
     * @param params
     */
    public static void doShareToQQ(final Activity activity, final Bundle params)
    {
        final Tencent tencent = doTencentRegister(activity);
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                tencent.shareToQQ(activity, params, sIUiListener);
            }
        });
    }

    public static IUiListener sIUiListener = new IUiListener()
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
