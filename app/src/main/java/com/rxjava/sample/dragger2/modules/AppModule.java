package com.rxjava.sample.dragger2.modules;

import android.content.Context;

import com.rxjava.sample.RxApplication;
import com.rxjava.sample.Utils.executor.JobExecutor;
import com.rxjava.sample.Utils.executor.PostExecutionThread;
import com.rxjava.sample.Utils.executor.ThreadExecutor;
import com.rxjava.sample.Utils.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhouyuan0304 on 2016/4/19.
 */
@Module
public class AppModule {
    private final RxApplication application;

    public AppModule(RxApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
