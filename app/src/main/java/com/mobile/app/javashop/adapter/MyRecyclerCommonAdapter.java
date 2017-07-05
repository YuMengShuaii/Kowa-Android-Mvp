package com.mobile.app.javashop.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import com.mobile.app.javashop.databinding.ZhihuItemBinding;

/**
 *
 */
public abstract class MyRecyclerCommonAdapter<T,T2 extends ViewDataBinding> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public MyRecyclerCommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {

            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                T2 itemBinding = DataBindingUtil.bind(holder.getConvertView());
                MyRecyclerCommonAdapter.this.convert(itemBinding, t, position);
            }
        });
    }

    public void setDatas(List<T> datas) {
        super.mDatas = datas;
        mDatas = datas;
    }

    protected abstract void convert(T2 holder, T t, int position);

}

