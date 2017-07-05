package com.mobile.app.javashop.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mobile.app.javashop.api.ApiService;
import com.yms.apt.router.TRouter;

import javax.inject.Inject;

import com.nat.android.javashoplib.aop.TimeLog;
import com.nat.android.javashoplib.utils.UtilsActivity;

/**
 * Created by LDD on 17/4/1.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter,T2 extends ViewDataBinding> extends UtilsActivity {

    @Inject
    protected T presenter;
    @Inject
    ApiService api;

    protected T2 mViewBinding;

    @TimeLog
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(getLayId(), null, false);
        mViewBinding = DataBindingUtil.bind(rootView);
        setContentView(rootView);
        bindDagger();
        attachView();
        init();
        BindEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        mViewBinding.unbind();
        destory();
    }

    protected abstract int  getLayId();

    protected abstract void bindDagger();

    protected abstract void init();

    protected abstract void BindEvent();

    protected abstract void destory();

    private void attachView(){
        presenter.attachView(this,api);
        TRouter.bind(activity);
    }

}
