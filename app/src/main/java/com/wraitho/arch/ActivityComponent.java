package com.wraitho.arch;

import com.squareup.picasso.Picasso;
import com.wraitho.api.characters.CharactersApi;
import com.wraitho.arch.feature.characters.CharactersStore;
import com.wraitho.arch.feature.home.HomeActivity;

import dagger.Component;
import rx.Scheduler;

@PerActivity
@Component(dependencies = ArchComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
	void inject(HomeActivity homeActivity);

	Scheduler scheduler();
	Picasso picasso();

	// EXPOSING API-CLIENTS
	CharactersApi charactersApi();

	// EXPOSING STORES
	CharactersStore charactersStrore();

}
