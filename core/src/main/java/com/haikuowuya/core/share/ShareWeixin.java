package com.haikuowuya.core.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.haikuowuya.core.R;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

public class ShareWeixin
{
    private static final int THUMB_SIZE = 100;

    private Context context;

    private IWXAPI api;

    public ShareWeixin(Context context)
    {
        this.context = context;

    }

    public static void doWeixinRegist(Context context)
    {
        IWXAPI api = WXAPIFactory.createWXAPI(context, ShareConstant.WEIXIN_APP_ID, true);
        api.registerApp(ShareConstant.WEIXIN_APP_ID);
    }

    private static String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void shareWeixin(boolean isPengyouquan, Context context, String filePath, String url, String title, String content)
    {
        doWeixinRegist(context);
        IWXAPI api = WXAPIFactory.createWXAPI(context, ShareConstant.WEIXIN_APP_ID);
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = (TextUtils.isEmpty(url)) ? ShareConstant.DEFAULT_SHARE_URL : url;

        WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title = (TextUtils.isEmpty(title)) ? ShareConstant.DEFAULT_SHARE_TITLE : title;
        msg.description = content;
        //System.out.println("朋友圈 内容 = " + content);
        Bitmap bmp = null;
        if (!TextUtils.isEmpty(filePath))
        {
            bmp = BitmapFactory.decodeFile(filePath);
        }
        if (bmp == null)
        {
            bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.share_default_ic);
        }
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        msg.thumbData = ShareUtils.bmpToByteArray(thumbBmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("" + System.currentTimeMillis());// 发送信息唯一标记
        req.message = msg;
        req.scene = isPengyouquan ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);

    }
}
