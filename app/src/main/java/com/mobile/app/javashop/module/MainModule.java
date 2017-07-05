package com.mobile.app.javashop.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobile.app.javashop.api.ApiService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import com.nat.android.javashoplib.init.FrameInit;
import com.nat.android.javashoplib.netutils.NetServiceFactory;

/**
 * Created by LDD on 17/4/10.
 */
@Module
public class MainModule {

    private Class<ApiService> s;
    private String BaseUrl;
    public MainModule(Class<ApiService> s,String BaseUrl) {
        this.BaseUrl = BaseUrl;
        this.s = s;
    }

    //在出现相同类型返回值方法时，加上Named注解用来区分参数
    @Named("BaseConext")
    @Provides
    public Context provideContext() {
        return FrameInit.Mcontext;
    }

    @Named("Application")
    @Provides
    public Context provideApplication() {
        return FrameInit.Mcontext.getApplicationContext();
    }

    @Provides
    public String provideCacheFillDir() {
        return FrameInit.Mcontext.getApplicationContext().getExternalCacheDir().getAbsolutePath();
    }

    @Provides
    public SharedPreferences provideSp() {
        return FrameInit.Mcontext.getApplicationContext().getSharedPreferences("MainCache", Context.MODE_PRIVATE);
    }

    @Provides
    public ApiService provideApi() {
        return  NetServiceFactory.getInstance().createService(s,BaseUrl);
    }


}
