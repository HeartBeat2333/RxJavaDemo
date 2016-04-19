package com.rxjava.sample.dragger2.components;

import android.content.Context;

import com.rxjava.sample.Utils.executor.PostExecutionThread;
import com.rxjava.sample.Utils.executor.ThreadExecutor;
import com.rxjava.sample.View.BaseActivity;
import com.rxjava.sample.dragger2.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhouyuan0304 on 2016/4/19.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}
