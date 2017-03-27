package com.wraitho.api;

import com.wraitho.api.characters.CharactersApi;
import com.wraitho.api.characters.CharactersClient;
import com.wraitho.api.characters.CharactersService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Additionally to the API module it provides the ApiClient implementations.
 */
@Module
public class ApiClientsModule {

	@Provides @Singleton
	CharactersApi providesCharactersClient(Retrofit retrofit){
		CharactersService characterService = retrofit.create(CharactersService.class);
		return new CharactersClient(characterService);
	}
}
