package com.mobile.app.javashop.contract;

import com.netease.gentiesdk.core.callback.DocProfileResp;

import com.mobile.app.javashop.base.BaseContract;


/**
 * Created by LDD on 17/4/1.
 */

public interface MainContract  {
    interface view extends BaseContract.BaseView{
        void showComment(DocProfileResp docData);
    }
    interface Presenter extends BaseContract.BasePresenter<view>{
        void loadComment(String url);
    }
}
