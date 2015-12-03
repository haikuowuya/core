package com.haikuowuya.demo.base;

import android.os.Bundle;

import com.haikuowuya.demo.helper.SwipeBackActivityHelper;
import com.haikuowuya.demo.view.SwipeBackFrameLayout;

/**
 * Created by Steven on 2015/11/12 0012.
 */
public abstract class BaseSwipeBackActivity extends BaseActivity implements SwipeBackFrameLayout.SwipeBackListener
{

    /***
     * 滑动结束help
     */
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    //==================================支持滑动结束Activity=============================================================================

    public SwipeBackFrameLayout getSwipeBackLayout()
    {
        return mHelper.getSwipeBackLayout();
    }

    public void setSwipeBackEnable(boolean enable)
    {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    public void scrollToFinishActivity()
    {
//        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();

    }

    @Override
    public void scrollToNextActivity()
    {

    }



    //==================================支持滑动结束Activity=============================================================================
}
