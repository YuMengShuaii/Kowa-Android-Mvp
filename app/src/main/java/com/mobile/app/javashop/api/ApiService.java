package com.mobile.app.javashop.api;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;
import com.mobile.app.javashop.model.BolgModel;
import com.mobile.app.javashop.model.catModel;
import com.nat.android.javashoplib.netutils.ProgressResponseBody;

/**
 * Created by LDD on 17/3/14.
 */

public interface ApiService {

    @GET("content.json")
    Observable<List<BolgModel.DataBean>> getBlogData();

    @GET("cat.json")
    Observable<catModel> getCat();

    @GET("Home_anim.json")
    Observable<ResponseBody> getHomeAnim();

}
