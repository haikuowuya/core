package com.haikuowuya.core.mvp.view;

/**
 * View  interface
 */
public interface DataView
{
    /***
     * 获取数据失败时调用
     *
     * @param msg        失败的消息
     * @param requestTag 请求TAG
     */
    public void onGetDataFailured(String msg, String requestTag);

    /***
     * 数据请求成功时调用
     *
     * @param result     数据结果
     * @param requestTag 请求TAG
     */
    public void onGetDataSuccess(String result, String requestTag);
}
