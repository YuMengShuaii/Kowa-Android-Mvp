package com.mobile.app.javashop.utils;

/**
 * Created by LDD on 17/5/5.
 */

public interface RouterControl {
    void go();
    RouterControl addParams(String key, Object value);
}
