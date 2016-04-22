package com.rxjava.sample.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.rxjava.sample.R;
import com.rxjava.sample.Utils.ToastUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private Button mBtnEnsure;
    private Button mBtnCancel;

    private TextInputLayout mTilAccount;
    private TextInputLayout mTilPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        initInputText();
        initButtons();
    }

    private void initInputText() {
        mTilAccount = (TextInputLayout) findViewById(R.id.til_account);
        mTilPass = (TextInputLayout) findViewById(R.id.til_password);
        mTilAccount.setErrorEnabled(true);

        // RxBinding监听EditText
        RxTextView.textChanges(mTilAccount.getEditText()).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                ToastUtil.show(charSequence.toString());
            }
        });
        RxTextView.textChanges(mTilPass.getEditText()).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                ToastUtil.show(charSequence.toString());
            }
        });
    }

    private void initButtons() {
        mBtnEnsure = (Button) findViewById(R.id.btn_ensure);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);

        // 普通按钮响应
        mBtnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show(user.getName());
                Intent intent = new Intent(MainActivity.this, ListButtonActivity.class);
                startActivity(intent);
            }
        });

        // Rxbinding按钮响应， 可以指定响应的线程 IO computation等
        RxView.clicks(mBtnCancel).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                ToastUtil.show("cancel click");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                ToastUtil.show("cancel click 2");
            }
        });
    }
}
