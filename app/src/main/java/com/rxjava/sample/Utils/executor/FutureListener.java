package com.rxjava.sample.Utils.executor;

public interface FutureListener<T> {
    public void onFutureDone(Future<T> future);
}
