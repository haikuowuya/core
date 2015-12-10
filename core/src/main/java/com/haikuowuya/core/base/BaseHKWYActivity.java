package com.haikuowuya.core.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haikuowuya.core.R;

/**
 * 使用到的基类Activity
 */
public abstract class BaseHKWYActivity extends AppCompatActivity implements IActivityTitle
{
    protected BaseHKWYActivity mActivity;
    private ProgressDialog mProgressDialog;
    private FrameLayout mFrameContainer;
    private TextView mTvCenterTitle;
    private RelativeLayout mRelativeTitleContainer;
    private ImageView mIvRight;
    private ImageView mIvBack;
    protected SharedPreferences mPreferences;
    private Toast mCurrentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mPreferences = mActivity.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    public SharedPreferences getPreferences()
    {
        return mPreferences;
    }

    @Override
    public void setContentView(int layoutResID)
    {
        View contentView = LayoutInflater.from(mActivity).inflate(layoutResID, null);
        setContentView(contentView);
    }

    @Override
    public void setContentView(View view)
    {
        super.setContentView(R.layout.activity_base_hkwy);
        mFrameContainer = (FrameLayout) findViewById(R.id.frame_container);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mFrameContainer.addView(view, layoutParams);
        initTitleBar();

    }

    /**
     * 初始化TitleBar
     */
    private void initTitleBar()
    {
        mTvCenterTitle = (TextView) findViewById(R.id.tv_center_title);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        mRelativeTitleContainer = (RelativeLayout) findViewById(R.id.relative_title_container);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
    }

    protected RelativeLayout getTitleContainer()
    {
        return mRelativeTitleContainer;
    }

    protected void hideTitleContainer()
    {
        if (null != mRelativeTitleContainer && View.VISIBLE == mRelativeTitleContainer.getVisibility())
        {
            mRelativeTitleContainer.setVisibility(View.GONE);
        }
    }

    public void setOnBackClickListener(int backResId, View.OnClickListener onClickListener)
    {
        if (null != mIvBack)
        {
            if (mIvBack.getVisibility() == View.GONE)
            {
                mIvBack.setVisibility(View.VISIBLE);
            }
            if (backResId != 0)
            {
                mIvBack.setImageResource(backResId);
            }
            mIvBack.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void onBackPressed()
    {
        hideSoftKeyBorard();
        super.onBackPressed();
    }

    public void setOnRightClickListener(int rightResId, View.OnClickListener onClickListener)
    {
        if (null != mIvRight)
        {
            if (mIvRight.getVisibility() == View.GONE)
            {
                mIvRight.setVisibility(View.VISIBLE);
            }
            if (rightResId != 0)
            {
                mIvRight.setImageResource(rightResId);
            }
            mIvRight.setOnClickListener(onClickListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgressDialog()
    {
        showProgressDialog("请稍等，正在加载数据……");
    }

    public void showProgressDialog(String msg)
    {
        if (null == mProgressDialog)
        {
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setMessage(msg);
        }
        if (null != mProgressDialog && !mProgressDialog.isShowing())
        {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog()
    {
        if (null != mProgressDialog && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }

    public void setCenterTitle(CharSequence centerTitle)
    {
        mTvCenterTitle.setText(centerTitle);
    }

    public void setCenterTitle(CharSequence centerTitle, int textColor)
    {
        mTvCenterTitle.setTextColor(textColor);
        mTvCenterTitle.setText(centerTitle);
    }

    /**
     * 隐藏软键盘，根据当前焦点View
     */
    public void hideSoftKeyBorard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
        {
            if (imm.isActive())
            {
                View focusView = mActivity.getCurrentFocus();
                if (null != focusView)
                {
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                }
            }
        }
    }

    /**
     * 隐藏软键盘，根据给定的View
     *
     * @param view    view
     */
    public void hideSoftKeyBorard(View view)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && null != view)
        {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showToast(String text)
    {
        if (null != mCurrentToast)
        {
            mCurrentToast.cancel();
        }
        mCurrentToast = Toast.makeText(mActivity, text, Toast.LENGTH_SHORT);
        mCurrentToast.show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        hideSoftKeyBorard();
    }

}
