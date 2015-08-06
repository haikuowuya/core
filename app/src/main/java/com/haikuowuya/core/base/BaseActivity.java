package com.haikuowuya.core.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.haikuowuya.core.R;
import com.haikuowuya.core.drawable.DrawerArrowDrawable;
import com.haikuowuya.core.http.VolleyUtils;
import com.haikuowuya.core.util.IntentUtils;
import com.haikuowuya.core.util.ToastUtils;
import com.haikuowuya.core.util.ViewUtils;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivityTitle
{
    protected BaseActivity mActivity;
    protected SharedPreferences mPreferences;
    private FrameLayout mFrameContainer;
    private TextView mTvCenterTitle;
    /**
     * 左上角的返回按钮
     */
    private ImageView mIvBack;

    public SharedPreferences getPreferences()
    {
        return mPreferences;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        int statusCoclor = getResources().getColor(R.color.color_title_background_color);
        ViewUtils.alphaStatusBarAndNavBar(mActivity, statusCoclor, 0xFF000000);
    }

    public void showToast(String text)
    {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    public void showCroutonToast(String text)
    {
        ToastUtils.showCrouton(mActivity, text, getContentViewGroup());
    }

    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(R.layout.activity_base);   //TODO XXX
        mFrameContainer = (FrameLayout) findViewById(R.id.frame_container);
        mTvCenterTitle = (TextView) findViewById(R.id.tv_center_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvCenterTitle.setText(getActivityTitle());
        View contentView = LayoutInflater.from(mActivity).inflate(layoutResID, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mFrameContainer.addView(contentView, layoutParams);
        mIvBack.setImageDrawable(new DrawerArrowDrawable.BackDrawerArrowDrawable(mActivity));
//        String action = getIntent().getAction();
//        System.out.println("action = " + action);
        if (IntentUtils.isLauncherIntent(getIntent()))
        {
            mIvBack.setVisibility(View.GONE);
        }
        mIvBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

//        if( !IntentUtils.isLauncherIntent(getIntent()))
//        {
//           mFrameContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                @Override
//                public boolean onPreDraw() {
//                    mFrameContainer.getViewTreeObserver().removeOnPreDrawListener(this);
//                    mFrameContainer.setScaleY(0.1f);
//                    mFrameContainer.setPivotY(mFrameContainer.getY() + mFrameContainer.getHeight() / 2);
//                    mFrameContainer.animate().scaleY(1)
//                            .setDuration(200)
//                            .setInterpolator(new AccelerateInterpolator())
//                            .start();
//                    return true;
//                }
//            });
//        }
    }

    public ViewGroup getContentViewGroup()
    {
        return mFrameContainer;
    }

    public void requestGetDataFromUrl(String url)
    {
        ListenerImpl listener = new ListenerImpl();
        Request request = new StringRequest(Request.Method.GET, url, listener, listener);
        VolleyUtils.getRequestQueueInstance(mActivity).add(request);
    }

    public void onGetDataSuccess(String string)
    {
        System.out.println("result string = " + string);
    }

    private class ListenerImpl implements Response.Listener, Response.ErrorListener
    {

        public void onErrorResponse(VolleyError error)
        {

        }

        @Override
        public void onResponse(Object response)
        {

            onGetDataSuccess(response.toString());
        }
    }

}
