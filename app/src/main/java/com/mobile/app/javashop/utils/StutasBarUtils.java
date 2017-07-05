package com.mobile.app.javashop.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.mobile.app.javashop.R;

import com.nat.android.javashoplib.BuildConfig;
import com.nat.android.javashoplib.init.FrameInit;
import com.nat.android.javashoplib.utils.ScreenUtils;
import com.nat.android.javashoplib.utils.ShareUtils;
import com.nat.android.javashoplib.utils.Utils;
import com.nat.android.javashoplib.utils.UtilsActivity;

/**
 * Created by LDD on 17/4/1.
 */

public class StutasBarUtils {
    public static void FullByCollLay(UtilsActivity activity, Toolbar toolbar,CollapsingToolbarLayout collapsing_toolbar_layout,Toolbar.OnMenuItemClickListener listener){
        initStatusBar(activity);
        setToolbar(activity,toolbar,listener);
        final CollapsingToolbarLayout.LayoutParams param = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
        param.setMargins(0, ScreenUtils.getStatusHeight(activity), 0, 0);
        toolbar.setLayoutParams(param);
    }
    public static void FullByCollLay(UtilsActivity activity, Toolbar toolbar,CollapsingToolbarLayout collapsing_toolbar_layout){
        FullByCollLay(activity,toolbar,collapsing_toolbar_layout,null);
    }
    private static void setToolbar(final UtilsActivity activity, Toolbar toolbar, Toolbar.OnMenuItemClickListener listener){
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity.getRunningActivityName().equals(FrameInit.getHomeActivity())){
                    Intent localIntent = new Intent("android.intent.action.MAIN");
                    localIntent.addCategory("android.intent.category.HOME");
                    activity.startActivity(localIntent);
                    activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
                    return;
                }
                activity.finish();
                activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });
        if (listener!=null){
            toolbar.setOnMenuItemClickListener(listener);
        }
        activity.getSupportActionBar().setHomeButtonEnabled(true); //显示小箭头
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayUseLogoEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private static void initStatusBar(AppCompatActivity activity){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarUpperAPI21(activity);
        }else{
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                setStatusBarUpperAPI19(activity);
            }
        }
    }

    // 5.0版本以上
    private static void setStatusBarUpperAPI21(AppCompatActivity activity) {
        Window window = activity.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        ViewGroup mContentView = (ViewGroup)activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }
    // 4.4 - 5.0版本
    private static void setStatusBarUpperAPI19(AppCompatActivity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = mContentView.getChildAt(0);
        //移除假的 View
        if (statusBarView != null && statusBarView.getLayoutParams() != null &&
                statusBarView.getLayoutParams().height == ScreenUtils.getStatusHeight(activity)) {
            mContentView.removeView(statusBarView);
        }
        //不预留空间
        if (mContentView.getChildAt(0) != null) {
            ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
        }
    }
}
