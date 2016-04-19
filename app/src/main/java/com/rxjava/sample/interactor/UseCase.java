package com.rxjava.sample.interactor;

import com.rxjava.sample.Utils.executor.PostExecutionThread;
import com.rxjava.sample.Utils.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by zhouyuan0304 on 2016/4/15.
 */
public abstract class UseCase {
    private Subscription subscription = Subscriptions.empty();

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    protected UseCase(ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable buildUseCaseObservable();

    public void excute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(null))
                .observeOn(null)
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if(!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
