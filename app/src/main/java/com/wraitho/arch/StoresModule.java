package com.wraitho.arch;

import com.wraitho.arch.feature.characters.CharactersStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StoresModule {

	@Provides @Singleton
	public CharactersStore providesCharactersStore() {
		return new CharactersStore();
	}

}
