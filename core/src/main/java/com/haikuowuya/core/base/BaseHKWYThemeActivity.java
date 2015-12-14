package com.haikuowuya.core.base;

import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

import com.haikuowuya.core.ThemeManager;

/**
 * 使用到的基类_支持切换主题Activity
 */
public abstract class BaseHKWYThemeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ThemeManager.get().setContext(this);
        setBaseTheme(ThemeManager.get().getBase(), true);
        super.onCreate(savedInstanceState);
        setUpTheme(ThemeManager.get().getTheme());
    }

    public void setBaseTheme(@StyleRes int baseTheme, boolean first)
    {
        setTheme(baseTheme);
        if (!first)
        {
            recreate();
        }
    }

    public abstract void setUpTheme(ThemeManager.ThemeItem theme );

}
