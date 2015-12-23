package com.haikuowuya.core.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.haikuowuya.core.R;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by raiyi-suzhou on 2015/7/10 0010.
 */
public class ShareUtils
{
    /**
     * 易信分享
     *
     * @param activity
     * @param sharecontent
     * @param isPengyouquan
     */
    public static void shareWithYixin(Activity activity, ShareContent sharecontent, boolean isPengyouquan)
    {
        try
        {
            JsonShareItem shareYixinItem;
            if (isPengyouquan)
            {
                shareYixinItem = sharecontent.getShareContent(ShareConstant.YIXIN_FRIENDS_SHARE_S);
            } else
            {
                shareYixinItem = sharecontent.getShareContent(ShareConstant.YIXIN_FRIEND_SHARE_S);
            }
            String share_title = shareYixinItem.getShareTitle();
            String share_content = shareYixinItem.getShareContent();
            String share_url = shareYixinItem.getShareUrl();
            ShareYiXin yixinshare = new ShareYiXin();
            String APP_ID = "yxf098afdb68ea4d248a3e8118e0f54f06";
            yixinshare.init(activity, APP_ID);
            yixinshare.sendText(share_content, false);
        } catch (Exception e)
        {
        }
    }

    /**
     * 短信分享
     */
    public static void shareWithSMS(Activity activity, ShareContent shareContent)
    {
        JsonShareItem shareSMSItem = shareContent.getShareContent(ShareConstant.SMS_SHARE_S);
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        String content = shareSMSItem.getShareTitle() + " " + shareSMSItem.getShareContent() + " " + shareSMSItem.getShareUrl();
        content = shareSMSItem.getShareContent() + " " + shareSMSItem.getShareUrl();
        intent.putExtra("sms_body", content);
        activity.startActivity(intent);
    }

