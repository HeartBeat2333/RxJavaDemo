package com.rxjava.sample;

import android.app.Application;

import com.rxjava.sample.Utils.ContextProvider;

import dragger2test.components.DaggerFruitComponent;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
public class RxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextProvider.init(this);
        DaggerFruitComponent.builder().build();
    }
}
