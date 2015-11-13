package com.haikuowuya.core.base;

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
 * @param <T>
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter
{
    /***
     * 数据源数据
     */
    protected List<T> mData;
    /**
     * 适配器创建时所在的上下文
     */
    private BaseActivity mActivity;
    /***
     * layoutinflater对象
     */
    protected LayoutInflater mInflater;
    /***
     * 当前位置
     */
    protected int mPosition;

    public BaseAdapter(BaseActivity activity, List<T> data)
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
     * @return
     */
    public BaseActivity getActivity()
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
     * @return
     */
    public boolean isLastPosition()
    {
        return mPosition == mData.size() - 1;
    }

    /***
     * 数据和view绑定关联
     *
     * @param convertView
     * @param t
     */
    public abstract void bindDataToView(View convertView, T t);

    /***
     * 适配器的布局文件
     *
     * @return
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
     * @param convertView
     * @param viewId
     * @param text
     */
    public void setTextViewText(View convertView, int viewId, CharSequence text)
    {
        TextView textView = getView(convertView, viewId);
        textView.setText(text);
    }

    /***
     * TextView设置文字颜色
     *
     * @param convertView
     * @param viewId
     * @param textColor
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
     * @param convertView
     * @param viewId
     * @param listener
     */
    public void setViewOnClick(View convertView, int viewId, View.OnClickListener listener)
    {
        getView(convertView, viewId).setOnClickListener(listener);
    }

    /***
     * 设置convertView的点击事件
     *
     * @param convertView
     * @param listener
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
//		ImageLoader.getInstance().loadImage(imageUrl, new AbsImageLoadingListener()
//		{
//			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
//			{
//				if (imageView.getTag() != null && imageView.getTag().equals(imageUrl))
//				{
//					imageView.setImageBitmap(loadedImage);
//				}
//			}
//		});
    }

    /***
     * 设置图片的资源文件
     *
     * @param convertView
     * @param viewId
     * @param resId
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
