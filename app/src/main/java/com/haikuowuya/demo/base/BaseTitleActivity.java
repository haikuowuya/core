package com.haikuowuya.demo.base;

import android.os.Bundle;

import com.haikuowuya.demo.R;

/**
 * 中间显示一个标题的Activity的基类，标题的名称为{@link BaseActivity#getActivityTitle()}
 */
public abstract class BaseTitleActivity extends BaseSwipeBackActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_as_view_common);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_content_container, fragmentAsView()).commit();
        setCenterTitle(getActivityTitle(), 0xFFFFFFFF);
        afterOnCreate(savedInstanceState);
    }

    public void afterOnCreate(Bundle savedInstanceState)
    {

    }

    public abstract BaseFragment fragmentAsView();

}
