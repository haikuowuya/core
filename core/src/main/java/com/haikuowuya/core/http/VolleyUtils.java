package com.haikuowuya.core.http;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.haikuowuya.core.base.BaseHKWYActivity;
import com.squareup.okhttp.OkHttpClient;

/**
 * 作者： raiyi-suzhou
 * 日期： 2015/8/5 0005
 * 时间： 10:17
 * 说明：
 */
public class VolleyUtils
{
    private static RequestQueue sRequestQueue = null;

    public static RequestQueue getRequestQueueInstance(BaseHKWYActivity activity)
    {
        if (sRequestQueue == null)
        {
            sRequestQueue = Volley.newRequestQueue(activity, new OkHttpStack(new OkHttpClient()));
        }
        return sRequestQueue;
    }

    /**
     * Adds a request to the Volley request queue
     * @param baseActivity baseactivity
     * @param request is the request to add to the Volley queue
     */
    public static void addRequest(BaseHKWYActivity baseActivity, Request<?> request)
    {
        addRequest(baseActivity, request, baseActivity.getActivityTitle());
    }

    /**
     * Adds a request to the Volley request queue with a given tag
     *
     *    @param baseActivity baseactivity
     * @param request is the request to be added
     * @param tag     tag identifying the request
     */
    public static void addRequest(BaseHKWYActivity baseActivity, Request<?> request, CharSequence tag)
    {
        request.setTag(tag);
        addRequest(baseActivity, request);
    }

    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param baseActivity baseactivity
     * @param tag associated with the Volley requests to be cancelled
     */
    public static void cancelAllRequests(BaseHKWYActivity baseActivity, String tag)
    {
        if (getRequestQueueInstance(baseActivity) != null)
        {
            getRequestQueueInstance(baseActivity).cancelAll(tag);
        }
    }

}
