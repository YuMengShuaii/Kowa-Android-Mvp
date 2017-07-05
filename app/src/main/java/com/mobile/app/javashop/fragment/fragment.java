package com.mobile.app.javashop.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.mobile.app.javashop.Config;
import com.mobile.app.javashop.utils.RouterUtils;
import com.yms.apt.router.TRouter;
import com.yms.aptlib.router.Router;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import com.mobile.app.javashop.activity.MainActivity;
import com.mobile.app.javashop.R;
import com.mobile.app.javashop.adapter.MyRecyclerCommonAdapter;
import com.mobile.app.javashop.application.App;
import com.mobile.app.javashop.base.BaseActivity;
import com.mobile.app.javashop.base.BaseFragment;
import com.mobile.app.javashop.commponent.DaggerAppComponent;
import com.mobile.app.javashop.contract.fragmentContract;
import com.mobile.app.javashop.databinding.BolgFragBinding;
import com.mobile.app.javashop.databinding.ZhihuItemBinding;
import com.mobile.app.javashop.model.BolgModel;
import com.mobile.app.javashop.presenter.fragmentPresemter;

import com.nat.android.javashoplib.pay.payutils.Util;
import com.nat.android.javashoplib.utils.Utils;

/**
 * Created by LDD on 17/3/28.
 */

public class fragment extends BaseFragment<fragmentPresemter,BolgFragBinding> implements fragmentContract.view ,MultiItemTypeAdapter.OnItemClickListener {

    private MyRecyclerCommonAdapter adapter;

    private BolgModel.DataBean[] data;

    public static fragment getInstance(ArrayList<BolgModel.DataBean> data) {
        fragment frag = new fragment();
        Bundle bundle = new Bundle();
        BolgModel.DataBean[] datas = new BolgModel.DataBean[data.size()];
        data.toArray(datas);
        bundle.putParcelableArray("data",datas);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = (BolgModel.DataBean[]) getArguments().getParcelableArray("data");
    }

    @Override
    protected int getLayId() {
        return R.layout.bolg_frag;
    }

    @Override
    protected void bindDagger() {
        App.component.inject(this);
    }

    @Override
    protected void init() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mViewDataBinding.bolgList.setHasFixedSize(true);
        mViewDataBinding.bolgList.setLayoutManager(staggeredGridLayoutManager);
        mViewDataBinding.bolgList.setItemAnimator(new DefaultItemAnimator());
        presenter .LoadSingleBlog(data);
    }

    @Override
    protected void bindEvent() {
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void showError(String messgae) {

    }

    @Override
    public void complete(String message) {

    }

    @Override
    public void start() {

    }

    @Override
    protected void destory() {

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        RouterUtils.init(Config.DETAIL)
                    .addParams(Config.DataTag.PAGEDATA,adapter.getDatas().get(position))
                    .go();
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    public void showSingleBlog(BolgModel.DataBean[] data) {
        ArrayList<BolgModel.DataBean> d = Utils.TranformList(data);
        adapter = new MyRecyclerCommonAdapter<BolgModel.DataBean,ZhihuItemBinding>(getActivity(), R.layout.zhihu_item, d) {
            @Override
            protected void convert(final ZhihuItemBinding itemBinding, final BolgModel.DataBean topStoriesBean, int position) {
                itemBinding.zhituText.setText(topStoriesBean.getTitle());
            }
        };
        mViewDataBinding.bolgList.setAdapter(adapter);
    }
}
