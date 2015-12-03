package com.haikuowuya.demo.base;

import android.os.Bundle;

/**
 * 将中间显示标题的ToolBar隐藏
 */
public abstract class BaseNoTitleActivity extends BaseTitleActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        hideToolBar();
    }

}
