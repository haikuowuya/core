package com.haikuowuya.demo.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseAdapter;
import com.haikuowuya.demo.base.BaseFragment;
import com.haikuowuya.demo.view.ptr.PullRefreshLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Steven on 2015/12/3 0003.
 */
public class PTRFragment extends BaseFragment
{
    public static PTRFragment newInstance()
    {
        PTRFragment fragment = new PTRFragment();
        return fragment;
    }

    @Bind(R.id.lv_listview)
    ListView mListView;
    @Bind(R.id.ptr_layout)
    PullRefreshLayout mPullRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_base_list, container, false);
    }

    public ListView getListView()
    {
        return mListView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mPullRefreshLayout.setOnRefreshListener(new EmptyOnRefreshListener());
        mListView.setOnItemClickListener(new OnItemClickImpl());
        mListView.setAdapter(genAdapter());
    }

    private ListAdapter genAdapter()
    {

        BaseAdapter<ListItem> adapter = new BaseAdapter<ListItem>(mActivity, genData())
        {
            public void bindDataToView(View convertView, final ListItem listItem)
            {
                setTextViewText(convertView, R.id.tv_text, listItem.text);
            }

            @Override
            public int layoutResId()
            {
                return R.layout.layout_list_item;
            }
        };
        return adapter;
    }

    private List<ListItem> genData()
    {
        LinkedList<ListItem> listItems = new LinkedList<>();
        try
        {
            PackageInfo packageInfo = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), PackageManager.GET_ACTIVITIES);
            for (ActivityInfo info : packageInfo.activities)
            {
                ListItem item = new ListItem();
                String name = info.name;
                int resId = info.labelRes;
                if (resId > 0)
                {
                    item.text = getString(info.labelRes);
                } else
                {
                    item.text = info.name;
                }
                if (!info.name.contains(mActivity.getPackageName()))
                {
                    name = mActivity.getPackageName() + name;
                }
                item.cls = name;
                if (!item.cls.equals(this.getClass().getName()))
                {
                    listItems.add(item);
                }
            }
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return listItems;
    }

    private class EmptyOnRefreshListener implements com.haikuowuya.demo.view.ptr.PullRefreshLayout.OnRefreshListener
    {
        public void onRefresh()
        {
            mPullRefreshLayout.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    mPullRefreshLayout.setRefreshing(false);
                    //showCroutonToast("刷新成功");

                }
            }, 2000);
        }
    }

    private class OnItemClickImpl implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

            ListItem listItem = (ListItem) parent.getAdapter().getItem(position);
            Intent intent = new Intent();
            intent.setClassName(mActivity, listItem.cls);
            startActivity(intent);
        }
    }

    @Override
    public String getFragmentTitle()
    {
        return "下拉刷新";
    }

    private static class ListItem
    {
        public String cls;
        public String text;
    }
}
