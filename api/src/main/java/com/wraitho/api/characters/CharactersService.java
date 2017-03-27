package com.wraitho.api.characters;

import com.wraitho.data.ApiResponse;
import com.wraitho.data.Character;

import retrofit2.http.GET;
import rx.Observable;

public interface CharactersService {

	String PATH_EXTENSION = "characters";

	@GET(PATH_EXTENSION)
	Observable<ApiResponse<Character>> getCharacters();
}
