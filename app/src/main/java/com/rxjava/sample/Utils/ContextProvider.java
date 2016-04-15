package com.rxjava.sample.Utils;

import android.app.Application;
import android.content.Context;

public class ContextProvider {
    private static Context mContext;
    public static void init(Context context){
        if(!(context instanceof Application)){
            throw new IllegalArgumentException("the parameter must be application");
        }
        mContext = context;
    }
    public static Context getContext(){
        return mContext;
    }
}
