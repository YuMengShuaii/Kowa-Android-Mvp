package com.mobile.app.javashop.contract;

import java.util.ArrayList;

import com.mobile.app.javashop.base.BaseContract;
import com.mobile.app.javashop.model.BolgModel;

/**
 * Created by LDD on 17/4/1.
 */

public interface fragmentContract {
    interface view extends BaseContract.BaseView {
        void showSingleBlog(BolgModel.DataBean[] datas );
    }

    interface Presenter extends BaseContract.BasePresenter<view> {
        void LoadSingleBlog(BolgModel.DataBean[] datas );
    }
}
