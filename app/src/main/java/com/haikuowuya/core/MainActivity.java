package com.haikuowuya.core;

import android.os.Bundle;

import com.haikuowuya.core.base.BaseListAcitivity;

public class MainActivity extends BaseListAcitivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "首页Activity";
    }
}
