package com.haikuowuya.core.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.File;

import im.yixin.sdk.api.IYXAPI;
import im.yixin.sdk.api.SendMessageToYX;
import im.yixin.sdk.api.YXAPIFactory;
import im.yixin.sdk.api.YXImageMessageData;
import im.yixin.sdk.api.YXMessage;
import im.yixin.sdk.api.YXTextMessageData;
import im.yixin.sdk.util.BitmapUtil;

/**
 * 
 * ClassName: ShareYiXin<br>
 * Description: 易信分享请求封装类
 */
public class ShareYiXin {

	/** 默认缩略图大小 */
	private static final int THUMB_SIZE = 80;

	/** 易信分享类对象 */
	private IYXAPI api;

	/**
	 * 初始化易信分享类对象
	 */
	public ShareYiXin init(Context context, String APP_ID) {
		// api = YXAPIFactory.createYXAPI(context, YixinConstants.TEST_APP_ID);
		
		api = YXAPIFactory.createYXAPI(context, APP_ID);
		api.registerApp();

		return this;
	}

	/**
	 * sendText,分享文本内容到易信
	 * 
	 * @param text
	 *            要分享的内容
	 * @param scene
	 *            是否分享到朋友圈
	 */
	public void sendText(String text, boolean scene) throws Exception {
		if (text == null || text.length() == 0) {
			return;
		}

		// 初始化一个YXTextObject对象
		YXTextMessageData textObj = new YXTextMessageData();
		textObj.text = text;

		// 用YXTextObject对象初始化一个YXMessage对象
		YXMessage msg = new YXMessage();
		msg.messageData = textObj;
		// 发送文本类型的消息时，title字段不起作用
		// msg.title = "title is ignored";
		msg.description = text;

		// 构造一个Req对象
		SendMessageToYX.Req req = new SendMessageToYX.Req();
		// transaction字段用于唯一标识一个请求
		req.transaction = buildTransaction("text");
		req.message = msg;
		req.scene = scene ? SendMessageToYX.Req.YXSceneTimeline : SendMessageToYX.Req.YXSceneSession;

		// 调用api接口发送数据到易信
		api.sendRequest(req);
	}

	/**
	 * sendImage,分享二进制图片到易信
	 * 
	 * @param context
	 *            上下文
	 * @param bmp
	 *            要分享的图片
	 * @param scene
	 *            是否分享到朋友圈
	 */
	public void sendImage(Context context, Bitmap bmp, boolean scene) throws Exception {
		// Bitmap bmp = BitmapFactory.decodeResource(getResources(),
		// R.drawable.test300);
		YXImageMessageData imgObj = new YXImageMessageData(bmp);

		YXMessage msg = new YXMessage();
		msg.messageData = imgObj;
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 200, 200, true);
		bmp.recycle();
		msg.thumbData = BitmapUtil.bmpToByteArray(thumbBmp, true); // 设置缩略图

		SendMessageToYX.Req req = new SendMessageToYX.Req();
		req.transaction = buildTransaction("img");
		req.message = msg;
		req.scene = scene ? SendMessageToYX.Req.YXSceneTimeline : SendMessageToYX.Req.YXSceneSession;
		api.sendRequest(req);
	}

	/**
	 * sendImage,分享图片到易信(根据本地图片的路径)
	 * 
	 * @param context
	 *            上下文
	 * @param path
	 *            要分享的图片路径
	 * @param scene
	 *            是否分享到朋友圈
	 */
	public void sendImageByPath(Context context, String path, boolean scene) throws Exception {

		File file = new File(path);
		if (!file.exists()) {
			String tip = "文件不存在";
			Toast.makeText(context, tip + " path = " + path, Toast.LENGTH_SHORT).show();
			return;
		}

		YXImageMessageData imgObj = new YXImageMessageData();
		imgObj.imagePath = path;

		YXMessage msg = new YXMessage();
		msg.messageData = imgObj;
		Bitmap bmp = BitmapFactory.decodeFile(path);
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
		bmp.recycle();
		msg.thumbData = BitmapUtil.bmpToByteArray(thumbBmp, true);

		SendMessageToYX.Req req = new SendMessageToYX.Req();
		req.transaction = buildTransaction("img");
		req.message = msg;
		req.scene = scene ? SendMessageToYX.Req.YXSceneTimeline : SendMessageToYX.Req.YXSceneSession;
		api.sendRequest(req);
	}

	/**
	 * sendImage,分享图片到易信(根据图片的url)
	 * 
	 * @param context
	 *            上下文
	 * @param url
	 *            要分享的图片url
	 * @param scene
	 *            是否分享到朋友圈
	 */
	public void sendImageByUrl(Context context, String url, boolean scene) throws Exception {
		// String url =
		// "http://img1.gamersky.com/image2013/08/20130824u_4/gamersky_10small_20_2013824120334.jpg";

		YXImageMessageData imgObj = new YXImageMessageData();
		imgObj.imageUrl = url;

		YXMessage msg = new YXMessage();
		msg.messageData = imgObj;

		/** 暂不需缩略图 */
		// Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
		// R.drawable.test300);
		// // 这里修改150参数可以测试缩略图质量情况
		// Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 200, 200, true);
		// bmp.recycle();
		// msg.thumbData = BitmapUtil.bmpToByteArray(thumbBmp, true); //
		// 设置缩略图

		SendMessageToYX.Req req = new SendMessageToYX.Req();
		req.transaction = buildTransaction("img");
		req.message = msg;
		req.scene = scene ? SendMessageToYX.Req.YXSceneTimeline : SendMessageToYX.Req.YXSceneSession;
		api.sendRequest(req);

	}

	/**
	 * 通用方法,摘自易信SDK Demo.
	 */
	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}

}
