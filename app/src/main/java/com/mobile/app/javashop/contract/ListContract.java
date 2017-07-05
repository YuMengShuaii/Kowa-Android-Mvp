package com.mobile.app.javashop.contract;

import android.view.View;

import java.util.ArrayList;

import com.mobile.app.javashop.base.BaseContract;
import com.mobile.app.javashop.databinding.ListActBinding;
import com.mobile.app.javashop.model.BolgModel;

/**
 * Created by LDD on 17/3/31.
 */

public interface ListContract {
    interface view extends BaseContract.BaseView {
        void showBlog(ArrayList<ArrayList<BolgModel.DataBean>> data);
    }
    interface Presenter extends BaseContract.BasePresenter<view> {
        void LoadBlog();
    }
}
