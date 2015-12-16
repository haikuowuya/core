package com.haikuowuya.core.base;

import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.util.TypedValue;

import com.haikuowuya.core.R;
import com.haikuowuya.core.ThemePref;
import com.haikuowuya.core.util.ViewUtils;

/**
 * 使用到的基类_支持切换主题Activity
 */
public abstract class BaseHKWYThemeActivity extends BaseHKWYTitleActivity
{
    private int mPrimaryColor;
    private int mAccentColor;

    private int mBaseThemeId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        ThemePref.init(this);
        int themeId = R.style.HKWY_Light;
        mBaseThemeId = ThemePref.getBaseTheme();
        if (mBaseThemeId != 0)
        {
            themeId = mBaseThemeId;
        }
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setUpTheme();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (mPrimaryColor != ThemePref.getPrimaryColor() || mAccentColor != ThemePref.getAccentColor() || mBaseThemeId != ThemePref.getBaseTheme())
        {
            recreate();
        }
    }

    protected void setUpTheme()
    {
        mPrimaryColor = ThemePref.getPrimaryColor();
        mAccentColor = ThemePref.getAccentColor();
        ViewUtils.setStatusBarColorWithPrimaryColor(mActivity, mPrimaryColor);
        themeTitleBar();
        themeBackground();

    }

    protected void themeBackground()
    {
        int backgroundColor = resolveColorAttr(android.R.attr.background);
        mFrameFragmentContainer.setBackgroundColor(backgroundColor);
    }

    protected void themeTitleBar()
    {
        setTitleBarBackgroundColor(mPrimaryColor);
        setCenterTitleTextColor(mAccentColor);
    }

    @ColorInt
    public int getPrimaryColor()
    {
        return ThemePref.getPrimaryColor();
    }

    @ColorInt
    public int getAccentColor()
    {
        return ThemePref.getAccentColor();
    }

    @ColorInt
    public int resolveColorAttr(@AttrRes int resId)
    {
        final TypedValue value = new TypedValue();
        getTheme().resolveAttribute(resId, value, true);
        return value.data;
    }
}
