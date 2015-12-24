package com.haikuowuya.core.base;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.haikuowuya.core.R;
import com.haikuowuya.core.util.ViewUtils;

/**
 *
 */
public abstract class BaseHKWYTitleActivity extends BaseHKWYSwipeBackActivity
{
    protected FrameLayout mFrameFragmentContainer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
       // setTheme(R.style.Base_AppTheme_Light);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_as_view_common_hkwy);
        mFrameFragmentContainer = (FrameLayout) findViewById(R.id.frame_content_container_hkwy);
        mFrameFragmentContainer.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.color_background_light));
        getSupportFragmentManager().beginTransaction().add(R.id.frame_content_container_hkwy, fragmentAsView()).commit();
        setCenterTitle(getActivityTitle(), 0xFFFFFFFF);
        int titleBackgroundColor = ViewUtils.resolveColorAttr(mActivity, R.attr.colorPrimary);
        setTitleBarBackgroundColor(titleBackgroundColor);
        afterOnCreate(savedInstanceState);
    }

    public void afterOnCreate(Bundle savedInstanceState)
    {

    }

    public abstract BaseHKWYFragment fragmentAsView();

}
