package com.wraitho.base;

import com.trello.rxlifecycle.RxLifecycle;

import rx.Observable;

/**
 * With the help of the LifecycleHandler we can bind a specific logic to a given life cycle event. In this special case
 * we are binding the observable chain until a lifecycle event happens. It simply unsubscribes at the given lifecycle
 * event.
 *
 * This wraps RxLifecycle's static class so it's easier to mock, no need for Powermock. But We could interface this out,
 * making this not coupled with RxLifecycle library.
 *
 * @param <T> Please use {@link com.trello.rxlifecycle.android.ActivityEvent} or
 *            {@link com.trello.rxlifecycle.android.FragmentEvent} as a generic parameter based on the context.
 */
public class LifecycleHandler<T> {

    private Observable<T> lifecycle;

    public LifecycleHandler(Observable<T> lifecycle) {
        this.lifecycle = lifecycle;
    }

    public <R> Observable.Transformer<R, R> bindUntilEvent(T event) {
        return RxLifecycle.bindUntilEvent(lifecycle, event);
    }

    public Observable<T> getLifecycle() {
        return lifecycle;
    }
}
