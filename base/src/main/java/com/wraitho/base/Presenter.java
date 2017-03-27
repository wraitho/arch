package com.wraitho.base;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * "Base" Presenter - all presenters should subtype this.
 * @param <View>    The view to connect with
 * @param <LifecycleEventType>  Based on Activity/Fragment it should be
 *                              {@link com.trello.rxlifecycle.android.ActivityEvent}
 *                              or {@link com.trello.rxlifecycle.android.FragmentEvent}
 */
public abstract class Presenter<View, LifecycleEventType>  {
    private View mView;
    private final LifecycleHandler<LifecycleEventType> mLifecycleHandler;
    private final Scheduler mViewScheduler;

    /**
     * Basic constructor for presenters.
     * @param lifecycleHandler  This can be provided from the joining Activity/Fragment
     * @param viewScheduler     In case of android it is {@link AndroidSchedulers#mainThread()} but it's better to provide
     *                          it from the Activity/Fragment or module as it eliminates the usage of Powermocks.
     */
    public Presenter(LifecycleHandler<LifecycleEventType> lifecycleHandler, Scheduler viewScheduler) {
        mLifecycleHandler = lifecycleHandler;
        mViewScheduler = viewScheduler;
    }

    /**
     * Sets the view. It is automatically called from the {@link #init(View)} so no need to call it manually.
     * @param view  the view
     */
    private void setView(@NonNull View view) {
        mView = view;
    }

    /**
     * Returns the view.
     * @return  View view
     */
    public View getView() {
        return mView;
    }

    /**
     * Initialising the view (sets the view, calls functions like lifecycle callbacks initialisation and UI interaction
     * initialisation
     * @param view  the current view (fragment, activity, custom, etc.)
     */
    public void init(View view) {
        setView(view);
        initLifecycleCallbacks();
        initUiInteractionOnResume();
    }

    /**
     * Initialises Lifecycle Callbacks
     * This method is called once-per-lifecycle (like onCreate in case of activities)
     * Implement function calls and connect with specific lifecycle events in this method.
     */
    protected abstract void initLifecycleCallbacks();

    /**
     * Initialises UI interactions
     * This method is called in onResume of Activity/Fragment. We can assign view with short operations here.
     * With RxLifecycle's help unsubscription should happen in the Pause event of the view.
     */
    protected abstract void initUiInteractions();

    /**
     * With usage of the lifecyclehandler we can get the current lifecycle object.
     *
     * @return {@link LifecycleHandler} with {@link com.trello.rxlifecycle.android.ActivityEvent}
     *         or {@link com.trello.rxlifecycle.android.FragmentEvent}
     */
    protected LifecycleHandler<LifecycleEventType> getLifecycleHandler() {
        return mLifecycleHandler;
    }

    /**
     * Returns a scheduler that should be used to touch the view.
     * @return {@link Scheduler} scheduler of the view
     */
    protected Scheduler getViewScheduler() {
        return mViewScheduler;
    }

    private void initUiInteractionOnResume() {
        List<Object> resumeEventsList = new ArrayList<>();
        resumeEventsList.add(ActivityEvent.RESUME);
        resumeEventsList.add(FragmentEvent.RESUME);

        RxLifecycleExtension.executeWhenEvent(mLifecycleHandler, resumeEventsList,
                Observable.fromCallable(() -> {
                    initUiInteractions();
                    return null;
                }))
                .subscribe();
    }
}
