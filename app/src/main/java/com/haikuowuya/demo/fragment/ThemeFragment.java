package com.haikuowuya.demo.fragment;

import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.core.ThemePref;
import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class ThemeFragment extends BaseFragment
{
    public static ThemeFragment newInstance()
    {
        ThemeFragment fragment = new ThemeFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @OnClick(R.id.tv_change_theme)
    void onChangeThemeClick()
    {
        int currentThemeId = ThemePref.getBaseTheme();

        if (currentThemeId == R.style.HKWY_Dark)
        {
            currentThemeId = R.style.HKWY_Light;

        } else
        {
            currentThemeId = R.style.HKWY_Dark;

        }
        int primaryColor = resolveColorAttr(android.R.attr.colorPrimary);
        int accentColor = resolveColorAttr(android.R.attr.colorAccent);
        ThemePref.setBaseTheme(currentThemeId);
        ThemePref.setPrimaryColor(primaryColor);
        ThemePref.setAccentColor(accentColor);
        mActivity.recreate();
    }

    @Override
    public String getFragmentTitle()
    {
        return null;
    }

    @ColorInt
    public int resolveColorAttr(@AttrRes int resId)
    {
        final TypedValue value = new TypedValue();
        mActivity.getTheme().resolveAttribute(resId, value, true);
        return value.data;
    }
}
