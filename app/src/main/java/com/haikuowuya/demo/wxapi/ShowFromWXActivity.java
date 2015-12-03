package com.haikuowuya.demo.wxapi;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.haikuowuya.demo.R;
import com.haikuowuya.demo.base.BaseActivity;

public class ShowFromWXActivity extends BaseActivity
{
	public static final String STitle = "showmsg_title";
	public static final String SMessage = "showmsg_message";
	public static final String BAThumbData = "showmsg_thumb_data";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wx_show_from_wx);
		initView();
	}

	private void initView() {

		final String title = getIntent().getStringExtra(   STitle);
		final String message = getIntent().getStringExtra( SMessage);
		final byte[] thumbData = getIntent().getByteArrayExtra( BAThumbData);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		
		if (thumbData != null && thumbData.length > 0) {
			ImageView thumbIv = new ImageView(this);
			thumbIv.setImageBitmap(BitmapFactory.decodeByteArray(thumbData, 0, thumbData.length));
			builder.setView(thumbIv);
		}
		
		builder.show();
	}

	@Override
	public CharSequence getActivityTitle()
	{
		return getString(R.string.tv_activity_show_wx);
	}
}
