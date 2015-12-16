package com.haikuowuya.core.base;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.haikuowuya.core.R;

/**
 *
 */
public abstract class BaseHKWYTitleActivity extends BaseHKWYSwipeBackActivity
{
    protected FrameLayout mFrameFragmentContainer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_as_view_common_hkwy);
        mFrameFragmentContainer = (FrameLayout) findViewById(R.id.frame_content_container_hkwy);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_content_container_hkwy, fragmentAsView()).commit();
        setCenterTitle(getActivityTitle(), 0xFFFFFFFF);
        afterOnCreate(savedInstanceState);
    }

    public void afterOnCreate(Bundle savedInstanceState)
    {

    }

    public abstract BaseHKWYFragment fragmentAsView();

}
