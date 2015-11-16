package com.haikuowuya.core.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.core.base.BaseFragment;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class MenuFragment extends BaseFragment
{
    public static MenuFragment newInstance()
    {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return null;
    }

    @Override
    public String getFragmentTitle()
    {
        return null;
    }
}
