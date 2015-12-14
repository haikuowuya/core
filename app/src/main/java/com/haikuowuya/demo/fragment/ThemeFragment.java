package com.haikuowuya.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        mActivity.setBaseTheme(R.style.AppTheme_Dark, false);
        mActivity.recreate();
    }

    @Override
    public String getFragmentTitle()
    {
        return null;
    }
}
