package com.rxjava.sample.bean;

import javax.inject.Inject;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
public class User {
    private String name;
    private String password;

    @Inject
    public User() {}

    public String getName() {
        return "张三";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return "1234566";
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
