package com.haikuowuya.core.share;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;

public class ShareContent implements Serializable {

    private static final long serialVersionUID = -5860450411215821159L;

    private String shareJson;

    public ShareContent(String shareJson) {
        setShareJson(shareJson);
    }

    public static String getShareJsonByTypes(int[] types, String title, String imageUrl, String content, String url) {
        if (types == null) {
            types = new int[]{0, 1, 2, 3, 4, 5};
           // types = new int[]{ShareConstant.WEIXIN_FRIEND_SHARE, ShareConstant.WEIXIN_FRIENDS_SHARE, ShareConstant.SINA_WEIBO_SHARE, ShareConstant.SMS_SHARE, ShareConstant.EMAIL_SHARE};
        }
        Gson gson = new Gson();
        JsonShare jsonShare = new JsonShare();
        jsonShare.setSharePic(imageUrl);
        String shareTypes = "";
        for (int i = 0; i < types.length; i++) {
            if (i == 0) {
                shareTypes = shareTypes + types[i];
            } else {
                shareTypes = shareTypes + "," + types[i];
            }
            switch (i) {
                case ShareConstant.SMS_SHARE:
                    JsonShareItem jsonSMSShareItem = new JsonShareItem();
                    jsonSMSShareItem.setShareTitle(title);
                    jsonSMSShareItem.setShareContent(content);
                    jsonSMSShareItem.setShareUrl(url);
                    jsonShare.setSms(jsonSMSShareItem);
                    break;
                case ShareConstant.EMAIL_SHARE:
                    JsonShareItem jsonEmailShareItem = new JsonShareItem();
                    jsonEmailShareItem.setShareTitle(title);
                    jsonEmailShareItem.setShareContent(content);
                    jsonEmailShareItem.setShareUrl(url);
                    jsonShare.setEmail(jsonEmailShareItem);
                    break;
                case ShareConstant.SINA_WEIBO_SHARE:
                    JsonShareItem jsonSinaShareItem = new JsonShareItem();
                    jsonSinaShareItem.setShareTitle(title);
                    jsonSinaShareItem.setShareContent(content);
                    jsonSinaShareItem.setShareUrl(url);
                    jsonShare.setSina(jsonSinaShareItem);
                    break;
                case ShareConstant.WEIXIN_FRIEND_SHARE:
                    JsonShareItem jsonWeixinFriendShareItem = new JsonShareItem();
                    jsonWeixinFriendShareItem.setShareTitle(title);
                    jsonWeixinFriendShareItem.setShareContent(content);
                    jsonWeixinFriendShareItem.setShareUrl(url);
                    jsonShare.setWeixinFriend(jsonWeixinFriendShareItem);
                    break;
                case ShareConstant.WEIXIN_FRIENDS_SHARE:
                    JsonShareItem jsonWeixinFriendsShareItem = new JsonShareItem();
                    jsonWeixinFriendsShareItem.setShareTitle(title);
                    jsonWeixinFriendsShareItem.setShareContent(content);
                    jsonWeixinFriendsShareItem.setShareUrl(url);
                    jsonShare.setWeixinFriends(jsonWeixinFriendsShareItem);
                    break;
                case ShareConstant.YIXIN_FRIEND_SHARE:
                    JsonShareItem jsonYixinFriendShareItem = new JsonShareItem();
                    jsonYixinFriendShareItem.setShareTitle(title);
                    jsonYixinFriendShareItem.setShareContent(content);
                    jsonYixinFriendShareItem.setShareUrl(url);
                    jsonShare.setYixinFriend(jsonYixinFriendShareItem);
                    break;
                case ShareConstant.YIXIN_FRIENDS_SHARE:
                    JsonShareItem jsonYixinFriendsShareItem = new JsonShareItem();
                    jsonYixinFriendsShareItem.setShareTitle(title);
                    jsonYixinFriendsShareItem.setShareContent(content);
                    jsonYixinFriendsShareItem.setShareUrl(url);
                    jsonShare.setYixinFriends(jsonYixinFriendsShareItem);
                    break;
            }
        }
        jsonShare.setShareTypes(shareTypes);
        String json = gson.toJson(jsonShare);
        return json;
    }

    /**
     * 所需分享内容类
     *
     * @param key
     * @return
     */
    public JsonShareItem getShareContent(String key) {
        JsonShareItem jsonShareItem = null;
        if (!TextUtils.isEmpty(shareJson)) {
            JsonObject jsonContent = new JsonParser().parse(getShareJson()).getAsJsonObject();
            if (jsonContent.get(key) != null) {
                jsonContent = jsonContent.get(key).getAsJsonObject();
                if (jsonContent != null) {
                    try {
                        Gson gson = new Gson();
                        jsonShareItem = gson.fromJson(jsonContent, JsonShareItem.class);
                    } catch (Exception e) {
                    }
                }
            }
        } else {
            jsonShareItem = new JsonShareItem();
        }
        return jsonShareItem;
    }

    /**
     * 所需分享类型类
     *
     * @param key
     * @return
     */
    public String getShareTypes(String key) {
        JsonObject jsonContent = new JsonParser().parse(getShareJson()).getAsJsonObject();
        if (jsonContent.get(key) != null) {
            return jsonContent.get(key).getAsString();
        }
        return "";
    }

    /**
     * 所需分享图片地址
     *
     * @return
     */
    public String getSharePic() {
        Gson gson = new Gson();
        SharePic sharePic = gson.fromJson(getShareJson(), SharePic.class);
        return sharePic.getSharePic();
    }

    public void setSharePic() {
    }


    private class SharePic implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 7645346550081894286L;
        private String sharePic = "";

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }
    }


    private String getShareJson() {
        return shareJson;
    }

    private void setShareJson(String shareJson) {
        this.shareJson = shareJson;
    }

}
