package dragger2test.bean;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
public class Apple implements Fruit {
    String info = "Apple yummy!!!";

    public Apple() {}

    @Inject
    Apple(Info info){//被@Inject标记，使用这个构造器生成实例
        this.info = info.info;
    }

    @Override
    public void eat() {
        Log.i("Apple", info);
    }
}
