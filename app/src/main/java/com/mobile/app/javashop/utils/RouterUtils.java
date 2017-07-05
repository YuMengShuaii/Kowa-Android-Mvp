package com.mobile.app.javashop.utils;

import com.mobile.app.javashop.R;
import com.mobile.app.javashop.application.App;
import com.yms.apt.router.TRouter;
import java.util.HashMap;

/**
 * Created by LDD on 17/5/4.
 */

public class RouterUtils extends HashMap<String,Object> implements RouterControl {
    private static RouterUtils instance;
    private String tag;

    private RouterUtils() {

    }

    public static RouterControl init(String tag){
        if (instance==null){
            instance = new RouterUtils();
        }
        instance.setActTag(tag);
        instance.clear();
        return instance;
    }

    @Override
    public RouterControl addParams(String key,Object value){
        put(key,value);
        return instance;
    }

    private RouterControl setActTag(String tag) {
        this.tag = tag;
        return instance;
    }

    @Override
    public void go() {
        if (instance.size()==0)
        {
            TRouter.go(tag);
        }else{
            TRouter.go(tag,instance);
        }
        App.getAppContext().getCurActivity().overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
}
