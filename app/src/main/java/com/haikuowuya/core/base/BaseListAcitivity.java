package com.haikuowuya.core.base;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.haikuowuya.core.R;
import com.haikuowuya.core.view.ptr.PullRefreshLayout;

import java.util.Arrays;

/**
 * 作者： raiyi-suzhou
 * 日期： 2015/7/24 0024
 * 时间： 14:14
 * 说明：
 */
public abstract class BaseListAcitivity extends BaseActivity
{
    private ListView mListView;
    private PullRefreshLayout mPullRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);//TODO XXX
        mListView = (ListView) findViewById(R.id.lv_listview);
        mPullRefreshLayout = (PullRefreshLayout) findViewById(R.id.ptr_layout);
        mPullRefreshLayout.setOnRefreshListener(new EmptyOnRefreshListener());
        mListView.setOnItemClickListener(new OnItemClickImpl());
        mListView.setAdapter(genAdapter());

    }

    private ListAdapter genAdapter()
    {

        BaseAdapter<String> adapter = new BaseAdapter<String>(mActivity, R.layout.layout_list_item, Arrays.asList(mActivity.getResources().getStringArray(R.array.list_arrays)))
        {
            @Override
            public void bindDataToView(View convertView, String s)
            {
                setTextViewText(convertView, R.id.tv_text, s);
            }
        }  ;

                return  adapter;
    }

    public ListView getListView()
    {
        return mListView;
    }

    private class EmptyOnRefreshListener implements PullRefreshLayout.OnRefreshListener
    {
        public void onRefresh()
        {
            mPullRefreshLayout.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    mPullRefreshLayout.setRefreshing(false);
                    showCroutonToast("刷新成功");

                }
            }, 2000);
        }
    }

    private class OnItemClickImpl implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            switch (position)
            {
                case 0:
                    mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
                    break;
                case 1:
                    mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);
                    break;
                case 2:
                    mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_ARC);
                    break;
                case 3:
                    mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
                    break;
                case 4:
                    mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
                    break;
            }
        }
    }
}
