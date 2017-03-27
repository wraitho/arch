package com.wraitho.arch;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Provides commonly used stuff
 */
@Module
class ArchModule {

    private final Application application;

    ArchModule(Application application) {
        this.application = application;
    }

	@Provides @Singleton
	Context providesAppContext() {
		return application;
	}

    @Provides @Singleton
    Scheduler providesScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
