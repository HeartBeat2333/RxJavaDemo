package com.rxjava.sample.View;

import android.app.Activity;
import android.os.Bundle;

import com.rxjava.sample.RxApplication;
import com.rxjava.sample.dragger2.components.AppComponent;
import com.rxjava.sample.dragger2.modules.ActivityModule;

/**
 * Created by zhouyuan0304 on 2016/4/19.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
    }

    /**
     * 获取AppComponent进行依赖注入
     * @return
     */
    protected AppComponent getAppComponent() {
        return ((RxApplication)getApplication()).getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
