package com.rxjava.sample.Utils.executor;

public abstract class ThreadExecutor {

    public interface Job<T> {
        public T run(JobContext jc);
    }

    public interface CancelListener {
        public void onCancel();
    }

    public interface JobContext {
        boolean isCancelled();
        void setCancelListener(CancelListener listener);
        boolean setMode(int mode);
    }

}
