package com.wraitho.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * "Base" fragment which defines the order for injecting dependencies, registering presenters
 * and deals with saving view states.
 * It uses {@link RxFragment} to serve us with the {@link #lifecycle()}
 */
public abstract class PresenterFragment extends RxFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
        initialisePresenters();
    }

    /**
     * Injecting dependencies (it slightly implies that we use some kind of dependency injection framework yes.)
     */
    protected abstract void injectDependencies();

    /**
     * Here we have to register the used presenters
     */
    protected abstract void initialisePresenters();
}
