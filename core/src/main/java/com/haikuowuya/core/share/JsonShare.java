package com.haikuowuya.core.share;

/**
 * Created by man on 2015/7/7.
 */
public class JsonShare {

    private String sharePic;

    private String shareTypes;

    private JsonShareItem sms;

    private JsonShareItem email;

    private JsonShareItem sina;

    private JsonShareItem weixinFriend;

    private JsonShareItem weixinFriends;

    private JsonShareItem yixinFriend;

    private JsonShareItem yixinFriends;


    public JsonShareItem getSms() {
        return sms;
    }

    public void setSms(JsonShareItem sms) {
        this.sms = sms;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getShareTypes() {
        return shareTypes;
    }

    public void setShareTypes(String shareTypes) {
        this.shareTypes = shareTypes;
    }

    public JsonShareItem getEmail() {
        return email;
    }

    public void setEmail(JsonShareItem email) {
        this.email = email;
    }

    public JsonShareItem getSina() {
        return sina;
    }

    public void setSina(JsonShareItem sina) {
        this.sina = sina;
    }

    public JsonShareItem getWeixinFriend() {
        return weixinFriend;
    }

    public void setWeixinFriend(JsonShareItem weixinFriend) {
        this.weixinFriend = weixinFriend;
    }

    public JsonShareItem getWeixinFriends() {
        return weixinFriends;
    }

    public void setWeixinFriends(JsonShareItem weixinFriends) {
        this.weixinFriends = weixinFriends;
    }

    public JsonShareItem getYixinFriend() {
        return yixinFriend;
    }

    public void setYixinFriend(JsonShareItem yixinFriend) {
        this.yixinFriend = yixinFriend;
    }

    public JsonShareItem getYixinFriends() {
        return yixinFriends;
    }

    public void setYixinFriends(JsonShareItem yixinFriends) {
        this.yixinFriends = yixinFriends;
    }
}
