package com.haikuowuya.core;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.haikuowuya.core.base.BaseAdapter;
import com.haikuowuya.core.base.BaseListAcitivity;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends BaseListAcitivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getListView().setAdapter(genAdapter());
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ListItem listItem = (ListItem) parent.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.setClassName(mActivity, listItem.cls);
                startActivity(intent);
            }
        });
        requestGetDataFromUrl("http://www.baidu.com");

    }

    public static class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler)
        {
            super(context, "mldn.db", null, 1, errorHandler);
        }

        public DBHelper(Context context )
        {
            super(context, "mldn.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String sql ="CREATE TABLE mytab (id INTEGER PRIMARY KEY, name VARCHAR(20) NOT NULL, birthday DATE NOT NULL )";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }

    private ListAdapter genAdapter()
    {
        BaseAdapter<ListItem> adapter = new BaseAdapter<ListItem>(mActivity, R.layout.layout_list_item, genData())
        {
            public void bindDataToView(View convertView, final ListItem listItem)
            {
                setTextViewText(convertView, R.id.tv_text, listItem.text);
            }
        };
        return adapter;
    }

    private List<ListItem> genData()
    {
        LinkedList<ListItem> listItems = new LinkedList<>();
        try
        {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            for (ActivityInfo info : packageInfo.activities)
            {
                ListItem item = new ListItem();
                String name = info.name;
                int resId = info.labelRes;
                if (resId > 0)
                {
                    item.text = getString(info.labelRes);
                }
                else
                {
                    item.text = info.name;
                }
                if (!info.name.contains(getPackageName()))
                {
                    name = getPackageName() + name;
                }
                item.cls = name;
                if (!item.cls.equals(this.getClass().getName()))
                {
                    listItems.add(item);
                }
            }
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return listItems;
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "首页Activity";
    }

    private static class ListItem
    {
        public String cls;
        public String text;
    }

}
