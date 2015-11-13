package com.haikuowuya.core.base;

import android.os.Bundle;

/**
 * 将中间显示标题的ToolBar隐藏
 */
public abstract class BaseNoTitleActivity1 extends BaseTitleActivity1
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        hideToolBar();
    }

}
