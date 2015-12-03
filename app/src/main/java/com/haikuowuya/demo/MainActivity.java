package com.haikuowuya.demo;

import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.base.BaseTitleActivity;
import com.haikuowuya.demo.fragment.PTRFragment;

public class MainActivity extends BaseTitleActivity
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
