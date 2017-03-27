package com.wraitho.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * "Base" Activity which defines the order for setting content view, injecting dependencies, registering presenters
 * and deals with saving view states.
 * It uses {@link RxAppCompatActivity} to serve us with the {@link #lifecycle()}
 */
public abstract class PresenterActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        injectDependencies();
        initialisePresenters();
    }

    /**
     * Place to call {@link #setContentView(int)} so the view will be ready for usage in the presenters.
     */
    protected abstract void setContentView();

    /**
     * Injecting dependencies (it slightly implies that we use some kind of dependency injection framework yes.)
     */
    protected abstract void injectDependencies();

    /**
     * Here we have to register the used presenters
     */
    protected abstract void initialisePresenters();

}
