package com.rxjava.sample.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rxjava.sample.R;

import javax.inject.Inject;

import dragger2test.bean.Fruit;

public class MainActivity extends Activity {

    private Button mEnsure;

    @Inject
    Fruit fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DaggerFruitComponent.create().inject(this);

        mEnsure = (Button) findViewById(R.id.btn_ensure);
        mEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruit.eat();
            }
        });
        fruit.eat();
    }
}
