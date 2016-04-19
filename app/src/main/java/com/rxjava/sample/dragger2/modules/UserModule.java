package com.rxjava.sample.dragger2.modules;

import dagger.Module;

/**
 * Created by zhouyuan0304 on 2016/4/19.
 */
@Module
public class UserModule {

    /*
     如果有有参构造时，需要在Component bulid的时候传入实例
     例：DaggerFruitComponent.builder()
            .moduleA(new ModuleA(User user)) //指定Module实例
            .build()
      */
//    public UserModule() {}

}
