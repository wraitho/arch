package com.wraitho.api.characters;

import com.wraitho.data.Character;

import java.util.List;

import rx.Observable;

public class CharactersClient implements CharactersApi {

	private final CharactersService service;

	public CharactersClient(CharactersService service) {
		this.service = service;
	}

	@Override
	public Observable<List<Character>> getCharacters() {
		return service.getCharacters()
				.map(characterApiResponse -> characterApiResponse.getData().getResults());
	}
}
