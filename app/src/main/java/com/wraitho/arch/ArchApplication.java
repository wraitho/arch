package com.wraitho.arch;

import android.app.Application;
import android.content.Context;

import com.wraitho.api.ApiModule;
import com.wraitho.api.ApiClientsModule;

public class ArchApplication extends Application {

	private ArchComponent archComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		createComponent();
	}

	private void createComponent() {
		if (archComponent == null) {
			archComponent = DaggerArchComponent.builder()
					.archModule(new ArchModule(this))
					.apiModule(new ApiModule())
					.apiClientsModule(new ApiClientsModule())
					.storesModule(new StoresModule())
					.build();
		}
	}

	public static ArchApplication get(Context context) {
		return (ArchApplication) context.getApplicationContext();
	}

	public ArchComponent getArchComponent() {
		return archComponent;
	}

}
