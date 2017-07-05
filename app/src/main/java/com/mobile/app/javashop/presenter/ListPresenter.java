package com.mobile.app.javashop.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

import com.mobile.app.javashop.application.App;
import com.mobile.app.javashop.base.RxPresenter;
import com.mobile.app.javashop.contract.ListContract;
import com.mobile.app.javashop.model.BolgModel;
import com.mobile.app.javashop.model.ImageModel;

import com.mobile.app.javashop.model.catModel;
import com.nat.android.javashoplib.netutils.RxDataUtils;
import com.nat.android.javashoplib.netutils.ThreadFromUtils;
import com.nat.android.javashoplib.rxbus.RxBindEvent;
import com.nat.android.javashoplib.rxbus.RxBus;

/**
 * Created by LDD on 17/3/31.
 */
public class ListPresenter extends RxPresenter<ListContract.view> implements ListContract.Presenter,RxBindEvent{

    @Inject
    public ListPresenter() {

    }

    @Override
    public void LoadBlog() {
        Subscription getBlog = api.
                 getBlogData()
                .compose(ThreadFromUtils.defaultSchedulers())
                .subscribe(new Observer<List<BolgModel.DataBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(final List<BolgModel.DataBean> data) {
                        if (data==null||data.size()==0){
                            mView.showError("数据获取失败！");
                        }else{
                            getCat(data);
                        }
                    }
                });
        addSubscrebe(getBlog);
    }

    private void getCat(final List<BolgModel.DataBean> datas){
      Subscription subscription = RxDataUtils.RxGet(api.getCat(), new RxDataUtils.RxDataListener<catModel>() {
          @Override
          public void start() {
                mView.start();
          }

          @Override
            public void success(catModel data) {
                if (data==null||data.getCat()==null||data.getCat().size()==0){
                    return;
                }

                ArrayList<ArrayList<BolgModel.DataBean>> result = new ArrayList<>();
                for (catModel.CatBean catBean : data.getCat()) {
                    ArrayList<BolgModel.DataBean> data1 = new ArrayList<>();
                    for (BolgModel.DataBean dataBean : datas) {
                        if (dataBean.getTags().get(0).getName().equals(catBean.getName())){
                            data1.add(dataBean);
                        }
                    }
                    if (data1.size()!=0){
                        result.add(data1);
                    }
                }
                mView.showBlog(result);
                mView.complete("加载完成");
            }

            @Override
            public void failed(Throwable e) {
                mView.showError(e.getMessage());
            }
        });
        addSubscrebe(subscription);
    }

    @Override
    public void registerBusEvent() {
        addSubscrebe(RxBus.getDefault().register(ImageModel.class, data -> App.LogE("RxBusText",data.getImageUrl())));
    }
}
