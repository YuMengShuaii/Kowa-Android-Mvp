package com.mobile.app.javashop.presenter;


import android.graphics.BitmapFactory;
import android.view.View;

import com.mobile.app.javashop.R;
import com.mobile.app.javashop.application.App;
import com.netease.gentiesdk.core.callback.DocProfileResp;
import com.netease.gentiesdk.core.callback.RequestListener;
import com.netease.gentiesdk.core.requests.DocProfileRequest;

import javax.inject.Inject;

import com.mobile.app.javashop.base.RxPresenter;
import com.mobile.app.javashop.contract.MainContract;
import com.mobile.app.javashop.model.ImageModel;

import rx.Subscription;
import com.nat.android.javashoplib.rxbus.BusType;
import com.nat.android.javashoplib.rxbus.RxBindEvent;
import com.nat.android.javashoplib.rxbus.RxBus;
import com.nat.android.javashoplib.utils.UmengShare;

/**
 * Created by LDD on 17/4/1.
 */
public class MainPresenter extends RxPresenter<MainContract.view> implements MainContract.Presenter , RxBindEvent{
    @Inject
    public MainPresenter() {
    }

    @Override
    public void loadComment(String url) {
        new DocProfileRequest(url, "", new RequestListener<DocProfileResp>() {
            @Override
            public void onRequestSucceeded(DocProfileResp o) {
                mView.showComment(o);
            }

            @Override
            public void onRequestFailed(DocProfileResp o) {

            }
        }).startTask();
    }

    @Override
    public void registerBusEvent() {
         Subscription busEvent =  RxBus.getDefault().register(ImageModel.class,new RxBus.RxBusEvent<ImageModel>() {
            @Override
            public void event(ImageModel data) {
                mView.showError(data.getImageUrl());
            }
        });

        addSubscrebe(busEvent);
    }
}
