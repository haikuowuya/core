package com.haikuowuya.core.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboShareException;

public class ShareSina
{
    private Context context;

    private String share_txt = "";
    private Object share_pic = "";
    private String share_imgUrl;
    private String share_url = "";
    private String share_title = "";

    /**
     * 微博微博分享接口实例
     */
    public IWeiboShareAPI mWeiboShareAPI = null;

    public ShareSina(final Context context)
    {
        this.context = context;

        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, ShareConstant.SINA_CONSUMER_KEY);
        mWeiboShareAPI.registerApp();
        // 如果未安装微博客户端，设置下载微博对应的回调
        if (!mWeiboShareAPI.isWeiboAppInstalled())
        {
            mWeiboShareAPI.registerWeiboDownloadListener(new IWeiboDownloadListener()
            {
                @Override
                public void onCancel()
                {
                    Toast.makeText(context, "取消下载新浪微博!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /** ------------------start---------------------- */

    /***
     * 设置分享消息
     *
     * @param share_txt
     * @param share_pic
     * @param share_url
     * @param share_title
     */
    public void setMessage(String share_txt, Object share_pic, String share_imgUrl, String share_url, String share_title)
    {
        this.share_txt = share_txt;
        this.share_pic = share_pic;
        this.share_url = share_url;
        this.share_title = share_title;
        this.share_imgUrl = share_imgUrl;
    }

    /***
     * 启动分享
     */
    public void sendRequest()
    {
        try
        {
            // 检查微博客户端环境是否正常，如果未安装微博，弹出对话框询问用户下载微博客户端
            if (mWeiboShareAPI.checkEnvironment(true))
            {

                // 注册第三方应用 到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
                // 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
                sendSinaMessage();
            }
        } catch (WeiboShareException e)
        {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发送消息
     */
    private void sendSinaMessage()
    {
        if (mWeiboShareAPI.isWeiboAppSupportAPI())
        {
            int supportApi = mWeiboShareAPI.getWeiboAppSupportAPI();
            if (supportApi >= 10351 /* ApiUtils.BUILD_INT_VER_2_2 */)
            {
                sendMultiMessage();
            } else
            {
                sendSingleMessage();
            }
        } else
        {
            Toast.makeText(context, "微博客户端不支持 SDK 分享或微博客户端未安装或微博客户端是非官方版本", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。 注意：当
     * {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 时，支持同时分享多条da消息，
     * 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
     */
    private void sendMultiMessage()
    {

        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = getTextObj();// 文本
        ImageObject imageObject = getImageObj();
        if (imageObject != null)
        {
            weiboMessage.imageObject = imageObject;// 图片
        }

        // weiboMessage.mediaObject = getWebpageObj();//网页
        // weiboMessage.mediaObject = getMusicObj();//音乐
        // weiboMessage.mediaObject = getVideoObj();//视频
        /*
		 * if (hasVoice) { weiboMessage.mediaObject = getVoiceObj(); }
		 */

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mWeiboShareAPI.sendRequest(request);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。 当{@link IWeiboShareAPI#getWeiboAppSupportAPI()}
     * < 10351 时，只支持分享单条消息，即 文本、图片、网页、音乐、视频中的一种，不支持Voice消息。
     */
    private void sendSingleMessage()
    {
        // 1. 初始化微博的分享消息
        // 用户可以分享文本、图片、网页、音乐、视频中的一种
        WeiboMessage weiboMessage = new WeiboMessage();
        weiboMessage.mediaObject = getTextObj();// 文本
        ImageObject imageObject = getImageObj();
        if (imageObject != null)
        {
            weiboMessage.mediaObject = imageObject;// 图片
        }
        // weiboMessage.mediaObject = getWebpageObj();//网页
        // weiboMessage.mediaObject = getMusicObj();//音乐
        // weiboMessage.mediaObject = getVideoObj();//视频
		/*
		 * if (hasVoice) { weiboMessage.mediaObject = getVoiceObj(); }
		 */

        // 2. 初始化从第三方到微博的消息请求
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mWeiboShareAPI.sendRequest(request);
    }

    // 分享后回调(有问题)目前只能在activity下写
    public void handleResponse(Intent intent)
    {
        mWeiboShareAPI.handleWeiboResponse(intent, response);
    }

    /**
     * 接收微客户端博请求的数据。 当微博客户端唤起当前应用并进行分享时，该方法被调用。
     *
     * @param baseRequest
     * 微博请求数据对象
     * @see {@link IWeiboShareAPI#handleWeiboRequest}
     */

    IWeiboHandler.Response response = new IWeiboHandler.Response()
    {

        @Override
        public void onResponse(BaseResponse baseResp)
        {
            switch (baseResp.errCode)
            {
                case WBConstants.ErrorCode.ERR_OK:
                    Toast.makeText(context, "新浪分享成功!", Toast.LENGTH_SHORT).show();
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    Toast.makeText(context, "取消新浪分享!", Toast.LENGTH_SHORT).show();
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    if (baseResp.errMsg.contains("auth faild"))
                    {
                        Toast.makeText(context, "新浪认证失败，请稍后再试!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(context, "新浪分享失败!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    /**
     * 创建文本消息对象。
     *
     * @return 文本消息对象。
     */
    private TextObject getTextObj()
    {
        TextObject textObject = new TextObject();
        if (!TextUtils.isEmpty(share_txt))
        {
            textObject.text = share_txt;
        } else
        {
            textObject.text = ShareConstant.DEFAULT_SHARE_TITLE;
        }
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @return 图片消息对象。
     */
    private ImageObject getImageObj()
    {
        ImageObject imageObject = new ImageObject();
        Bitmap sharePic = null;
        if (share_pic != null)
        {
            if (share_pic instanceof Bitmap)
            {
                sharePic = (Bitmap) share_pic;
            } else if (share_pic instanceof String)
            {
                sharePic = BitmapFactory.decodeFile((String) share_pic);
            }
        } else
        {
            if (!TextUtils.isEmpty(share_imgUrl))
            {
                // 暂没加载图片
            }
        }

        if (sharePic == null)
        {
            return null;
        }
        imageObject.setImageObject(sharePic);
        return imageObject;
    }

}
