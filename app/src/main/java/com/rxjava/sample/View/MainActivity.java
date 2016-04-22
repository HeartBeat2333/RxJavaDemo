package com.rxjava.sample.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rxjava.sample.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private Button mBtnEnsure;
    private Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBtnEnsure = (Button) findViewById(R.id.btn_ensure);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);

        mBtnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show(user.getName());
                Intent intent = new Intent(MainActivity.this, ListButtonActivity.class);
                startActivity(intent);
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
