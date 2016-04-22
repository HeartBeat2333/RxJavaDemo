package com.rxjava.sample.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rxjava.sample.R;
import com.rxjava.sample.Utils.ToastUtil;
import com.rxjava.sample.bean.Course;
import com.rxjava.sample.bean.Student;
import com.rxjava.sample.bean.User;
import com.rxjava.sample.dragger2.components.DaggerUserComponent;
import com.rxjava.sample.dragger2.components.UserComponent;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ListButtonActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ListButtonActivity";

    private Button mBtnEnsure;
    private Button mBtnCancel;

    UserComponent userComponent;
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        userComponent = DaggerUserComponent.builder()
                .appComponent(getAppComponent()) // 依赖AppComponent
                .activityModule(getActivityModule()) // 依赖ActivityModule
                .build();
        userComponent.inject(this);
        initViews();
    }

    private void initViews() {
       findViewById(R.id.dragger2_test).setOnClickListener(this);
       findViewById(R.id.rxjava_test1).setOnClickListener(this);
       findViewById(R.id.rxjava_test2).setOnClickListener(this);
       findViewById(R.id.rxjava_test3).setOnClickListener(this);
       findViewById(R.id.rxjava_test4).setOnClickListener(this);
       findViewById(R.id.rxjava_test5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dragger2_test:
                ToastUtil.show(user.getName());
                break;
            case R.id.rxjava_test1:
                // OnSubscribe 相当于一个任务计划
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello");
                        subscriber.onNext("Hi");
                        subscriber.onNext("Aloha");
                        subscriber.onCompleted();
                    }}).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "string :" + s);
                    }
                });

                break;
            case R.id.rxjava_test2:
                /*
                 Action0 是 RxJava 的一个接口，它只有一个方法 call()，这个方法是无参无返回值的
                 just(T...): 将传入的参数依次发送出来
                 from(T[]) / from(Iterable<? extends T>) : 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
                 */
                /*
                 Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
                 Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
                 Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
                     行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
                     可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中
                     ，可以避免创建不必要的线程。
                 Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被
                     I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。
                     不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
                 Android 专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
                 */
                Observable.just(1, 2, 3, 4)
                        .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                        .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer number) {
                                Log.d(TAG, "number : " + number);
                            }
                        });

                String[] words = {"Hello", "Hi", "Aloha"};
                Observable.from(words).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "string : " + s);
                    }
                });
                break;
            case R.id.rxjava_test3 :
                /*
                   Func1 和Action 的区别在于， Func1 包装的是有返回值的方法
                   map() 方法将参数中的 Integer 对象转换成一个 String 对象后返回
                 */
                Observable.just(5, 6, 7, 8)
                        .map(new Func1<Integer, String>() {
                            @Override
                            public String call(Integer integer) {
                                return String.valueOf(integer);
                            }
                        }).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                Log.d(TAG, "string : " + s);
                            }
                        });
                break;
            case R.id.rxjava_test4 :
                /*
                  flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象
                  flatMap() 中返回的是个 Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中.
                  flatMap() 的原理：
                  1. 使用传入的事件对象创建一个 Observable 对象；
                  2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
                  3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法
                 */
                Student[] students = {new Student("帅哥"), new Student("美女"), new Student("扶她")};
                Subscriber<Course> subscriber = new Subscriber<Course>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onNext(Course course) {
                        Log.d(TAG, course.getName());
                    }
                };
                Observable.from(students)
                        .flatMap(new Func1<Student, Observable<Course>>() {
                            @Override
                            public Observable<Course> call(Student student) {
                                return Observable.from(student.getCourses());
                            }
                        }).subscribe(subscriber);
                break;
            case R.id.rxjava_test5 :
                /*
                    doOnSubscribe() 执行在 subscribe() 发生的线程,在subscribe() 调用后而且在事件发送前执行.
                    而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
                 */
//                Observable.create(onSubscribe)
//                        .subscribeOn(Schedulers.io())
//                        .doOnSubscribe(new Action0() {
//                            @Override
//                            public void call() {
//                                progressBar.setVisibility(View.VISIBLE); // 需要在主线程执行
//                            }
//                        })
//                        .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(subscriber);
                break;
            default:
                // RxBinding、RxBus
                break;
        }
    }
}
