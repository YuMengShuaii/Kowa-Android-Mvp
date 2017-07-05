package com.nat.android.javashoplib.utils;

import android.util.Log;

import com.nat.android.javashoplib.init.FrameInit;

/**
 * Created by LDD on 17/6/28.
 */

public class TsLog  {
    private static String defTag = "javashop";

    public static void e(String tag,String message){
        if (!FrameInit.LogEnable())return;
        if (tag==null||tag.equals("")){
            Log.e(defTag,message);
            return;
        }
        Log.e(tag,message);
    }

    public static void e(String message){
        e(null,message);
    }

    public static void d(String tag,String message){
        if (!FrameInit.LogEnable())return;
        if (tag==null||tag.equals("")){
            Log.d(defTag,message);
            return;
        }
        Log.d(tag,message);
    }

    public static void d(String message){
        d(null,message);
    }
}
