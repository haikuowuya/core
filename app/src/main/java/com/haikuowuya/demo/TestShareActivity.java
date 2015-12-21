package com.haikuowuya.demo;

import com.haikuowuya.core.base.BaseHKWYThemeActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.ShareFragment;

public class TestShareActivity extends BaseHKWYThemeActivity
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

}
