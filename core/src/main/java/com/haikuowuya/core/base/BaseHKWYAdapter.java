package com.haikuowuya.core.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/***
 * 简化适配器的写法，
 *
 * @param <T> t
 */
public abstract class BaseHKWYAdapter<T> extends android.widget.BaseAdapter
{
    /***
     * 数据源数据
     */
    protected List<T> mData;
    /**
     * 适配器创建时所在的上下文
     */
    private Activity mActivity;
    /***
     * layoutinflater对象
     */
    protected LayoutInflater mInflater;
    /***
     * 当前位置
     */
    protected int mPosition;

    public BaseHKWYAdapter(Activity activity, List<T> data)
    {
        this.mData = data;
        this.mActivity = activity;
        mInflater = LayoutInflater.from(mActivity);
    }

    public T getPositionData(int position)
    {
        T t = null;
        if (null != mData && position < mData.size())
        {
            t = mData.get(position);
        }
        return t;
    }

    public LayoutInflater getInflater()
    {
        return mInflater;
    }

    /****
     * 获取创建adapter时的上下文对象
     *
     * @return      Activity
     */
    public Activity getActivity()
    {
        return mActivity;
    }

    @Override
    public int getCount()
    {
        return null == mData ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null == mData ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = mInflater.inflate(layoutResId(), null);
        }
        mPosition = position;

        bindDataToView(convertView, mData.get(position));
        return convertView;
    }

    public int getPosition()
    {
        return mPosition;
    }

    /***
     * 判断是否是最后一个view
     *
     * @return    boolean
     */
    public boolean isLastPosition()
    {
        return mPosition == mData.size() - 1;
    }

    /***
     * 数据和view绑定关联
     *
     * @param convertView    convertView
     * @param t    t
     */
    public abstract void bindDataToView(View convertView, T t);

    /***
     * 适配器的布局文件
     *
     * @return  适配器的布局文件
     */
    public abstract int layoutResId();

    /***
     * View是否可见
     *
     * @param convertView ：复用的对象
     * @param viewId      ：viewid
     * @param visibility  :是否可见
     */
    public void setViewVisibility(View convertView, int viewId, int visibility)
    {
        View view = getView(convertView, viewId);
        view.setVisibility(visibility);
    }

    /***
     * TextView设置文字
     *
     * @param convertView  convertView
     * @param viewId      viewId
     * @param text       text
     */
    public void setTextViewText(View convertView, int viewId, CharSequence text)
    {
        TextView textView = getView(convertView, viewId);
        textView.setText(text);
    }

    /***
     * TextView设置文字颜色
     *
     * @param convertView   convertView
     * @param viewId       viewId
     * @param textColor        textColor
     */
    public void setTextViewTextColor(View convertView, int viewId, int textColor)
    {
        TextView textView = getView(convertView, viewId);
        textView.setTextColor(textColor);
    }

    public void setViewBackgroundResId(View convertView, int viewId, int resId)
    {
        getView(convertView, viewId).setBackgroundResource(resId);
    }

    public void setViewBackgroundColor(View convertView, int viewId, int color)
    {
        getView(convertView, viewId).setBackgroundColor(color);
    }

    /***
     * 设置View的点击事件
     *
     * @param convertView convertView
     * @param viewId      viewId
     * @param listener    listener
     */
    public void setViewOnClick(View convertView, int viewId, View.OnClickListener listener)
    {
        getView(convertView, viewId).setOnClickListener(listener);
    }

    /***
     * 设置convertView的点击事件
     *
     * @param convertView convertView
     * @param listener    listener
     */
    public void setConvertViewOnClick(View convertView, View.OnClickListener listener)
    {
        convertView.setOnClickListener(listener);
    }

    public void setCurrentProgress(View convertView, int viewId, int progress)
    {
        ProgressBar progressBar = getView(convertView, viewId);
        progressBar.setProgress(progress);

    }

    public void setImageViewDrawable(View convertView, int viewId, Drawable drawable)
    {
        ImageView imageView = getView(convertView, viewId);
        imageView.setImageDrawable(drawable);
    }

    public void setImageViewDrawableFromUrl(View convertView, int viewId, final String imageUrl, int defaultResId)
    {
        final ImageView imageView = getView(convertView, viewId);
        imageView.setImageResource(defaultResId);
        imageView.setTag(imageUrl);
    }

    /***
     * 设置图片的资源文件
     *
     * @param convertView convertView
     * @param viewId      viewId
     * @param resId       resId
     */
    public void setImageViewResId(View convertView, int viewId, int resId)
    {
        ImageView imageView = getView(convertView, viewId);
        imageView.setImageResource(resId);
    }

    public <W extends View> W getView(View convertView, int viewId)
    {
        return ViewHolder.getView(convertView, viewId);
    }

    /**
     * ViewHolder
     */
    public static class ViewHolder
    {
        @SuppressWarnings("unchecked")
        public static <T extends View> T getView(View convertView, int viewId)
        {
            SparseArray<View> viewHolder = null;
            if (null != convertView.getTag())
            {
                viewHolder = (SparseArray<View>) convertView.getTag();
            }
            if (null == viewHolder)
            {
                viewHolder = new SparseArray<View>();
                convertView.setTag(viewHolder);
            }
            View childView = viewHolder.get(viewId);
            if (null == childView)
            {
                childView = convertView.findViewById(viewId);

                viewHolder.put(viewId, childView);
            }
            return (T) childView;
        }
    }

}
