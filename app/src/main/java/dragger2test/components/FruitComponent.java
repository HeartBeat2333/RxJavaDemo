package dragger2test.components;

import android.app.Activity;

import dagger.Component;
import dragger2test.bean.Apple;
import dragger2test.modules.FruitModule;

@Component(modules = FruitModule.class)
public interface FruitComponent{
    void inject(Activity container);

    Apple apple();
}
