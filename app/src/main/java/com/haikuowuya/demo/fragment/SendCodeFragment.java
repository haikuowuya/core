package com.haikuowuya.demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haikuowuya.core.util.SendSmsCodeUtils;
import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class SendCodeFragment extends BaseFragment
{
    public static SendCodeFragment newInstance()
    {
        SendCodeFragment fragment = new SendCodeFragment();
        return fragment;
    }

    @Bind(R.id.tv_code)
    TextView mTvCode;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_send_sms, container, false);
    }

    @OnClick(R.id.tv_code)
    void onCodeClick()
    {
        SendSmsCodeUtils.sendSmsCodeSuccess(new Handler(),mTvCode);
    }


    @Override
    public String getFragmentTitle()
    {
        return null;
    }
}
