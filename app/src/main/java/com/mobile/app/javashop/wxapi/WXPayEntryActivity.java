package com.mobile.app.javashop.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.mobile.app.javashop.Config;
import com.mobile.app.javashop.R;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import com.nat.android.javashoplib.utils.Utils;

public class WXPayEntryActivity extends Activity
        implements IWXAPIEventHandler
{
    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;

    private void paymentCallback(int paramInt, String paramString)
    {
        switch (paramInt){
            case 0:
                Utils.ToastL(paramString);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case 1:
                Utils.ToastL("查看订单");
                break;
            case 2:
                Utils.ToastL("订单正在处理中，请您稍后查询订单状态！");
                break;
        }
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        this.api = WXAPIFactory.createWXAPI(this, Config.WECHAT_APP_ID);
        this.api.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent paramIntent)
    {
        super.onNewIntent(paramIntent);
        setIntent(paramIntent);
        this.api.handleIntent(paramIntent, this);
    }

    public void onReq(BaseReq paramBaseReq)
    {
    }

    public void onResp(BaseResp paramBaseResp)
    {
        Log.d(TAG, "onPayFinish, errCode = " + paramBaseResp.errCode);
        if (paramBaseResp.getType() == 5);
        switch (paramBaseResp.errCode)
        {
            case -1:
            default:
                paymentCallback(0, "支付失败，请您重试！");
                return;
            case 0:
                paymentCallback(1, "订单支付成功！");
                return;
            case -2:
        }
        paymentCallback(0, "取消支付！");
    }
}