package com.mobile.app.javashop.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by LDD on 17/3/14.
 */

public class ImageModel extends BaseObservable {


    public String ImageUrl;

    public String Title ;

    public ImageModel(String imageUrl, String title) {
        ImageUrl = imageUrl;
        Title = title;
    }

    @Bindable
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
        notifyPropertyChanged(BR.title);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView image,String url){
        Glide.with(image.getContext()).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(image);
    }


}
