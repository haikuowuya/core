package com.haikuowuya.demo;

import com.haikuowuya.core.base.BaseHKWYTitleActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.PTRFragment;

public class MainActivity extends BaseHKWYTitleActivity
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
