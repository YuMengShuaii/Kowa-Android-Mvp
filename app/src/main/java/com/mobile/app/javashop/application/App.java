package com.mobile.app.javashop.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.mobile.app.javashop.BuildConfig;
import com.mobile.app.javashop.api.ApiService;
import com.mobile.app.javashop.commponent.AppComponent;
import com.mobile.app.javashop.commponent.DaggerAppComponent;
import com.mobile.app.javashop.commponent.DaggerApplicationComponent;
import com.mobile.app.javashop.module.MainModule;
import com.netease.gentiesdk.core.GTConfig;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMShareAPI;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import com.nat.android.javashoplib.init.FrameInit;

/**
 * Created by LDD on 17/3/28.
 */
public class App extends MultiDexApplication {


    /**
     * 储存数据公共空间
     */
    private static HashMap map;

    public static AppComponent component;

    private static App mApp;

    public Stack<Activity> store;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        map = new HashMap();
        initLog();
        initFrame();
        initUmeng();
        initRouter();
        initDagger();
        initWY();
    }

    private void initWY(){
        GTConfig.register(this, "8057f5b0e4fb48178207e30c4f48b20b");
    }

    private void initLog(){
        try {
            if (BuildConfig.DEBUG)
                Runtime.getRuntime().exec("logcat -f "+ getExternalCacheDir().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDagger(){
        component = DaggerAppComponent.builder()
                .applicationComponent(DaggerApplicationComponent.builder().mainModule(new MainModule(ApiService.class,"http://yumengshuai.cn/")).build())
                .build();
    }

    private void initFrame(){
        if (android.os.Build.VERSION.SDK_INT< android.os.Build.VERSION_CODES.LOLLIPOP){
            FrameInit.setIsOpenScroll(false);
        }
        FrameInit.initPay("http://yumengshuai.cn/");
        FrameInit.init(getBaseContext());
        FrameInit.setHomeActivity("ListActivity");
    }

    private void initUmeng(){
        UMShareAPI.get(this);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.setResourcePackageName("com.mobile.app.javashop");
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogE("UmengRegister",deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogE("UmengRegister",s+s1);
            }
        });
        PushAgent.getInstance(getApplicationContext()).onAppStart();
    }

    private void initRouter(){
        store = new Stack<>();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }

    /**
     * 存储数据
     * @param key
     * @param o
     */
    public static void put(String key,Object o){
        map.put(key,o);
    }

    /**
     * 获取数据
     * @param key
     * @param isDetele
     * @return
     */
    public static Object get(String key,boolean isDetele){
        if (isDetele){
            return map.remove(key);
        }else{
            return map.get(key);
        }
    }
    public static App getAppContext() {
        return mApp;
    }


    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity getCurActivity() {
        return store.lastElement();
    }

    public static void LogE(String tag,String message){
        if (BuildConfig.DEBUG){
            Log.e(tag,message);
        }

    }

    public static void LogD(String tag,String message){
        if (BuildConfig.DEBUG){
            Log.d(tag,message);
        }

    }
}
