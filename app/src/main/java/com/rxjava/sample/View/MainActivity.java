package com.rxjava.sample.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rxjava.sample.R;
import com.rxjava.sample.Utils.ToastUtil;
import com.rxjava.sample.bean.User;
import com.rxjava.sample.dragger2.components.DaggerUserComponent;
import com.rxjava.sample.dragger2.components.UserComponent;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private Button mEnsure;

    UserComponent userComponent;
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userComponent = DaggerUserComponent.builder()
                .appComponent(getAppComponent()) // 依赖AppComponent
                .activityModule(getActivityModule()) // 依赖ActivityModule
                .build();
        userComponent.inject(this);

        mEnsure = (Button) findViewById(R.id.btn_ensure);
        mEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(user.getName());
            }
        });
    }
}
