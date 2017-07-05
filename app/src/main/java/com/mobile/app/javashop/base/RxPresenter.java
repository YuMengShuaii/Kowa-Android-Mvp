package com.mobile.app.javashop.base;


import com.mobile.app.javashop.api.ApiService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LDD on 17/3/31.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;
    protected ApiService api;

    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }

    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(T view,ApiService api) {
        this.mView = view;
        this.api = api;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

}