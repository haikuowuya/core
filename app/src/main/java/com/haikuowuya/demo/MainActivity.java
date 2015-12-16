package com.haikuowuya.demo;

import com.haikuowuya.core.base.BaseHKWYThemeActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.PTRFragment;

public class MainActivity extends BaseHKWYThemeActivity
{
    public BaseFragment fragmentAsView()
    {
        return PTRFragment.newInstance() ;
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "首页Activity";
    }

}
