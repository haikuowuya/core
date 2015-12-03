package com.haikuowuya.demo.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseActivity;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.ShowMessageFromWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXAppExtendObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler
{
    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    private Button gotoBtn, regBtn, launchBtn, checkBtn;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    public static void actionWX( BaseActivity activity)
    {
        Intent intent = new Intent(activity, WXEntryActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_entry);   //TODO
        findViewById(R.id.linear_wx_container).setVisibility(View.VISIBLE);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, mActivity.getString(R.string.tv_wx_key));
        api.registerApp(mActivity.getString(R.string.tv_wx_key));
        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 将该app注册到微信
                api.registerApp(mActivity.getString(R.string.tv_wx_key));
            }
        });

        gotoBtn = (Button) findViewById(R.id.goto_send_btn);
        gotoBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(WXEntryActivity.this, SendToWXActivity.class));
//                finish();
            }
        });

        launchBtn = (Button) findViewById(R.id.launch_wx_btn);
        launchBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(WXEntryActivity.this, "launch result = " + api.openWXApp(), Toast.LENGTH_LONG).show();
            }
        });

        checkBtn = (Button) findViewById(R.id.check_timeline_supported_btn);
        checkBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                int wxSdkVersion = api.getWXAppSupportAPI();
                if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION)
                {
                    Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
                }
            }
        });

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req)
    {
        System.out.println("onReq");
        switch (req.getType())
        {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp)
    {
        int result = 0;
        switch (resp.errCode)
        {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
        System.out.println("onResp");
        mActivity.showToast("分享结果： " + getString(result));
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mActivity.finish();
            }
        }, 1000L);
    }

    private void goToGetMsg()
    {
        System.out.println("goToGetMsg");
        Intent intent = new Intent(this, GetFromWXActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
        finish();
    }

    private void goToShowMsg(ShowMessageFromWX.Req showReq)
    {
        System.out.println("goToShowMsg");
        WXMediaMessage wxMsg = showReq.message;
        WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;

        StringBuffer msg = new StringBuffer(); // 组织一个待显示的消息内容
        msg.append("description: ");
        msg.append(wxMsg.description);
        msg.append("\n");
        msg.append("extInfo: ");
        msg.append(obj.extInfo);
        msg.append("\n");
        msg.append("filePath: ");
        msg.append(obj.filePath);

        Intent intent = new Intent(this, ShowFromWXActivity.class);
        intent.putExtra(ShowFromWXActivity.STitle, wxMsg.title);
        intent.putExtra(ShowFromWXActivity.SMessage, msg.toString());
        intent.putExtra(ShowFromWXActivity.BAThumbData, wxMsg.thumbData);
        startActivity(intent);
        finish();
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "微信分享结果";
    }
}