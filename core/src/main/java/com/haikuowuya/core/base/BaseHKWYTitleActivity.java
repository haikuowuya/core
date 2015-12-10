package com.haikuowuya.core.base;

import android.os.Bundle;

import com.haikuowuya.core.R;

/**
 *
 */
public abstract class BaseHKWYTitleActivity extends BaseHKWYSwipeBackActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_as_view_common_hkwy);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_content_container, fragmentAsView()).commit();
        setCenterTitle(getActivityTitle(), 0xFFFFFFFF);
        afterOnCreate(savedInstanceState);
    }

    public void afterOnCreate(Bundle savedInstanceState)
    {

    }

    public abstract BaseHKWYFragment fragmentAsView();

}
