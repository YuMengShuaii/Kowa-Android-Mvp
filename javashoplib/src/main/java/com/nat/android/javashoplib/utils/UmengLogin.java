package com.nat.android.javashoplib.utils;

import android.app.Activity;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

/**
 * Created by LDD on 17/7/11.
 */

public class UmengLogin {
    private static ConnectListener Blistener;
    private static Activity myActivity;
    /**
     *  第三方登录
     * @param mActivity
     * @param type  登录方式
     * SHARE_MEDIA.QQ
     * SHARE_MEDIA.WEIXIN
     * SHARE_MEDIA.SINA
     */
    public static void login(Activity mActivity, SHARE_MEDIA type,ConnectListener listener){
        UmengLogin.myActivity=mActivity;
        Blistener = listener;
        UMShareAPI shareAPI = UMShareAPI.get( mActivity );
        shareAPI.doOauthVerify(mActivity,type, umAuthListener);
    }

    /**
     *  第三方登录监听
     */
    public static UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String,String> data) {
            Toast.makeText(myActivity, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e("data",data.toString());
            Blistener.success(data);
        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( myActivity, "授权失败", Toast.LENGTH_SHORT).show();
            Log.e("data",t.toString()+"");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( myActivity, "关闭授权", Toast.LENGTH_SHORT).show();
            Log.e("data","关闭授权");
        }
    };
    public interface ConnectListener{
        void success(Map<String,String> data);
    }
}