    /**
     * 邮件分享
     */
    public static void shareWithEmail(Activity activity, ShareContent shareContent)
    {
        JsonShareItem shareEmailItem = shareContent.getShareContent(ShareConstant.EMAIL_SHARE_S);
        try
        {
            String shareUrl = shareEmailItem.getShareUrl();
            String contentEmail = shareEmailItem.getShareContent() + shareUrl;
            String subjectEmail = shareEmailItem.getShareTitle();
            Intent data = new Intent(Intent.ACTION_SENDTO);
            data.setData(Uri.parse("mailto:"));
            data.putExtra(Intent.EXTRA_SUBJECT, subjectEmail);
            data.putExtra(Intent.EXTRA_TEXT, contentEmail);
            activity.startActivity(data);
        } catch (Exception e1)
        {
            Toast.makeText(activity, "未检测到邮件客户端", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * sina分享
     */
    public static void shareWithSina(Activity activity, ShareContent shareContent)
    {
        ShareSina shareSina = new ShareSina(activity);
        JsonShareItem shareSinaItem = shareContent.getShareContent(ShareConstant.SINA_WEIBO_SHARE_S);
        String share_title = shareSinaItem.getShareTitle();
        String share_content = shareSinaItem.getShareContent();
        String share_url = shareSinaItem.getShareUrl();
        String share_txt = share_content;
        share_txt = share_txt + share_url;
        Object share_pic = null;
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_crop);
        String filename = ShareUtils.decodeBitmap2File(activity, bitmap);
        share_pic = filename;
        shareSina.setMessage(share_txt, share_pic, null, share_url, share_title);
        shareSina.sendRequest();
    }

    public static boolean checkWXIsExist(Activity activity)
    {
        String packageName = "com.tencent.mm";

        try
        {
            @SuppressWarnings("unused") ApplicationInfo info = activity.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e)
        {
        }
        return false;

    }

    public static boolean checkQQIsExist(Activity activity)
    {
        String packageName = "com.tencent.mobileqq";
        boolean flag = false;
        try
        {
            @SuppressWarnings("unused") ApplicationInfo info = activity.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e)
        {
        }
        return false;

    }

    /**
     * 微信分享
     *
     * @param sharecontent
     * @param isPengyouquan
     */
    public static void shareWithWeixin(Activity activity, ShareContent sharecontent, boolean isPengyouquan)
    {
        shareWithWeixin(activity, sharecontent, isPengyouquan, null);
    }

    /**
     * 微信分享
     *
     * @param sharecontent
     * @param isPengYouQuan
     */
    public static void shareWithWeixin(Activity activity, ShareContent sharecontent, boolean isPengYouQuan, Bitmap bitmap)
    {
        JsonShareItem shareWeixinItem;
        ShareWeixin shareWeixin = new ShareWeixin(activity);
        if (isPengYouQuan)
        {
            shareWeixinItem = sharecontent.getShareContent(ShareConstant.WEIXIN_FRIENDS_SHARE_S);
        } else
        {
            shareWeixinItem = sharecontent.getShareContent(ShareConstant.WEIXIN_FRIEND_SHARE_S);
        }
        boolean isWXExist = checkWXIsExist(activity);
        if (!isWXExist)
        {
            Toast.makeText(activity, "您未安装微信哦", Toast.LENGTH_SHORT).show();
            return;
        }
        String share_title = shareWeixinItem.getShareTitle();
        if (!isPengYouQuan)
        {
            share_title = activity.getString(R.string.app_name);
        }
        String share_content = shareWeixinItem.getShareContent();
        System.out.println("微信 分享 内容  = " + share_content);
        String share_url = shareWeixinItem.getShareUrl();
        if (null == bitmap)
        {
            bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_crop);
        }
        String filename = ShareUtils.decodeBitmap2File(activity, bitmap);
        shareWeixin.shareWeixin(isPengYouQuan, activity, filename, share_url, share_title, share_content);
    }

    public static final void shareWithQQ(Activity activity, ShareContent shareContent, boolean withQzone, Bitmap bitmap)
    {
        boolean isQQExist = checkQQIsExist(activity);
        if (!isQQExist)
        {
            Toast.makeText(activity, "您未安装QQ哦", Toast.LENGTH_SHORT).show();
            return;
        }
        JsonShareItem shareItem = null;
        if (withQzone)
        {
            shareItem = shareContent.getShareContent(ShareConstant.QQ_QZONE);
        } else
        {
            shareItem = shareContent.getShareContent(ShareConstant.QQ);
        }
        ShareQQ shareQQ = new ShareQQ();
        String share_title = "测试QQ分享", share_content = "测试QQ分享", share_url = "http://haikuowuya.com";
        if (null != shareItem)
        {
            share_title = shareItem.getShareTitle();
            share_content = shareItem.getShareContent();
            share_url = shareItem.getShareUrl();
        }
        Bundle bundle = new Bundle();

        if (withQzone)
        {
            bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
            bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, share_title);
            bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, share_url);
            bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, share_content);
            shareQQ.doShareToQzone(activity, bundle);
        } else
        {
            bundle.putString(QQShare.SHARE_TO_QQ_TITLE, share_title);
            bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, share_url);
            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, share_content);
            shareQQ.doShareToQQ(activity, bundle);
        }
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle)
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle)
        {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try
        {
            output.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static String decodeBitmap2File(Context context, Bitmap bmp)
    {
        if (bmp == null)
        {
            return null;
        }
        String filename = "" + System.currentTimeMillis();
        String file = "";
        String file_dir = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().exists())
        {
            file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gdyp/sharecache/" + filename;
            file_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gdyp/sharecache/";
        } else
        {
            file = context.getFilesDir().getAbsolutePath() + "/gdyp/sharecache/" + filename;
            file_dir = context.getFilesDir().getAbsolutePath() + "/gdyp/sharecache/";
        }
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try
        {
            File file_pic = new File(file);
            File file_pic_dir = new File(file_dir);
            if (!file_pic_dir.exists())
            {
                file_pic_dir.mkdirs();
            }
            if (!file_pic.exists())
            {
                file_pic.createNewFile();
            }
            stream = new FileOutputStream(file_pic);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        boolean isSuccess = false;
        if (stream != null && format != null)
        {
            isSuccess = bmp.compress(format, quality, stream);
        }
        if (isSuccess)
        {
            return file;
        }
        return null;
    }

    public static final String QQ_APP_ID = "1104965199";
}
