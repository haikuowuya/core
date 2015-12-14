package com.haikuowuya.demo;

import com.haikuowuya.core.base.BaseHKWYTitleActivity;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.fragment.ThemeFragment;

public class TestThemeActivity extends BaseHKWYTitleActivity
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
