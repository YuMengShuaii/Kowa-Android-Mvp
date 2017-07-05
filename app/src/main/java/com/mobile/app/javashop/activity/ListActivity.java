package com.mobile.app.javashop.activity;

import android.support.v4.app.Fragment;

import com.mobile.app.javashop.Config;
import com.mobile.app.javashop.R;
import com.mobile.app.javashop.adapter.MyPagerAdapter;
import com.mobile.app.javashop.application.App;
import com.mobile.app.javashop.base.BaseActivity;
import com.mobile.app.javashop.contract.ListContract;
import com.mobile.app.javashop.databinding.ListActBinding;
import com.mobile.app.javashop.fragment.fragment;
import com.mobile.app.javashop.model.BolgModel;
import com.mobile.app.javashop.model.ImageModel;
import com.mobile.app.javashop.presenter.ListPresenter;
import com.mobile.app.javashop.utils.StutasBarUtils;
import com.nat.android.javashoplib.utils.TsLog;
import com.yms.aptlib.router.Router;
import java.util.ArrayList;

import com.nat.android.javashoplib.utils.Utils;

/**
 * Created by LDD on 17/3/28.
 */
@Router(Config.HOME)
public class ListActivity extends BaseActivity<ListPresenter,ListActBinding> implements ListContract.view {

    @Override
    protected int getLayId() {
        return R.layout.list_act;
    }

    @Override
    protected void bindDagger() {
        App.component.inject(this);
    }

    @Override
    protected void init() {
        presenter.registerBusEvent();
        StutasBarUtils.FullByCollLay(activity,mViewBinding.toolbar,mViewBinding.collapsingToolbarLayout);
        ImageModel imageModel = new ImageModel("http://onghqryqs.bkt.clouddn.com/_____________________lanrentuku.com.jpg","asdsadasd");
        mViewBinding.setModel(imageModel);
        presenter.LoadBlog();
    }

    @Override
    protected void BindEvent() {

    }

    @Override
    public void showBlog(ArrayList<ArrayList<BolgModel.DataBean>> data) {
        Fragment[] fragments = new Fragment[data.size()];
        String[]  titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            fragments[i] = fragment.getInstance(data.get(i));
            titles[i] = data.get(i).get(0).getTags().get(0).getName();
        }
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),fragments,getBaseContext(),titles);
        mViewBinding.viewPager.setAdapter(adapter);
        mViewBinding.viewPager.setOffscreenPageLimit(4);
        mViewBinding.slidingTabs.setupWithViewPager(mViewBinding.viewPager);
    }

    @Override
    public void start() {
        showDialog();
    }

    @Override
    public void showError(String messgae) {
        Utils.ToastL(messgae);
        TsLog.e(messgae);
    }

    @Override
    public void complete(String message) {
        dismissDialog();
        Utils.ToastL(message);
    }

    @Override
    protected void destory() {

    }
}
