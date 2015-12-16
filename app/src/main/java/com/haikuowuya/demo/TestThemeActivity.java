package com.haikuowuya.demo;

import com.haikuowuya.core.base.BaseHKWYThemeActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.ThemeFragment;

public class TestThemeActivity extends BaseHKWYThemeActivity
{

    public BaseFragment fragmentAsView()
    {
        return ThemeFragment.newInstance();
    }


    @Override
    public CharSequence getActivityTitle()
    {
        return "测试主题";
    }

}
