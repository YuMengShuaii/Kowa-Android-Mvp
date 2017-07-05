package com.mobile.app.javashop.commponent;

import com.mobile.app.javashop.api.ApiService;
import com.mobile.app.javashop.module.MainModule;

import dagger.Component;

/**
 * Created by LDD on 17/4/12.
 */

@Component(modules = {MainModule.class})
public interface ApplicationComponent {
    ApiService provideApi();
}
