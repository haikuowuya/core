package com.haikuowuya.core.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * 项目中使用fragment的基类
 */
public abstract class BaseHKWYFragment extends Fragment implements IFragmentTitle
{
    protected BaseHKWYActivity mActivity;
    /**
     * 一个标识值， 应该在 Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle) 方法中将其修改为true
     */
    protected boolean mIsInit = false;
    protected BaseHKWYFragment mFragment;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mActivity = (BaseHKWYActivity) activity;
        mFragment = this;
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
