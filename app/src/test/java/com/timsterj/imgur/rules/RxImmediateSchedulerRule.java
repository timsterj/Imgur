package com.timsterj.imgur.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxImmediateSchedulerRule implements TestRule {

    private Scheduler immmediate = new Scheduler() {
        @Override
        public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
            return super.scheduleDirect(run, 0, unit);
        }

        @Override
        public Scheduler.Worker createWorker() {

            return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
        }
    };


    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> immmediate);
                RxJavaPlugins.setInitComputationSchedulerHandler(schedulerCallable -> immmediate);
                RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immmediate);
                RxJavaPlugins.setInitSingleSchedulerHandler(schedulerCallable -> immmediate);
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
