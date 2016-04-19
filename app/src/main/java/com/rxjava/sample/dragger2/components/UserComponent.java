package com.rxjava.sample.dragger2.components;

import com.rxjava.sample.View.MainActivity;
import com.rxjava.sample.dragger2.PerActivity;
import com.rxjava.sample.dragger2.modules.ActivityModule;
import com.rxjava.sample.dragger2.modules.UserModule;

import dagger.Component;

/**
 * Created by zhouyuan0304 on 2016/4/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
    void inject(MainActivity mainActivity);
}
