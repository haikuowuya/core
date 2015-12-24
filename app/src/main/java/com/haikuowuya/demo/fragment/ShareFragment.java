package com.haikuowuya.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.core.util.SharePopupWindowUtils;
import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseFragment;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @OnClick(R.id.tv_share)
    void onShareClick()
    {
        SharePopupWindowUtils.showShare(mActivity, "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick(R.id.tv_share_qzone)
    void onQzoneClick()
    {
        Tencent tencent = Tencent.createInstance("1104965199", mActivity);
        String share_title = "测试QQ分享", share_content = "测试QQ分享", share_url = "http://haikuowuya.com";
        Bundle bundle = new Bundle();
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, share_title);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, share_url);
        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, share_content);
        tencent.shareToQQ(mActivity, bundle, mIUiListener);
    }

    @Override
    public String getFragmentTitle()
    {
        return getString(R.string.tv_activity_test_share);

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
