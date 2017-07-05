package com.mobile.app.javashop.presenter;

import javax.inject.Inject;

import com.mobile.app.javashop.base.RxPresenter;
import com.mobile.app.javashop.contract.fragmentContract;
import com.mobile.app.javashop.model.BolgModel;


/**
 * Created by LDD on 17/4/1.
 */
public class fragmentPresemter extends RxPresenter<fragmentContract.view> implements fragmentContract.Presenter {
    @Inject
    public fragmentPresemter() {

    }

    @Override
    public void LoadSingleBlog(BolgModel.DataBean[] data) {
        mView.showSingleBlog(data);
    }
}
