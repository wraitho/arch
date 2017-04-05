package com.wraitho.arch;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

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

	@Provides @Singleton
	protected Picasso providesPicasso() {
		return new Picasso.Builder(application.getApplicationContext())
				.loggingEnabled(BuildConfig.DEBUG)
				.indicatorsEnabled(BuildConfig.DEBUG)
				.listener((picasso, uri, exception) -> {
					Log.d(BuildConfig.APPLICATION_ID, "error in picasso: " + uri.toString());
					exception.printStackTrace();
				})
				.build();
	}
}
