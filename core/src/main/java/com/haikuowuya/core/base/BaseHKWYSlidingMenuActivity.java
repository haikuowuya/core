package com.haikuowuya.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.haikuowuya.core.util.DensityUtils;
import com.haikuowuya.core.R;
import com.haikuowuya.core.slidingmenu.SlidingMenu;


public abstract class BaseHKWYSlidingMenuActivity extends BaseHKWYTitleActivity
{
    protected SlidingMenu mSlidingMenu;

    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(layoutResID);
        initSlidingMenu();
    }

    @Override
    public void afterOnCreate(Bundle savedInstanceState)
    {
        super.afterOnCreate(savedInstanceState);
        setSwipeBackEnable(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            mSlidingMenu.toggle(true);
        }
        return true;
    }

    public void toggle()
    {
        mSlidingMenu.toggle(true);
    }

    public void closeSlidingMenu()
    {
        mSlidingMenu.showContent(true);
    }

    private void initSlidingMenu()
    {
        mSlidingMenu = new SlidingMenu(mActivity);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setShadowWidth(DensityUtils.dpToPx(mActivity, 120.f));
        mSlidingMenu.setBehindOffset(DensityUtils.dpToPx(mActivity, 120.f));
        mSlidingMenu.setFadeDegree(0.65f);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        mSlidingMenu.setBehindCanvasTransformer(
//                new SlidingMenu.CanvasTransformer()
//                {
//                    @Override
//                    public void transformCanvas(Canvas canvas, float percentOpen)
//                    {
//                        float scale = (float) (percentOpen * 0.25 + 0.75);
//                        canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
//                        // canvas.scale(percentOpen, 1, 0, 0);
//                    }
//                });
        mSlidingMenu.setMenu(R.layout.layout_menu_hkwy);
        // 设置隐藏在AboveMenu菜单后面的菜单
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_container, fragmentAsMenu()).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        BaseHKWYFragment baseHKWYFragment = fragmentAsMenu();
        if (null != baseHKWYFragment)
        {
            baseHKWYFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public abstract BaseHKWYFragment fragmentAsMenu();
}
