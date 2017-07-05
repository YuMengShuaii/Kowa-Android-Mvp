package com.mobile.app.javashop.commponent;

import com.mobile.app.javashop.activity.ListActivity;
import com.mobile.app.javashop.activity.MainActivity;
import com.mobile.app.javashop.fragment.fragment;
import dagger.Component;

/**
 * Created by LDD on 17/4/7.
 */

@Component(dependencies = ApplicationComponent.class)
public interface AppComponent {
    void inject(fragment fragment);

    void inject(ListActivity activity);

    void inject(MainActivity activity);
}
