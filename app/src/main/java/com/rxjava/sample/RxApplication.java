package com.rxjava.sample;

import android.app.Application;

import com.rxjava.sample.Utils.ContextProvider;
import com.rxjava.sample.dragger2.components.AppComponent;
import com.rxjava.sample.dragger2.components.DaggerAppComponent;
import com.rxjava.sample.dragger2.modules.AppModule;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
public class RxApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ContextProvider.init(this);
        initializeInjector();
    }

    private void initializeInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
