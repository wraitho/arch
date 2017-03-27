package com.wraitho.base;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import rx.Observable;

/**
 *  Class with helper functions for RxLifecycle. (In Kotlin these could be nice extension functions)
 */
public class RxLifecycleExtension {

    /**
     * Executes a given piece of code at specific lifecycle event
     * @param lifecycleHandler  {@link LifecycleHandler} with the current lifecycle of the activity/fragment
     * @param event             The {@link com.trello.rxlifecycle.android.ActivityEvent}
     *                          or {@link com.trello.rxlifecycle.android.FragmentEvent} when we'd like to execute the code
     * @param observable        The piece of code that we'd like to execute wrapped in {@link Observable}
     *
     * @param <T>   {@link com.trello.rxlifecycle.android.ActivityEvent} or {@link com.trello.rxlifecycle.android.FragmentEvent}
     * @param <R>   {@link com.trello.rxlifecycle.android.ActivityEvent} or {@link com.trello.rxlifecycle.android.FragmentEvent}
     * @param <S>   Generic type of the {@link Observable} (usually the return type of the executable code).
     * @return      {@link Observable} as the result of the given observable filtered out and mapped to the result.
     */
    public static <T, R, S> Observable<S> executeWhenEvent(@Nonnull final LifecycleHandler<T> lifecycleHandler,
                                                           @Nonnull final R event, final Observable<S> observable) {
        List<R> eventsList = new ArrayList<>();
        eventsList.add(event);
        return executeWhenEvent(lifecycleHandler, eventsList, observable);
    }

    /**
     * Executes a given piece of code at a list of specific lifecycle events (usually can be used adding both
     * Activity's and Fragment's same events.
     *
     * @param lifecycleHandler  {@link LifecycleHandler} with the current lifecycle of the activity/fragment
     * @param events            The  List of {@link com.trello.rxlifecycle.android.ActivityEvent}
     *                          or {@link com.trello.rxlifecycle.android.FragmentEvent} when we'd like to execute the code
     * @param observable        The piece of code that we'd like to execute wrapped in {@link Observable}
     * @param <T>   {@link com.trello.rxlifecycle.android.ActivityEvent} or {@link com.trello.rxlifecycle.android.FragmentEvent}
     * @param <R>   {@link com.trello.rxlifecycle.android.ActivityEvent} or {@link com.trello.rxlifecycle.android.FragmentEvent}
     * @param <S>   Generic type of the {@link Observable} (usually the return type of the executable code).
     * @return      {@link Observable} as the result of the given observable filtered out and mapped to the result.
     */
    public static <T, R, S> Observable<S> executeWhenEvent(@Nonnull final LifecycleHandler<T> lifecycleHandler,
                                                           @Nonnull final List<R> events, final Observable<S> observable) {
        return lifecycleHandler.getLifecycle()
                .filter(events::contains)
                .flatMap(t -> observable);
    }
}
