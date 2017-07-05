package com.mobile.app.javashop.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jph.takephoto.model.TImage;
import com.nat.android.javashoplib.utils.UmengShare;
import com.netease.gentiesdk.core.callback.DocProfileResp;
import com.netease.gentiesdk.views.DocCommentsFragment;
import com.umeng.socialize.UMShareAPI;
import com.yms.aptlib.router.Extra;
import com.yms.aptlib.router.Router;
import java.util.ArrayList;
import com.mobile.app.javashop.Config;
import com.mobile.app.javashop.R;
import com.mobile.app.javashop.application.App;
import com.mobile.app.javashop.base.BaseGalleryActivity;
import com.mobile.app.javashop.contract.MainContract;
import com.mobile.app.javashop.databinding.ActivityMainBinding;
import com.mobile.app.javashop.model.BolgModel;
import com.mobile.app.javashop.presenter.MainPresenter;
import com.mobile.app.javashop.utils.StutasBarUtils;
import com.nat.android.javashoplib.aop.isLogin;
import com.nat.android.javashoplib.photoutils.RxGetPhotoUtils;
import com.nat.android.javashoplib.rxbus.RxBus;
import com.nat.android.javashoplib.utils.ScreenUtils;
import com.nat.android.javashoplib.utils.ShareUtils;
import com.nat.android.javashoplib.utils.Utils;

@Router(Config.DETAIL)
public class MainActivity extends BaseGalleryActivity<MainPresenter,ActivityMainBinding> implements MainContract.view{
    private String start = "<html>";

    private String end = "</body></html>";

    private int px;

    @Extra(Config.DataTag.PAGEDATA)
    public BolgModel.DataBean data;

    @Override
    protected int getLayId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindDagger() {
        App.component.inject(this);
    }

    @Override
    protected void init() {

        StutasBarUtils.FullByCollLay(activity, mViewBinding.toolbar, mViewBinding.collapsingToolbarLayout, item -> {
            ShareUtils.shareUrl(activity,"http://yumengshuai.cn/" + data.getPath(),"Y-Blog","分享");
            return false;
        });
        presenter.registerBusEvent();
        initWebView();
        presenter.loadComment("http://yumengshuai.cn/" + data.getPath());
        Glide.with(getBaseContext()).load("http://onghqryqs.bkt.clouddn.com/_____________________lanrentuku.com.jpg").asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(mViewBinding.imageback);
        mViewBinding.toolbar.setTitle(data.getTitle());
        mViewBinding.collapsingToolbarLayout.setTitle(data.getTitle());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @isLogin
    protected void BindEvent() {
        mViewBinding.fab.setOnClickListener(
                v -> RxGetPhotoUtils.init(this)
                        .configCompress(true,true,false,102400,800,800)
                        .getPhotoFromGallery(true));
    }

    @Override
    protected void destory() {

    }

    @Override
    protected void getPhotoImage(String path) {
        App.LogD("PhotoImagePath",path);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showComment(DocProfileResp docData) {
        FragmentManager fm = (getSupportFragmentManager());
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        DocCommentsFragment docCommentsFragment = new DocCommentsFragment();
        fragmentTransaction.replace(R.id.comm, docCommentsFragment);
        docData.articleVO.setCrawlerData(data.getTitle(), data.getContent());
        docCommentsFragment.setDoc(docData.articleVO);
        fragmentTransaction.commit();
    }

    @Override
    public void showError(String messgae) {
        Utils.ToastL(messgae);
    }

    @Override
    public void complete(String message) {
        Utils.ToastL(message);
    }

    @Override
    public void start() {

    }

    private void initWebView() {
        px = ScreenUtils.getScreenWidthPX(getBaseContext());

        mViewBinding.blog.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();// 接受所有网站的证书
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                WebView.HitTestResult hit = view.getHitTestResult();
                if (hit != null) {
                    int hitType = hit.getType();
                    if (hitType == WebView.HitTestResult.SRC_ANCHOR_TYPE
                            || hitType == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {// 点击超链接
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    } else {
                        view.loadUrl(url);
                    }
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        String header = "<head><style>img{width:" + (ScreenUtils.px2dip(getBaseContext(), px) - 10) + "px !important;},body{width:"+(ScreenUtils.px2dip(getBaseContext(),px)-10)+"px !important;}</style></head><body>";
        mViewBinding.blog.getSettings().setJavaScriptEnabled(true);
        mViewBinding.blog.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mViewBinding.blog.loadDataWithBaseURL("", start + header + data.getContent() + end, "text/html", "utf-8", "");
    }
}
