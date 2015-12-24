package com.haikuowuya.core.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.haikuowuya.core.R;
import com.haikuowuya.core.base.BaseHKWYActivity;
import com.haikuowuya.core.share.ShareConstant;
import com.haikuowuya.core.share.ShareContent;
import com.haikuowuya.core.share.ShareGridAdapter;
import com.haikuowuya.core.share.ShareUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by raiyi-suzhou on 2015/7/10 0010.
 */
public class SharePopupWindowUtils
{
    private static SharePopupWindow mPopupWindow;

    private static void initPopupWindow(BaseHKWYActivity activity)
    {
        mPopupWindow = new SharePopupWindow(activity);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mPopupWindow.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mPopupWindow.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mPopupWindow.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        mPopupWindow.setAnimationStyle(R.style.AnimBottom);
        // 设置SelectPicPopupWindow弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0000000000));

    }

    private static void showShare(BaseHKWYActivity baseActivity, View contentView)
    {
        initPopupWindow(baseActivity);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.showAtLocation(baseActivity.getContentViewGroup(), Gravity.BOTTOM, 0, 0);
    }

    public static void showShare(BaseHKWYActivity baseActivity, String shareContent)
    {
        initPopupWindow(baseActivity);
        int[] shareTypes = new int[]{ShareConstant.WEIXIN_FRIEND_SHARE, ShareConstant.WEIXIN_FRIENDS_SHARE, ShareConstant.SINA_WEIBO_SHARE, ShareConstant.SMS_SHARE, ShareConstant.EMAIL_SHARE, ShareConstant.QQ_SHARE, ShareConstant.QQ_QZONE_SHARE, ShareConstant.COPY_SHARE, ShareConstant.YIXIN_FRIEND_SHARE, ShareConstant.YIXIN_FRIENDS_SHARE};
        String[] shareTypeNames = new String[]{"微信", "朋友圈", "微博", "信息", "邮件", "QQ好友", "QQ空间", "剪切板", "易信", "易信朋友圈"};
        Integer[] shareTypeIcons = new Integer[]{R.mipmap.share_weixin_bg, R.mipmap.share_weixinq_bg, R.mipmap.share_weibo_bg, R.mipmap.share_sms_bg, R.mipmap.share_email_bg, R.mipmap.ic_share_qq, R.mipmap.ic_share_qq_qzone, R.mipmap.share_default_ic, R.mipmap.ic_share_yinxin, R.mipmap.ic_share_yinxin};
        List<ShareGridAdapter.ShareItem> shareItems = new LinkedList<>();
        int count = shareTypes.length >= shareTypeNames.length ? shareTypeNames.length : shareTypes.length;
        count = count >= shareTypeIcons.length ? shareTypeIcons.length : count;
        if (count > 8)
        {
            count = 8;//去除易信
        }
        for (int i = 0; i < count; i++)
        {
            ShareGridAdapter.ShareItem shareItem = new ShareGridAdapter.ShareItem();
            shareItem.resId = shareTypeIcons[i];
            shareItem.text = shareTypeNames[i];
            shareItem.type = shareTypes[i];
            shareItems.add(shareItem);
        }
        String shareJson = ShareContent.getShareJsonByTypes(shareTypes, baseActivity.getString(R.string.app_name), "", shareContent, "");
        View contentView = LayoutInflater.from(baseActivity).inflate(R.layout.layout_share_popupwindow, null);
        GridView gridView = (GridView) contentView.findViewById(R.id.gv_gridview);
        gridView.setAdapter(new ShareGridAdapter(baseActivity, shareItems));
        gridView.setSelector(new ColorDrawable(0x00000000));
        View viewView = contentView.findViewById(R.id.view_view);
        Button btnCancel = (Button) contentView.findViewById(R.id.btn_cancel);
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharePopupWindowUtils.dismiss();
            }
        };
        btnCancel.setOnClickListener(onClickListener);
        viewView.setOnClickListener(onClickListener);
        gridView.setOnItemClickListener(new OnItemClickListenerImpl(baseActivity, shareJson));
        mPopupWindow.setContentView(contentView);
        mPopupWindow.showAtLocation(baseActivity.getContentViewGroup(), Gravity.BOTTOM, 0, 0);
    }

    public static void showShare(BaseHKWYActivity baseActivity, List<ShareGridAdapter.ShareItem> shareItems, String shareJson)
    {
        initPopupWindow(baseActivity);
        View contentView = LayoutInflater.from(baseActivity).inflate(R.layout.layout_share_popupwindow, null);
        GridView gridView = (GridView) contentView.findViewById(R.id.gv_gridview);
        gridView.setAdapter(new ShareGridAdapter(baseActivity, shareItems));
        View viewView = contentView.findViewById(R.id.view_view);
        Button btnCancle = (Button) contentView.findViewById(R.id.btn_cancel);
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharePopupWindowUtils.dismiss();
            }
        };
        btnCancle.setOnClickListener(onClickListener);
        viewView.setOnClickListener(onClickListener);
        gridView.setOnItemClickListener(new OnItemClickListenerImpl(baseActivity, shareJson));
        mPopupWindow.setContentView(contentView);
        mPopupWindow.showAtLocation(baseActivity.getContentViewGroup(), Gravity.BOTTOM, 0, 0);
    }

    public static void showShare(BaseHKWYActivity baseActivity, List<ShareGridAdapter.ShareItem> shareItems, String shareJson, Bitmap bitmap)
    {
        initPopupWindow(baseActivity);
        View contentView = LayoutInflater.from(baseActivity).inflate(R.layout.layout_share_popupwindow, null);
        GridView gridView = (GridView) contentView.findViewById(R.id.gv_gridview);
        gridView.setAdapter(new ShareGridAdapter(baseActivity, shareItems));
        View viewView = contentView.findViewById(R.id.view_view);
        Button btnCancle = (Button) contentView.findViewById(R.id.btn_cancel);
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharePopupWindowUtils.dismiss();
            }
        };
        btnCancle.setOnClickListener(onClickListener);
        viewView.setOnClickListener(onClickListener);
        gridView.setSelector(new ColorDrawable(0x00000000));
        gridView.setOnItemClickListener(new OnItemClickListenerImpl(baseActivity, shareJson, bitmap));
        mPopupWindow.setContentView(contentView);
        mPopupWindow.showAtLocation(baseActivity.getContentViewGroup(), Gravity.BOTTOM, 0, 0);
    }

    private static class OnItemClickListenerImpl implements AdapterView.OnItemClickListener
    {
        private BaseHKWYActivity activity;
        private String shareJson;
        private Bitmap mBitmap;

        public OnItemClickListenerImpl(BaseHKWYActivity activity, String shareJson)
        {
            this.activity = activity;
            this.shareJson = shareJson;
            this.mBitmap = null;
        }

        public OnItemClickListenerImpl(BaseHKWYActivity activity, String shareJson, Bitmap bitmap)
        {
            this.activity = activity;
            this.shareJson = shareJson;
            this.mBitmap = bitmap;
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            ShareGridAdapter.ShareItem shareItem = (ShareGridAdapter.ShareItem) parent.getAdapter().getItem(position);
            ShareContent shareContent = new ShareContent(shareJson);
            if (shareItem.type == ShareConstant.EMAIL_SHARE)
            {
                ShareUtils.shareWithEmail(activity, shareContent);
            } else if (shareItem.type == ShareConstant.SMS_SHARE)
            {
                ShareUtils.shareWithSMS(activity, shareContent);
            } else if (shareItem.type == ShareConstant.WEIXIN_FRIEND_SHARE)
            {
                ShareUtils.shareWithWeixin(activity, shareContent, false, mBitmap);
            } else if (shareItem.type == ShareConstant.WEIXIN_FRIENDS_SHARE)
            {

                ShareUtils.shareWithWeixin(activity, shareContent, true, mBitmap);
            } else if (shareItem.type == ShareConstant.SINA_WEIBO_SHARE)
            {
                ShareUtils.shareWithSina(activity, shareContent);
            } else if (shareItem.type == ShareConstant.YIXIN_FRIEND_SHARE)
            {
                ShareUtils.shareWithYixin(activity, shareContent, false);
            } else if (shareItem.type == ShareConstant.YIXIN_FRIENDS_SHARE)
            {
                ShareUtils.shareWithYixin(activity, shareContent, true);
            } else if (shareItem.type == ShareConstant.QQ_SHARE)
            {
                ShareUtils.shareWithQQ(activity, shareContent, false, mBitmap);
            } else if (shareItem.type == ShareConstant.QQ_QZONE_SHARE)
            {
                ShareUtils.shareWithQQ(activity, shareContent, true, mBitmap);
            } else if (shareItem.type == ShareConstant.COPY_SHARE)
            {
                ShareUtils.ShareCopyToClip(activity, shareContent);
            } else
            {
                activity.showToast("未知错误，请重试");
            }
            dismiss();
        }
    }

    public static void dismiss()
    {
        if (null != mPopupWindow && mPopupWindow.isShowing())
        {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }

    public static class SharePopupWindow extends PopupWindow
    {
        private Context mContext;

        public SharePopupWindow(Context context)
        {
            super(context);
            mContext = context;
        }

        public SharePopupWindow(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            mContext = context;
        }

        public SharePopupWindow(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
            mContext = context;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public SharePopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
        {
            super(context, attrs, defStyleAttr, defStyleRes);
            mContext = context;
        }

        @Override
        public void showAtLocation(View parent, int gravity, int x, int y)
        {
            super.showAtLocation(parent, gravity, x, y);
            setbackgroundAlpha(0.5f);
        }

        @Override
        public void showAsDropDown(View anchor)
        {
            super.showAsDropDown(anchor);
            setbackgroundAlpha(0.5f);
        }

        @Override
        public void showAsDropDown(View anchor, int xoff, int yoff)
        {
            super.showAsDropDown(anchor, xoff, yoff);
            setbackgroundAlpha(0.5f);
        }

        public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
        {
            super.showAsDropDown(anchor, xoff, yoff, gravity);
            setbackgroundAlpha(0.5f);
        }

        @Override
        public void dismiss()
        {
            super.dismiss();
            setbackgroundAlpha(1f);
        }

        /**
         * 设置添加屏幕的背景透明度
         *
         * @param bgAlpha
         */
        public void setbackgroundAlpha(float bgAlpha)
        {
            Window window = ((Activity) mContext).getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.alpha = bgAlpha;
            window.setAttributes(lp);
        }
    }

}
