package com.haikuowuya.core.util;

import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * 手机注册、忘记密码时需要手机验证码操作时的工具，用于验证码发送成功后的提示操作
 */
public class SendSmsCodeUtils
{
    /***
     * 验证码发送成功后的操作，
     *
     * @param handler  handler
     * @param textView textview
     */
    public static void sendSmsCodeSuccess(Handler handler, TextView textView)
    {
        sendSmsCodeSuccess(handler, textView, 60, "获取验证码");
    }

    /**
     * 提示剩余时间
     *
     * @param textView      ：textView
     * @param remainderTime :剩余时间
     */
    private static void setRemainderTimeHint(TextView textView, int remainderTime)
    {
        int textColor = 0xFF333333;
        int remainderTextColor = 0xFFE22828;
        setRemainderTimeHint(textView, remainderTime, textColor, remainderTextColor);
    }

    /***
     * 提示剩余时间
     *
     * @param textView           ：textview
     * @param remainderTime      :剩余时间
     * @param textColor          :文字颜色(剩余……)
     * @param remainderTextColor :剩余时间颜色
     */
    private static void setRemainderTimeHint(TextView textView, int remainderTime, int textColor, int remainderTextColor)
    {
        textView.setEnabled(false);
        textView.setTextColor(remainderTextColor);
        String hint1 = "剩余 ";
        String hint2 = " 秒";
        String timeStr = remainderTime + "";
        if (remainderTime < 10)
        {
            timeStr = "0" + remainderTime;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(hint1 + timeStr + hint2);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(textColor), 0, hint1.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(textColor), spannableStringBuilder.length() - hint2.length(), spannableStringBuilder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

    /**
     * @param handler       handler
     * @param textView      textview
     * @param countTime     总共时间
     * @param finalHintText 时间完成后的提示文字
     */
    public static void sendSmsCodeSuccess(final Handler handler, final TextView textView, final int countTime, final CharSequence finalHintText)
    {
        final long beginTime = System.currentTimeMillis();
        if (countTime < 0)
        {
            System.out.println("剩余时间不可以小于0");
            return;
        }
        String hintText = "获取验证码";
        if (!TextUtils.isEmpty(finalHintText))
        {
            hintText = finalHintText + "";
        }
        final String finalHintText1 = hintText;
        Runnable timeRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                int time = (int) (countTime - (System.currentTimeMillis() - beginTime) / 1000);
                if (time > 0)
                {
                    setRemainderTimeHint(textView, time);
                    handler.postDelayed(this, 1000L);
                } else
                {
                    textView.setEnabled(true);
                    textView.setText(finalHintText1);
                }
            }
        };
        handler.postDelayed(timeRunnable, 0);
    }
}