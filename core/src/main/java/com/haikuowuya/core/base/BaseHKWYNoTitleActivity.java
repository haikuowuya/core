package com.haikuowuya.core.base;

import android.os.Bundle;

/**
 * 将中间显示标题的ToolBar隐藏
 */
public abstract class BaseHKWYNoTitleActivity extends BaseHKWYThemeActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        hideTitleContainer();
    }

}
