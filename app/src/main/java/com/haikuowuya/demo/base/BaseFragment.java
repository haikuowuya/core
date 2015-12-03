package com.haikuowuya.demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by raiyi-suzhou on 2015/5/11 0011.
 */
public abstract class BaseFragment extends Fragment implements IFragmentTitle
{
    protected BaseActivity mActivity;
    /**
     * 一个标识值， 应该在{@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}方法中将其修改为true
     */
    protected boolean mIsInit = false;
    protected boolean mCanPullToRefresh = false;
    protected  BaseFragment mFragment;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
        mFragment = this;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if(null != view)
        {
            ButterKnife.bind(mFragment, view);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (getUserVisibleHint())
        {
            if (mIsInit)
            {
                mIsInit = false;
                doGetData();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            if (mIsInit)
            {
                mIsInit = false;
                doGetData();
            }
        }
    }

    /**
     * 在此方法中进行网络请求操作  ,注意懒加载只有在和ViewPager结合使用时才有效的
     * 如果只是一个单纯的Fragment,需要手动去调用此方法
     */
    protected void doGetData()
    {
    }

}
